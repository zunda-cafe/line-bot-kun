# line-bot-kun
LINE Bot基盤 based on SpringBoot.  
複数のボット実装と共に。

## How to develop  
**実装クラスの追加**
このBotは起動時のプロファイル指定で機能を切り替えることができる。  
現在使用できるのは 翻訳：`translation`, ぐるなび(未実装)：`gnavi`

指定したプロファイルに対応する実装クラスをService,Repositoryに追加する。  
`LineBotController`が共通してLINEプラットフォームからメッセージを受け取る。
その内容を`LineBotService`を実装したクラスへ渡す。
```java
@LineMessageHandler
@RequiredArgsConstructor
public class LineBotController {
    private final LineBotService lineBotService;

    @EventMapping public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event){
        String msg = lineBotService.replyMessage(event.getMessage().getText());
        return new TextMessage(processedMsg);
    }
```

この時、`lineBotService`にインジェクションされるBeanはProfileにより切り替わる。
```java

@Profile("translation")
@Service
public class TranslationServiceImpl implements LineBotService {
```
上記のように、`@Profile`を付けたService(必要ならRepositoryも)を作成する。

## How to boot
**起動に必要なプロパティ**  
propertiesファイルに埋め込むとGithub上でオープンになってしまうため、実行時・環境変数で指定する方式とした。  

| key                    | value                                 | 必須？                |
|------------------------|---------------------------------------|-----------------------|
| CHANNEL_SECRET         | LINEdeveloperのシークレットキー       | required              |
| CHANNEL_TOKEN          | 〃のアクセストークン                  | required              |
| AZURE_SUBSCRIPTION_KEY | Azureのリソース管理画面から取れるキー | optional(translation) |

**指定および起動方法(例)**
```
# export CHANNEL_SECRET=key
# mvn spring-boot:run --spring.profiles.active=translation
```
**herokuへのデプロイ [・参考](https://devcenter.heroku.com/articles/config-vars#setting-up-config-vars-for-a-deployed-application)**  
あらかじめheroku側でAPPを作成し、その環境へ設定の上、pushします。
```
# git clone git@github.com:maruhachi/line-bot-kun.git
# heroku git:remote --app APPNAME
# heroku config:set KEY=VALUE
# git push heroku master
```

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
  - [OAuth token API reference](http://docs.microsofttranslator.com/oauth-token.html#!/Authentication_token_service/getToken)
* TranslationAPIへ上記トークンをつけてRequest
  - [Translation API reference](http://docs.microsofttranslator.com/text-translate.html#!/default/get_Translate)

しかし、ここまでやって得られるResponseは`<string honyarara="url">afterTranslate</string>`とかいう謎XML  
扱いに困り、暫定で正規表現で取り出してる。
