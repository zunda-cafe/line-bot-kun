package cf.zunda.LineBot.repository.translation;

import cf.zunda.LineBot.model.AzureToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.net.URI;

/**
 * {@inheritDoc}
 */
@Component
@Slf4j
@lombok.RequiredArgsConstructor
public class AzureTokenRestRepositoryImpl implements AzureTokenRestRepository {

    private final RestOperations restOperations;

    @Value("azure.cognitive.Endpoint")
    private String TOKEN_ENDPOINT;

    @Value("azure.cognitive.SubscriptionKey")
    private String TOKEN_SUBSCRIPTION_KEY;

    @Override
    public AzureToken prepareCognitiveToken(){


        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create(TOKEN_ENDPOINT))
                .header("Ocp-Apim-Subscription-Key", TOKEN_SUBSCRIPTION_KEY)
                .build();

        ResponseEntity<String> responseEntity = restOperations.exchange(requestEntity, String.class);


        AzureToken azureToken = AzureToken.builder()
                .authorizationBearer(responseEntity.getBody()).build();

        return azureToken;
    }
}
