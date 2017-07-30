package cf.zunda.LineBot.service.gnavi;

import cf.zunda.LineBot.service.LineBotService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author
 * @since
 */
@Profile("gnavi")
@Service
public class GnaviServiceImpl implements LineBotService {

    @Override
    public String replyMessage(String message) {
        return null;
    }
}
