package template.attribute;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import lombok.Builder;
import lombok.Getter;
import template.rules.UIRules;
import template.rules.ValidationRules;

@Getter
@Builder
@DynamoDBDocument
public class Attribute<T> {

    @DynamoDBIgnore
    private T value;

    @DynamoDBTypeConvertedEnum
    private AttributeType type;

    @DynamoDBIgnore
    private ValidationRules<T> validationRules;

    @DynamoDBIgnore
    private UIRules uiRules;
}
