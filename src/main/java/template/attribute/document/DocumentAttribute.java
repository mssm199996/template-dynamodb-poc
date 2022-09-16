package template.attribute.document;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbFlatten;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.attribute.Attribute;
import template.attribute.base.StringAttribute;
import template.rules.ui.GenericUIRules;
import template.rules.validation.GenericValidationRules;

@Getter
@ToString
@SuperBuilder
public class DocumentAttribute implements Attribute {

    @Getter(onMethod = @__({ @DynamoDbFlatten }))
    private GenericValidationRules genericValidationRules;

    @Getter(onMethod = @__({ @DynamoDbFlatten }))
    private GenericUIRules genericUiRules;

    @Override
    @DynamoDbIgnore
    public Attribute.AttributeType type() {
        return Attribute.AttributeType.DOCUMENT;
    }
}
