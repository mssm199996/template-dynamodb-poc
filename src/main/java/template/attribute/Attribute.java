package template.attribute;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import lombok.Builder;
import lombok.Getter;
import template.rules.UIRules;
import template.rules.ValidationRules;

@Getter
@Builder
@DynamoDBDocument
public class Attribute<T> {

    @DynamoDBTypeConvertedJson
    private T value;

    @DynamoDBTypeConvertedEnum
    private AttributeType type;

    private ValidationRules<T> validationRules;

    private UIRules uiRules;
}
