package cf.zunda.LineBot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: Azure CognitiveServiceの認証情報
 * @author ykonno.
 * @since 2017/06/09.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AzureToken {

    /** 認証結果として返されるベアラー */
    private String authorizationBearer;


}
