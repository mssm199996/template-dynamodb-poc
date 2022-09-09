package template.attribute;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.Builder;
import lombok.Getter;
import template.rules.UIRules;
import template.rules.ValidationRules;

@Getter
@Builder
@DynamoDBDocument
public class Attribute<T> {

    private T value;

    @DynamoDBTypeConvertedEnum
    private AttributeType type;

    private ValidationRules<T> validationRules;
    private UIRules uiRules;
}
