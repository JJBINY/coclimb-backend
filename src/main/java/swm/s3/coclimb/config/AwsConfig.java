package swm.s3.coclimb.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.StsClientBuilder;
import swm.s3.coclimb.config.propeties.AwsCredentialsProperties;
import swm.s3.coclimb.config.propeties.AwsRdsProperties;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class AwsConfig {

    private final AwsCredentialsProperties credentialsProperties;
    private final AwsRdsProperties rdsProperties;
    @Value("${cloud.aws.region.static}")
    String region;

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(credentialsProperties.getAccessKey(), credentialsProperties.getSecretKey());
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region)
                .build();
    }

    @Bean
    public StsClient StsClient(){
        return StsClient.builder()
                .credentialsProvider(() -> AwsBasicCredentials
                        .create(credentialsProperties.getAccessKey(),
                                credentialsProperties.getSecretKey()))
                .region(Region.of(region))
                .build();
    }

    @Bean
    @Profile("release")
    public DataSource dataSource() {
        AwsRdsProperties.RdsSecret secret = rdsProperties.getSecret();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(rdsProperties.getDbUrl());
        dataSource.setUsername(secret.getUsername());
        dataSource.setPassword(secret.getPassword());
        return dataSource;
    }

}
