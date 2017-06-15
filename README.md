# line-bot-kun
LINE Botのお試し。 based on SpringBoot.

## How to develop, boot
**herokuの追加**
```
# git clone git@github.com:maruhachi/line-bot-kun.git
# heroku git:remote --app APPNAME
```

**必要とするトークンを環境変数で渡す**
```
# export CHANNEL_SECRET=key
# export CHANNEL_TOKEN=key
# export AZURE_SUBSCRIPTION_KEY=toolongkey
# mvn spring-boot:run
# # if behind proxy
# export WORK_PROXY=proxy.host; mvn spring-boot:run -Drun.profiles=work
```

### アクセストークン、シークレットキーの取得
applicaton.properties に埋め込むわけにいかないので、herokuの機能を利用。

```application.properties
line.bot.channelSecret=${CHANNEL_SECRET}
line.bot.channelToken=${CHANNEL_TOKEN}
azure.cognitive.SubscriptionKey=${AZURE_SUBSCRIPTION_KEY}
```
上記の通り設定しておき、herokuの環境変数で置き換える。 ⇒ [ここをみた](https://devcenter.heroku.com/articles/config-vars#setting-up-config-vars-for-a-deployed-application)

## LINE@のBot利用

### 参考資料

* LINE アカウントの作成まで
  * [LINE BOTの作り方を世界一わかりやすく解説（１）【アカウント準備編】](http://qiita.com/yoshizaki_kkgk/items/bd4277d3943200beab26)
* SpringBootでBotのアプリケーション作成
  * [Spring BootとLINE Messaging APIで作ったLINE BOTをHerokuで動かす](http://kikutaro777.hatenablog.com/entry/2017/01/16/230122)

### LINEの管理
作成したLINEアカウントの管理画面
* [LINE BUSINESS CENTER](https://business.line.me/ja/companies/1460544/accounts?ownerType=company&roleType=operator)

[![友達に追加](https://scdn.line-apps.com/n/line_add_friends/btn/ja.png)](https://line.me/R/ti/p/%40wlj3544j)

以下のリンクがある
* [LINE@ MANAGER](https://admin-official.line.me/8555323/account/)
  * LINE@アカウントとしての挙動制御
* [LINE developer](https://developers.line.me/ba/udd0c5b9f4969c3dddd53b59768279068/bot)
  * Botとして必要な情報(シークレットキー、アクセストークン、コールバックURL)

## Microsoft Translation APIの利用

**まず、TranslationAPIは、Azureの1機能として追加する形で利用するよう。**
そのため、Azureへログインできる環境をまず用意。

そうしたらAPIを用意する
* [Qiita - Microsoft Translation APIを使ってみた](http://qiita.com/helicalgear/items/d34fac20d68f17e75406#azure)

用意したAPIを呼ぶ流れ
* CognitiveService共通のトークンを発行してもらう
  * [OAuth token API reference](http://docs.microsofttranslator.com/oauth-token.html#!/Authentication_token_service/getToken)
* TranslationAPIへ上記トークンをつけてRequest
  * [Translation API reference](http://docs.microsofttranslator.com/text-translate.html#!/default/get_Translate)

しかし、ここまでやって得られるResponseは`<string honyarara="url">afterTranslate</string>`とかいう謎XML  
扱いに困り、暫定で正規表現で取り出してる。
