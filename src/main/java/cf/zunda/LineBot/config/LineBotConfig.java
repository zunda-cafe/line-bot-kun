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

    /** 実行時引数でプロキシを指定
     * ex: --PROXY_WORK=proxy.co.jp */
    @Value("${PROXY_WORK}")
    private String proxyUri;

    @Bean
    @Profile("default")
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /** work環境でプロキシを通す
     * --spring.profiles.active=work で指定 */
    @Bean
    @Profile("work")
    RestTemplate restTemplateOnWork(){
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setProxy(
                new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUri,8080)));
        return new RestTemplate(httpRequestFactory);
    }
}
