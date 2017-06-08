package cf.zunda.LineBot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Description: Azure Translation APIに必要な情報
 * @author ykonno.
 * @since 2017/06/09.
 */
@AllArgsConstructor
@Data
@Builder
public class TranslationMessage {

    /** Cognitive Service の認証ベアラー */
    private AzureToken azureToken;

    /** TranslationAPIに投げる翻訳対象メッセージ */
    private String Message;
}
