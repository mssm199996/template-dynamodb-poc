package template.attribute.document.component;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.attribute.base.StringAttribute;
import template.attribute.document.DocumentAttribute;

@Getter
@ToString
@SuperBuilder
@DynamoDbImmutable(builder = Component.ComponentBuilder.class)
public class Component extends DocumentAttribute {

    private StringAttribute id, templateId;
    private CustomAttributes customAttributes;
}
