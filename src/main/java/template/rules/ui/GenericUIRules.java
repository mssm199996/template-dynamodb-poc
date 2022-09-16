package template.rules.ui;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = GenericUIRules.GenericUIRulesBuilder.class)
public class GenericUIRules {

    private Boolean hidden;
}
