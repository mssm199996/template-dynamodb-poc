package template;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@DynamoDBTable(tableName = "CollectionTemplate")
public class CollectionTemplateVersion {

    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "partitionKey")
    private String templateId;
    private Integer revision;
    private Long createdAt, publishedAt;
    private String modifiedBy;

    @DynamoDBTypeConvertedEnum
    private CollectionTemplateVersionState state;

    @DynamoDBRangeKey(attributeName = "sortKey")
    public String getSortKey() {
        return this.state + "-" + this.revision;
    }

    public enum CollectionTemplateVersionState {
        PUBLISHED,
        DRAFT
    }
}
