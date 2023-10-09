package swm.s3.coclimb.config.propeties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "aws-config.credentials")
public class AwsCredentialsProperties {
    private final String accessKey;
    private final String secretKey;

    public AwsCredentialsProperties(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
}
