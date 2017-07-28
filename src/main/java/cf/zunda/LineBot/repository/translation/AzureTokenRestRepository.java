package cf.zunda.LineBot.repository.translation;

import cf.zunda.LineBot.model.translation.AzureToken;

/**
 * Description: Azure Cognitive Service のWebService.
 * @author ykonno.
 * @since 2017/06/09.
 */
public interface AzureTokenRestRepository {

    /** CognitiveServiceの使用のため認証情報を取得 */
    public AzureToken prepareCognitiveToken();
}
