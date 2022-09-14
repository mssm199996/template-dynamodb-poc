package template.attribute;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.DocumentAttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import template.rules.UIRules;
import template.rules.ValidationRules;
import template.template.CollectionTemplateVersion;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = Attribute.AttributeBuilder.class)
public class Attribute<T> {

    //@Getter(onMethod = @__({ @DynamoDbConvertedBy(value = DocumentAttributeConverter.class) }))
    //private T value;

    private AttributeType type;

    //private ValidationRules<T> validationRules;

    private UIRules uiRules;
}
