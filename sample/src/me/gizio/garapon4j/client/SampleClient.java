package me.gizio.garapon4j.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import me.gizio.garapon4j.GaraponImpl;
import me.gizio.garapon4j.json.ProgramInfo;
import me.gizio.garapon4j.other.GaraponSettings;

public class SampleClient {
	static GaraponImpl garapon;
	static GaraponSettings settings;

	public static void main(String[] args) {
		System.out.println("============ before ============");
		garapon = GaraponImpl.getInstance();
		settings = GaraponSettings.getInstance();
		print();
		System.out.println("============ before ============");

		System.out.println("============ initialize ============");
		Properties prop = new Properties();
		String configFile = "conf/garapon.conf";
		try {
			prop.load(new FileInputStream(configFile));
			String user = prop.getProperty("user");
			String password = prop.getProperty("md5password");
			String devid = prop.getProperty("devid");
			garapon.initialize(user, password, devid);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		print();
		System.out.println("============ initialize ============");
		System.out.println("============ search ============");
		print();
		ArrayList<ProgramInfo> list = garapon.search();
		System.out.println(list.size());
		System.out.println(list.toString());
		System.out.println("============ search ============");
		System.out
				.println("============ search NHK Kohaku Utagassen ============");
		print();
		ArrayList<ProgramInfo> kohaku = garapon.getSearchBuilder().setDt("s")
				.setS("e").setKey("紅").execute();
		System.out.println(kohaku.size());
		for (ProgramInfo k : kohaku) {
			System.out.println(k.getTitle());
			// System.out.println(k.getRtmpUrl());
		}
		System.out
				.println("============ search NHK Kohaku Utagassen ============");
		System.out.println("============ searchbuilder ============");
		print();

		ArrayList<ProgramInfo> list2 = garapon.getSearchBuilder().setN("1")
				.execute();
		System.out.println(list2.size());
		for (ProgramInfo k : list2) {
			System.out.println(k.getTitle());
		}
		System.out.println("============ searchbuilder ============");
		System.out.println("============ favorite ============");
		print();
		boolean result2 = garapon.favorite(list2.get(0).getGtvid(), "-1");
		System.out.println(result2);
		System.out.println("============ favorite ============");
		System.out.println("============ channel ============");
		print();
		String result = garapon.channel();
		System.out.println(result);
		System.out.println("============ channel ============");
	}

	private static void print() {
		System.out.println(settings.getUser());
		System.out.println(settings.getMD5Password());
		System.out.println(settings.getDevId());
		System.out.println(settings.getIpAddress());
		System.out.println(settings.getPrivateIpAddress());
		System.out.println(settings.getGrobalIpAddress());
		System.out.println(settings.getPort());
		System.out.println(settings.getPort2());
		System.out.println(settings.getGtvApiVer());
		System.out.println(settings.getGtvSessionId());
		System.out.println(settings.getGtvFarmwareVer());

	}
}
