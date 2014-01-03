Garapon4J
==============

The Java Wrapper for Garapon TV API.

Garapon4Jは、だれでも簡単にJavaアプリケーションからGaraponTVに接続し、番組の情報を取得できるライブラリです。これは@NoriakiHoriuchiが作成している、非公式のライブラリです。

## 特徴
  1. Java界唯一のガラポンTV APIラッパ。
  1. 純粋Java。JDK7にて動作確認済。
  1. 低依存性。有名なライブラリしか使っていません。
    * Joda-Time
    * HTTP Components HttpCore
    * HTTP Components HttpClient
    * Jackson
  1. 簡単な認証。めんどうなWeb認証と端末認証をシームレスに実現。
  1. ガラポンTV APIにほぼ完全対応。

## version 0.1.1 最初のリリース
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
1. 1月13日までに実装予定
  * SearchBuilder#setKey：全角文字が2文字以上だとヒットしない。
  * SearchBuilder#setGenre0, setGenre1：ProgramInfoでparseに失敗する。
  * 簡単なテスト
1. やりたい
  * お気に入り追加結果を生Jsonから `boolean` または独自オブジェクトに変更
1. 余裕あれば
  * 録画チャンネル取得結果を生Jsonから 独自オブジェクトに変更
  
## 関連リンク
* http://garapon.tv/developer
