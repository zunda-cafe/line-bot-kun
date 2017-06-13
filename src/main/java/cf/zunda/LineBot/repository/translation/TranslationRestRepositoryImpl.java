package cf.zunda.LineBot.repository.translation;

import cf.zunda.LineBot.model.TranslationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.net.URI;

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

        // TODO：翻訳先の言語を判定する。暫定で英語固定
        TranslationMessage judgedMessage = judgeTranslateTo(translationMessage);

        String query = String.format("?text=%s&to=%s", judgedMessage.getMessage(), judgedMessage.getTranslateTo());
        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create(TRANSLATE_API_ENDPOINT + query))
                .header("Authorization", "Baerer : " + judgedMessage.getAzureToken().getAuthorizationBearer())
                .build();

        // APIにメッセージを認証情報付きで投げる
        ResponseEntity<String> responseEntity = restOperations.exchange(requestEntity, String.class);

        // ResponseのXMLをどうにかする
        String translated = responseEntity.getBody();

        return translated;
    }

    /**
     * どの言語に翻訳するか、元メッセージを元に判定します。
     * @param translationMessage 翻訳対象メッセージ
     * @return 翻訳先言語を設定したメッセージDTO
     */
    private TranslationMessage judgeTranslateTo(TranslationMessage translationMessage){
        translationMessage.setTranslateTo(translationMessage.TLANSLATE_EN);
        return translationMessage;
    }

}
