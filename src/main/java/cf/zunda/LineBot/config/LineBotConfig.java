package cf.zunda.LineBot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Description: Bean定義等 Spring的な設定クラス
 * @author ykonno
 * @since 2017/06/08.
 */
@Configuration
public class LineBotConfig {

    /** 必要なら実行時にプロキシを指定
     * ex: --PROXY_WORK=proxy.co.jp */
    @Value("${PROXY_WORK:none}")
    private String proxyUri;

    /** プロキシのポート
     * デフォルト：8080 */
    @Value("${PROXY_PORT:8080}")
    private String proxyPort;

    @Profile("!work")
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /** work環境でプロキシを通す
     * --spring.profiles.active=work で有効化 */
    @Profile("work")
    @Bean
    RestTemplate restTemplateOnWork(){
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setProxy(
                new Proxy(Proxy.Type.HTTP
                        , new InetSocketAddress(proxyUri,Integer.getInteger(proxyPort))));
        return new RestTemplate(httpRequestFactory);
    }
}
