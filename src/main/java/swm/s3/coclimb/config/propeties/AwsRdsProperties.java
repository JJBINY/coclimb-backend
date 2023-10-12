package swm.s3.coclimb.config.propeties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Getter
@Profile("release")
@ConfigurationProperties(prefix = "aws-config.rds")
public class AwsRdsProperties {
        private final String secretName;
        private final Region region;
        private final String dbUrl;

        public AwsRdsProperties(String secretName, String region, String dbUrl) {
            this.secretName = secretName;
            this.region = Region.of(region);
            this.dbUrl = dbUrl;
        }

    @Getter
    @NoArgsConstructor
    public class RdsSecret {
        private String username;
        private String password;
    }

    public RdsSecret getSecret() {
        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            // For a list of exceptions thrown, see
            // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
            throw e;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(getSecretValueResponse.secretString(), RdsSecret.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Your code goes here.
    }
}
