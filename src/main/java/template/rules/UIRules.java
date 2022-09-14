package template.rules;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.attribute.Attribute;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = UIRules.UIRulesBuilder.class)
public class UIRules {

    private Boolean hidden;
}
