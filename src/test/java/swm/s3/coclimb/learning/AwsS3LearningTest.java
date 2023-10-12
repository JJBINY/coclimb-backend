package swm.s3.coclimb.learning;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.Credentials;
import swm.s3.coclimb.api.IntegrationTestSupport;
import swm.s3.coclimb.api.exception.errortype.aws.S3UploadFail;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class AwsS3LearningTest extends IntegrationTestSupport {

    private final String ROLE_ARN = "arn:aws:iam::085796513949:role/CoclimbS3Access";
    private final String ROLE_ARN2 = "arn:aws:iam::085796513949:role/CoclimbS3ReadOnly";

    @Autowired
    StsClient stsClient;

    @Test
    @DisplayName("AWS Security Token Service Test")
    void getTempAuth() throws Exception {
        // given
        String bucket = "coclimb-media-bucket";
        AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder()
                .roleArn(ROLE_ARN)
                .roleSessionName("temp-upload-session")
                .durationSeconds(900) //최소 15분이어야함
                .build();
        Credentials credentials = stsClient.assumeRole(assumeRoleRequest).credentials();

        AssumeRoleRequest assumeRole2Request = AssumeRoleRequest.builder()
                .roleArn(ROLE_ARN2)
                .roleSessionName("temp-read-session")
                .durationSeconds(900) //최소 15분이어야함
                .build();
        Credentials credentials2 = stsClient.assumeRole(assumeRole2Request).credentials();

        // when
        uploadToS3(bucket, credentials);

        // then
        String sut = downloadFromS3(bucket, credentials2);
        Assertions.assertThat(sut).isEqualTo("test");
    }

    private void uploadToS3(String bucket, Credentials credentials) {

        BasicSessionCredentials awsCredentials = new BasicSessionCredentials(credentials.accessKeyId(), credentials.secretAccessKey(), credentials.sessionToken());
        AmazonS3Client amazonS3Client = (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion("ap-northeast-2")
                .build();

        try {
            amazonS3Client.putObject(bucket, "coclimblearningtestfile", "test");
        } catch (Exception e) {
            throw new S3UploadFail();
        }
    }

    private String downloadFromS3(String bucket, Credentials credentials) {

            BasicSessionCredentials awsCredentials = new BasicSessionCredentials(credentials.accessKeyId(), credentials.secretAccessKey(), credentials.sessionToken());
            AmazonS3Client amazonS3Client = (AmazonS3Client) AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .withRegion("ap-northeast-2")
                    .build();

            // 파일 다운로드
        S3Object file = amazonS3Client.getObject(bucket, "coclimblearningtestfile");

        return convertStreamToString(file.getObjectContent());
    }

    private static String convertStreamToString(java.io.InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error while converting InputStream to String", e);
        }
    }
}
