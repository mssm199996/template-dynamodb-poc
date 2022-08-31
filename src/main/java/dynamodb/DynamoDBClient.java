package dynamodb;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import template.CollectionTemplate;

public class DynamoDBClient {

    private AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    public DynamoDBClient() {
        this.client = this.createAsyncClient();
        this.mapper = new DynamoDBMapper(client);
    }

    public static final String ACCESS_KEY_ID = "local";
    public static final String SECRET_ACCESS_KEY = "local";

    public static final String DYNAMODB_URL = "http://localhost:8000";
    public static final Region REGION = Region.getRegion(Regions.US_WEST_2);

    public <T> void createTable(Class<T> type) {
        CreateTableRequest createTableRequest = this.mapper.generateCreateTableRequest(type);
        createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(25L, 25L));

        this.client.createTable(createTableRequest);
    }

    public <T> void createEntity(T entity) {
        this.mapper.save(entity);
    }

    private AmazonDynamoDB createAsyncClient() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(DYNAMODB_URL, REGION.getName()))
                .build();

        return client;
    }

}
