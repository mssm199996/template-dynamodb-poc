package template.rules.validation;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = RangeValidationRules.RangeValidationRulesBuilder.class)
public class RangeValidationRules {

    private Integer min, max;
}
