package dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import template.template.CollectionTemplateVersion;

import java.util.List;

public class DynamoDBClient {

    public static final String ACCESS_KEY_ID = "local";
    public static final String SECRET_ACCESS_KEY = "local";

    public static final String DYNAMODB_URL = "http://localhost:8000";
    public static final Region REGION = Region.EU_WEST_1;

    // --->

    private static String COLLECTION_TEMPLATE_TABLE_NAME = "CollectionTemplate";

    private DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private TableSchema<CollectionTemplateVersion> collectionTemplateVersionTableSchema;
    private DynamoDbTable<CollectionTemplateVersion> collectionTemplateVersionDynamoDbTable;

    public DynamoDBClient() {
        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.create();

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
                .filterExpression(Expression.builder()
                        .expression("partitionKey = :partitionKey")
                        .putExpressionValue("partitionKey", AttributeValue.builder()
                                .s(partitionKey)
                                .build())
                        .build())
                .build();

        return this.collectionTemplateVersionDynamoDbTable.query(queryEnhancedRequest);
    }
}
