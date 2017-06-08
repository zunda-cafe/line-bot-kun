package cf.zunda.LineBot.service.translation;

import cf.zunda.LineBot.model.AzureToken;
import cf.zunda.LineBot.model.TranslationMessage;
import cf.zunda.LineBot.repository.translation.AzureTokenRestRepository;
import cf.zunda.LineBot.repository.translation.AzureTokenRestRepositoryImpl;
import cf.zunda.LineBot.repository.translation.TranslationRestRepository;
import cf.zunda.LineBot.repository.translation.TranslationRestRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ykonno.
 * @since 2017/06/09.
 */
@Service
@Slf4j
@AllArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final AzureTokenRestRepository azureTokenRestRepository;

    private final TranslationRestRepository translationRestRepository;



    @Override
    public String translationMessage(String message) {

        AzureToken azureToken = azureTokenRestRepository.prepareCognitiveToken();

        TranslationMessage translationMessage = TranslationMessage.builder()
                .azureToken(azureToken)
                .Message(message)
                .build();

        String translate = translationRestRepository.translate(translationMessage);

        return null;
    }
}
