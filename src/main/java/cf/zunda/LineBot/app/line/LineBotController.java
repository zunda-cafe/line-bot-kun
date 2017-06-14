package cf.zunda.LineBot.app.line;

import cf.zunda.LineBot.service.translation.TranslationService;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: LINE プラットフォームから受け取ったメッセージのハンドラ
 * @author ykonno.
 * @since 2017/06/08.
 */
@LineMessageHandler
@Slf4j
@RequiredArgsConstructor
public class LineBotController {

    private final TranslationService translationService;

    /** テキストメッセージはここで受ける */
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event){
        log.info("event : " + event);

        // LINEで受け取ったテキストメッセージを翻訳して返す
        String translated = translationService.translationMessage(event.getMessage().getText());
        return new TextMessage("翻訳したよ!" + System.lineSeparator() + translated);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event){
        log.info("event : " + event);
    }

}
