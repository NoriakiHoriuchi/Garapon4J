
package com.example.garapon4jsample;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import me.gizio.garapon4j.GaraponImpl;
import me.gizio.garapon4j.json.Program;
import me.gizio.garapon4j.other.GaraponSettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class MainActivity extends Activity {

    private static GaraponImpl mGaraponImpl;
    private static GaraponSettings mSettings;
    private TextView mPositiveCount;
    private TextView mNegativeCount;
    private ArrayList<Program> mPositiveArrayList;
    private ArrayList<Program> mNegativeArrayList;
    private ListView mNegativeListView;
    private ListView mPositiveListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPositiveCount = (TextView) findViewById(R.id.positive_count);
        mNegativeCount = (TextView) findViewById(R.id.negative_count);
        mNegativeListView = (ListView) findViewById(R.id.negative_list);
        mPositiveListView = (ListView) findViewById(R.id.positive_list);

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                startNetwork();
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                mPositiveCount.setText(mPositiveArrayList.size() + "件");
                mNegativeCount.setText(mNegativeArrayList.size() + "件");

                ArrayAdapter<Program> negativeAdapter = new ProgramArrayAdapter(
                        getApplicationContext(),
                        mNegativeArrayList);
                mNegativeListView.setAdapter(negativeAdapter);

                ArrayAdapter<Program> positiveAdapter = new ProgramArrayAdapter(
                        getApplicationContext(),
                        mPositiveArrayList);
                mPositiveListView.setAdapter(positiveAdapter);
            }
        };
        task.execute();
    }

    private void startNetwork() {
        Util.logv("============ before ============");
        mGaraponImpl = GaraponImpl.getInstance();
        mSettings = GaraponSettings.getInstance();
        print();
        Util.logv("============ before ============");

        Util.logv("============ initialize ============");
        Properties prop = new Properties();
        AssetManager assets = getResources().getAssets();

        String configFile = "garapon.conf";
        try {
            // prop.load(new FileInputStream(configFile));
            prop.load(assets.open(configFile));
            String user = prop.getProperty("user");
            String password = prop.getProperty("md5password");
            String devid = prop.getProperty("devid");
            mGaraponImpl.initialize(user, password, devid);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        print();
        Util.logv("============ initialize ============");
        Util.logv("============ search ============");
        String nagativeKeyword = getResources().getString(R.string.nagative);
        mNegativeArrayList = getSearchKey(nagativeKeyword);
        String positiveKeyword = getResources().getString(R.string.positive);
        mPositiveArrayList = getSearchKey(positiveKeyword);
    }

    private ArrayList<Program> getSearchKey(String keyword) {
        ArrayList<Program> kohaku = mGaraponImpl.getSearchBuilder().setDt("s")
                .setS("e").setKey(keyword).execute();
        Util.logv(kohaku.size());
        for (Program k : kohaku) {
            Util.logv(k.getTitle());
        }
        return kohaku;
    }

    private static void print() {
        Util.logv(mSettings.getUser());
        Util.logv(mSettings.getMD5Password());
        Util.logv(mSettings.getDevId());
        Util.logv(mSettings.getIpAddress());
        Util.logv(mSettings.getPrivateIpAddress());
        Util.logv(mSettings.getGrobalIpAddress());
        Util.logv(mSettings.getPort());
        Util.logv(mSettings.getPort2());
        Util.logv(mSettings.getGtvApiVer());
        Util.logv(mSettings.getGtvSessionId());
        Util.logv(mSettings.getGtvFarmwareVer());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
