# line-bot-kun
LINE Botのお試し。 based on SpringBoot.

### How to boot on local
```
# mvn clean package
# mvn spring-boot:run -D CHANNEL_SECRET=key -D CHANNEL_TOKEN=key
```

### アクセストークン、シークレットキーの取得
applicaton.properties に埋め込むわけにいかないので、herokuの機能を利用。

```application.properties
line.bot.channelSecret=${CHANNEL_SECRET}
line.bot.channelToken=${CHANNEL_TOKEN}
```
上記の通り設定しておき、herokuの環境変数で置き換える。 ⇒ [ここをみた](https://devcenter.heroku.com/articles/config-vars#setting-up-config-vars-for-a-deployed-application)

### 参考資料

* LINE アカウントの作成まで
  * [LINE BOTの作り方を世界一わかりやすく解説（１）【アカウント準備編】](http://qiita.com/yoshizaki_kkgk/items/bd4277d3943200beab26)
* SpringBootでBotのアプリケーション作成
  * [Spring BootとLINE Messaging APIで作ったLINE BOTをHerokuで動かす](http://kikutaro777.hatenablog.com/entry/2017/01/16/230122)

### LINEの管理
作成したLINEアカウントの管理画面
* [LINE BUSSINESS CENTER](https://business.line.me/ja/companies/1460544/accounts?ownerType=company&roleType=operator)
  
以下のリンクがある
* [LINE@ MANAGER](https://admin-official.line.me/8555323/account/)
  * LINE@としての挙動制御
* [LINE developer](https://developers.line.me/ba/udd0c5b9f4969c3dddd53b59768279068/bot)
  * LINE@をBotとしているときに必要な情報(シークレットキー、アクセストークン･･･)
