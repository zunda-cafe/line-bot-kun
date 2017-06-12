package cf.zunda.LineBot.service.translation;

import cf.zunda.LineBot.model.AzureToken;
import cf.zunda.LineBot.model.TranslationMessage;
import cf.zunda.LineBot.repository.translation.AzureTokenRestRepository;
import cf.zunda.LineBot.repository.translation.TranslationRestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final AzureTokenRestRepository azureTokenRestRepository;

    private final TranslationRestRepository translationRestRepository;

    @Override
    public String translationMessage(String message) {

        AzureToken azureToken = azureTokenRestRepository.prepareCognitiveToken();

        // CognitiveServiceを使うための認証情報と翻訳対象メッセージをまとめる
        TranslationMessage translationMessage = TranslationMessage.builder()
                .azureToken(azureToken)
                .Message(message)
                .build();

        String translated = translationRestRepository.translate(translationMessage);

        return translated;
    }
}
