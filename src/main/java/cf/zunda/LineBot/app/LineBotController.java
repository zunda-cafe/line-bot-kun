package cf.zunda.LineBot.app;

import cf.zunda.LineBot.service.translation.TranslationService;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: LINE プラットフォームから受け取ったメッセージのハンドラ
 * @author ykonno.
 * @since 2017/06/08.
 */
@LineMessageHandler
@Slf4j
@AllArgsConstructor
public class LineBotController {

    private TranslationService translationService;

    /** ただの文字列はここで受ける */
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event){
        log.info("event : " + event);
        return new TextMessage(event.getMessage().getText());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event){
        log.info("event : " + event);
    }

}
