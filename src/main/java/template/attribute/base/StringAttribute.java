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

import java.util.Set;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = StringAttribute.StringAttributeBuilder.class)
public class StringAttribute implements Attribute {

    private String value;
    private Set<String> allowedValues;

    @Getter(onMethod = @__({ @DynamoDbFlatten }))
    private GenericValidationRules genericValidationRules;

    @Getter(onMethod = @__({ @DynamoDbFlatten }))
    private GenericUIRules genericUiRules;

    @Override
    @DynamoDbIgnore
    // We don't need to save this to DynamoDB since we'll get it correctly and we can send it to the studio
    // Saving this will cause an issue (Need a setter because of @DynamoDBImmutable)
    public AttributeType type() {
        return AttributeType.STRING;
    }
}
