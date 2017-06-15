package cf.zunda.LineBot.repository.translation;

import cf.zunda.LineBot.model.TranslatedMessage;
import cf.zunda.LineBot.model.TranslationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ykonno.
 * @since 2017/06/09.
 */
@Component
@Slf4j
@lombok.RequiredArgsConstructor
public class TranslationRestRepositoryImpl implements TranslationRestRepository {

    private final RestOperations restOperations;

    @Value("${azure.translate.Endpoint}")
    private String TRANSLATE_API_ENDPOINT;

    @Override
    public String translate(TranslationMessage translationMessage) {

        TranslationMessage judgedMessage = judgeTranslateTo(translationMessage);

        String encoded = "";
        try {
            encoded = URLEncoder.encode(judgedMessage.getMessage(), "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String query = String.format("appid=Bearer %s&text=%s&to=%s"
                , judgedMessage.getAzureToken().getAuthorizationBearer(), encoded, judgedMessage.getTranslateTo());
        String parameter = "";

        log.info("生のURL : " + TRANSLATE_API_ENDPOINT + query);

        parameter = query.replace("+", "%20").replace(" ", "%20");
        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create(TRANSLATE_API_ENDPOINT + "?" + parameter))
                .build();

        // APIにメッセージを認証情報付きで投げる
        ResponseEntity<String> responseEntity = restOperations.exchange(requestEntity, String.class);

        // TODO:MessageConverterでXMLにしたい。。
        // ResponseEntity<TranslatedMessage> responseEntity = restOperations.exchange(requestEntity, TranslatedMessage.class);

        // ResponseのXMLから翻訳後部分を切り出す
        String regex = ">(.+)<";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(responseEntity.getBody());

        String translated = "";
        if (matcher.find()) {
            translated = matcher.group(1);
        }

        return translated;
    }

    /**
     * どの言語に翻訳するか、元メッセージを元に判定します。
     *
     * @param translationMessage 翻訳対象メッセージ
     * @return 翻訳先言語を設定したメッセージDTO
     */
    private TranslationMessage judgeTranslateTo(TranslationMessage translationMessage) {
        // TODO：翻訳先の言語を判定する。暫定で英語固定
        translationMessage.setTranslateTo(TranslationMessage.TLANSLATE_EN);
        return translationMessage;
    }

}
