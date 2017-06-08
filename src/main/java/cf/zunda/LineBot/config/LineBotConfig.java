package cf.zunda.LineBot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Description: Bean定義等 Spring的な設定クラス
 * @author ykonno
 * @since 2017/06/08.
 */
@Configuration
public class LineBotConfig {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
