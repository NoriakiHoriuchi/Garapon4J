Garapon4J
==============

The Java Wrapper for Garapon TV API.

Garapon4Jは、だれでも簡単にJavaアプリケーションからGaraponTVに接続し、番組の情報を取得できるライブラリです。これは@NoriakiHoriuchiが作成している、非公式のライブラリです。

## 特徴
  1. Java界唯一のガラポンTV APIラッパ。
  1. 純粋Java。JDK6にて動作確認済。
  1. 簡単な認証。めんどうなWeb認証と端末認証をシームレスに実現。
  1. ガラポンTV APIにほぼ完全対応。

## 依存性
  * Joda-Time 2.3
  * HTTP Components HttpCore 4.3
  * HTTP Components HttpClient 4.3.1
  * Jackson 2.2.3


## version 0.1.3
第1回ガラポンTVハッカソンから重要なフィードバックを得ました。
[ダウンロードはこちら](https://github.com/NoriakiHoriuchi/Garapon4J/releases)

## できること
ガラポンTV APIで提供されているほぼすべての機能を利用できます。

1. Web認証と端末認証をシームレスに実行
1. 番組検索：選べる2種類の検索法
  * Mapで検索条件を一度に指定
  * メソッドチェーンで条件を絞り込み
1. 番組オブジェクト `Program` が返ってくる
1. 番組オブジェクトから簡単に視聴用URLを取得できる
1. お気に入り追加
1. 録画チャンネルの取得

## TODO
1. やりたい
    * 簡単なテスト
    * お気に入り追加結果を生Jsonから `boolean` または独自オブジェクトに変更 ←Done!
    * [宇野さんのRuby版ライブラリ](https://github.com/unok/GaraponTVAPI4Ruby)の[searchメソッド](https://github.com/unok/GaraponTVAPI4Ruby/blob/master/GaraponTVAPI4Ruby/search.rb)を参考にしたい
1. ディレクトリの整理 ←Done!
1. post周りのリファクタリング ←Done!
1. 余裕あれば
  * ジャンルをオブジェクト化
  * 録画チャンネル取得結果を生Jsonから 独自オブジェクトに変更
  
## 関連リンク
* http://garapon.tv/developer

## 中身について
projectフォルダ内がGarapon4J本体のプロジェクトです。これをインポートしてください。

## サンプルの使い方
Sampleフォルダ内をプロジェクトとしてインポートしてください。
confフォルダを作成し、その中に garapon.conf ファイルを作成してください。 ファイルには以下の様に設定してください。

```
    user=username
    md5password=ao2ertjaio4wfjaosd6gjqpweor6iuq234958u03jownf132oi45ualqw35uq9
    devid=upq3498npvw3ctuqad35omqipx85xq039px58yq398tycabq26937x430234x23jh42
```