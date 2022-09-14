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
@Builder(access = AccessLevel.PUBLIC)
@ToString
@DynamoDbImmutable(builder = CollectionTemplateVersion.CollectionTemplateVersionBuilder.class)
public class CollectionTemplateVersion {

    @Getter(onMethod = @__({ @DynamoDbPartitionKey, @DynamoDbAttribute("partitionKey") }))
    private String templateId;

    private Integer revision;
    private Long createdAt, publishedAt;
    private String modifiedBy;

    private Attribute<String> title;
    private Attribute<Boolean> async;

    private CollectionTemplateVersionState state;

    @DynamoDbSortKey
    @DynamoDbAttribute("sortKey")
    public String getSortKey() {
        return switch (state) {
            case DRAFT -> CollectionTemplateVersionState.DRAFT.name();
            case PUBLISHED -> Integer.toString(revision);
        };
    }

    public enum CollectionTemplateVersionState {
        PUBLISHED,
        DRAFT
    }
}
