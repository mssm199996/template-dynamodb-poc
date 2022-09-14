package template.attribute;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.rules.UIRules;
import template.rules.ValidationRules;
import template.template.CollectionTemplateVersion;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = Attribute.AttributeBuilder.class)
public class Attribute<T> {

    private T value;

    private AttributeType type;

    private ValidationRules<T> validationRules;

    private UIRules uiRules;
}
