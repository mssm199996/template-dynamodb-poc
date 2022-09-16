package template.rules.validation;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = GenericValidationRules.GenericValidationRulesBuilder.class)
public class GenericValidationRules {

    private Boolean required;
    private Boolean overridable;
}