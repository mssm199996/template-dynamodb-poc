package template.template;

import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import template.attribute.base.BooleanAttribute;
import template.attribute.base.StringAttribute;
import template.attribute.document.autodata.AutodataAttribute;
import template.attribute.document.component.Component;

@Getter
@Builder
@DynamoDbImmutable(builder = CollectionTemplateVersion.CollectionTemplateVersionBuilder.class)
public class CollectionTemplateVersion {

    @Getter(onMethod = @__({ @DynamoDbPartitionKey, @DynamoDbAttribute("partitionKey") }))
    private String templateId;

    @Getter(onMethod = @__({ @DynamoDbSortKey, @DynamoDbAttribute("sortKey") }))
    private Integer sortKey;

    private Integer revision;
    private Long createdAt, publishedAt;

    @Getter(onMethod = @__({ @DynamoDbAttribute("meta") }))
    private CollectionTemplateVersionMetadata metadata;

    private StringAttribute title, kind;
    private BooleanAttribute async;
    private Component component;
    private AutodataAttribute autodata;

    private CollectionTemplateVersionState state;

    public enum CollectionTemplateVersionState {
        PUBLISHED,
        DRAFT
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CollectionTemplateVersion)) {
            return false;
        }

        return this.templateId.equals(((CollectionTemplateVersion) o).getTemplateId()) && this.sortKey.equals(((CollectionTemplateVersion) o).getSortKey());
    }
}
