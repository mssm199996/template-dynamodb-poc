package template.template;

import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;

@Getter
@Builder
@DynamoDbImmutable(builder = CollectionTemplateVersionMetadata.CollectionTemplateVersionMetadataBuilder.class)
public class CollectionTemplateVersionMetadata {

    private String modifiedBy;
}
