package cf.zunda.LineBot.service.translation;

import cf.zunda.LineBot.model.translation.AzureToken;
import cf.zunda.LineBot.model.translation.TranslationMessage;
import cf.zunda.LineBot.repository.translation.AzureTokenRestRepository;
import cf.zunda.LineBot.repository.translation.TranslationRestRepository;
import cf.zunda.LineBot.service.LineBotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Profile("translation")
@Service
@Slf4j
@RequiredArgsConstructor
public class TranslationServiceImpl implements LineBotService {

    private final AzureTokenRestRepository azureTokenRestRepository;

    private final TranslationRestRepository translationRestRepository;

    @Override
    public String replyMessage(String message) {

        AzureToken azureToken = azureTokenRestRepository.prepareCognitiveToken();

        // CognitiveServiceを使うための認証情報と翻訳対象メッセージをまとめる
        TranslationMessage translationMessage = TranslationMessage.builder()
                .azureToken(azureToken)
                .Message(message)
                .build();

        String translated = translationRestRepository.translate(translationMessage);
        translated = "翻訳したよ!" + System.lineSeparator() + translated;

        return translated;
    }
}
