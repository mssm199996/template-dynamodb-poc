package template.template;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import template.attribute.Attribute;

@Getter
@Builder
@ToString
@DynamoDbImmutable(builder = CollectionTemplateVersion.CollectionTemplateVersionBuilder.class)
public class CollectionTemplateVersion {

    @Getter(onMethod = @__({ @DynamoDbPartitionKey, @DynamoDbAttribute("partitionKey") }))
    private String templateId;

    @Getter(onMethod = @__({ @DynamoDbSortKey, @DynamoDbAttribute("sortKey") }))
    private String sortKey;

    private Integer revision;
    private Long createdAt, publishedAt;
    private String modifiedBy;

    private Attribute<String> title;
    private Attribute<Boolean> async;

    private CollectionTemplateVersionState state;

    public enum CollectionTemplateVersionState {
        PUBLISHED,
        DRAFT
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof CollectionTemplateVersion))
            return false;

        return this.templateId.equals(((CollectionTemplateVersion) o).getTemplateId()) && this.sortKey.equals(((CollectionTemplateVersion) o).getSortKey());
    }
}
