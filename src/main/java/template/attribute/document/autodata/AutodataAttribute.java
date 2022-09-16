package template.attribute.document.autodata;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.attribute.base.StringAttribute;
import template.attribute.document.DocumentAttribute;

@Getter
@ToString
@SuperBuilder
@DynamoDbImmutable(builder = AutodataAttribute.AutodataAttributeBuilder.class)
public class AutodataAttribute extends DocumentAttribute {

    private StringAttribute event, template;
    private EventDataAttribute eventData;
}
