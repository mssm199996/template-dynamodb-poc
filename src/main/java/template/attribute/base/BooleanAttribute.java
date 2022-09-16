package template.attribute.base;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbFlatten;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.attribute.Attribute;
import template.rules.ui.GenericUIRules;
import template.rules.validation.GenericValidationRules;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = BooleanAttribute.BooleanAttributeBuilder.class)
public class BooleanAttribute implements Attribute {

    private Boolean value;

    @Getter(onMethod = @__({ @DynamoDbFlatten }))
    private GenericValidationRules genericValidationRules;

    @Getter(onMethod = @__({ @DynamoDbFlatten }))
    private GenericUIRules genericUiRules;

    @Override
    @DynamoDbIgnore
    public AttributeType type() {
        return AttributeType.BOOLEAN;
    }
}