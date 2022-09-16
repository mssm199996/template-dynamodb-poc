package template.rules.validation;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.rules.ContentType;

import java.util.List;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = ArrayValidationRules.ArrayValidationRulesBuilder.class)
public class ArrayValidationRules<T> {

    private Integer minSize, maxSize;
    private List<ContentType> allowedContentTypes, disallowedContentTypes;
}
