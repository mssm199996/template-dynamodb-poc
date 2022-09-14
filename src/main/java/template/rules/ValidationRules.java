package template.rules;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.attribute.Attribute;

import java.util.Set;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = ValidationRules.ValidationRulesBuilder.class)
public class ValidationRules<T> {

    private Boolean required;
    private Boolean overridable;

    private Set<T> allowedValues;
}