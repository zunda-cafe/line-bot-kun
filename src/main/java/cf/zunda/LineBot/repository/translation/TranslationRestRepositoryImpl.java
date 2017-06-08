package cf.zunda.LineBot.repository.translation;

import cf.zunda.LineBot.model.TranslationMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

/**
 * @author ykonno.
 * @since 2017/06/09.
 */
@Component
@Slf4j
@AllArgsConstructor
public class TranslationRestRepositoryImpl implements TranslationRestRepository {

    private RestOperations restOperations;

    @Override
    public String translate(TranslationMessage translationMessage) {
        return null;
    }
}
