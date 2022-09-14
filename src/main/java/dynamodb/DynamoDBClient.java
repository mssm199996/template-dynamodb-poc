package dynamodb;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import template.template.CollectionTemplateVersion;

import java.net.URI;

public class DynamoDBClient {

    public static final String ACCESS_KEY_ID = "local";
    public static final String SECRET_ACCESS_KEY = "local";

    public static final String DYNAMODB_URL = "http://localhost:8000";
    public static final Region REGION = Region.US_WEST_2;

    // --->

    private static String COLLECTION_TEMPLATE_TABLE_NAME = "CollectionTemplate2";

    private DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private TableSchema<CollectionTemplateVersion> collectionTemplateVersionTableSchema;
    private DynamoDbTable<CollectionTemplateVersion> collectionTemplateVersionDynamoDbTable;

    public DynamoDBClient() {
        DynamoDbClient ddb = DynamoDbClient.builder()
                .region(REGION)
                .endpointOverride(URI.create(DYNAMODB_URL))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ACCESS_KEY_ID, SECRET_ACCESS_KEY)
                ))
                .build();

        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(ddb).build();

        this.collectionTemplateVersionTableSchema = TableSchema.fromImmutableClass(CollectionTemplateVersion.class);
        this.collectionTemplateVersionDynamoDbTable = this.dynamoDbEnhancedClient.table(COLLECTION_TEMPLATE_TABLE_NAME, collectionTemplateVersionTableSchema);
    }

    public void createTable() {
        this.collectionTemplateVersionDynamoDbTable.createTable();
    }

    public void deleteTable() {
        this.collectionTemplateVersionDynamoDbTable.deleteTable();
    }

    public void createEntity(CollectionTemplateVersion entity) {
        this.collectionTemplateVersionDynamoDbTable.putItem(entity);
    }

    public CollectionTemplateVersion findByPartitionKeyAndSortKey(String partitionKey, String sortKey) {
        return this.collectionTemplateVersionDynamoDbTable.getItem(Key.builder()
                .partitionValue(partitionKey)
                .sortValue(sortKey)
                .build()
        );
    }

    public PageIterable<CollectionTemplateVersion> findAllByPartitionKey(String partitionKey) {
        QueryEnhancedRequest queryEnhancedRequest = QueryEnhancedRequest.builder()
                .queryConditional(
                        QueryConditional.keyEqualTo(Key.builder().partitionValue(partitionKey).build())
                )
                .build();

        return this.collectionTemplateVersionDynamoDbTable.query(queryEnhancedRequest);
    }
}
