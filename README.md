# Spring BootでURL短縮サービスを作ってみる

習作です。

## 概要

Spring Boot、PostgreSQL、JSPでURL短縮サービス（短縮URLの管理画面付き）を作ってみました。

`http://localhost:8080/{短縮文字列}`にアクセスすると元のURLへリダイレクトします。

`http://localhost:8080/urlmappings`にアクセスすると短縮URLの管理画面が開きます。
管理画面では短縮URLの登録・削除、登録されている短縮URLの確認ができます。

## 起動方法

### PostgreSQLを準備する

前準備としてPostgreSQLを起動しておいてください。
PostgreSQLは次のような設定を前提としています。

|設定項目|設定値|
|---|---|
|ホスト|`localhost`|
|ポート|`5432`|
|データベース名|`postgres`|
|ユーザー|`postgres`|
|パスワード|`postgres`|

この設定でPostgreSQLを準備するにはDockerを利用するのが簡単です。

```
docker run -d -p 5432:5432 --name=db postgres
```

とはいえ、PostgreSQLが起動してくれていれば良いのでDockerにはこだわりません。

なお、テーブルは手動で作成する必要はありません。
FlywayというDBマイグレーションライブラリを利用して勝手に作られるようにしています。

### アプリケーションを起動する

PostgreSQLが準備できたらアプリケーションを起動します。
IDEから`UrlShortenerApplication`を実行するか、もしくは次のコマンドを実行します。

```
mvnw spring-boot:run
```

## ライセンス

このアプリケーションは[The MIT License](https://opensource.org/licenses/MIT)で提供します。

