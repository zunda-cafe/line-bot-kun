package cf.zunda.LineBot.service;

/**
 * @author ykonno
 * @since 2017/06/08.
 */
public interface LineBotService {

    /**
     * 単純な文字列のみのメッセージを処理する
     * @param message LINEプラットフォームから送られる文字列
     * @return 返信する文字列
     */
    String replyMessage(String message);
}
