package template.attribute.document.component;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.attribute.base.BooleanAttribute;

@Getter
@ToString
@SuperBuilder
@DynamoDbImmutable(builder = CustomAttributes.CustomAttributesBuilder.class)
public class CustomAttributes {

    private BooleanAttribute broadcastTile, shouldRefresh;
}
