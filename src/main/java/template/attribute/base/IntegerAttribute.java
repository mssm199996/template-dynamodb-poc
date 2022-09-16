package template.attribute.base;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbFlatten;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.attribute.Attribute;
import template.rules.ui.GenericUIRules;
import template.rules.validation.GenericValidationRules;
import template.rules.validation.RangeValidationRules;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = IntegerAttribute.IntegerAttributeBuilder.class)
public class IntegerAttribute implements Attribute {

    private Integer value;

    @Getter(onMethod = @__({ @DynamoDbFlatten }))
    private GenericValidationRules genericValidationRules;

    @Getter(onMethod = @__({ @DynamoDbFlatten }))
    private RangeValidationRules rangeValidationRules;

    @Getter(onMethod = @__({ @DynamoDbFlatten }))
    private GenericUIRules genericUiRules;

    @Override
    @DynamoDbIgnore
    public AttributeType type() {
        return AttributeType.STRING;
    }
}
