package template.template;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;

@Getter
@DynamoDBTable(tableName = "CollectionTemplate")
public class CollectionTemplateVersionDefinition extends CollectionTemplateVersion {

    @DynamoDBRangeKey(attributeName = "sortKey")
    private final String sortKey = "DRAFT";
}
