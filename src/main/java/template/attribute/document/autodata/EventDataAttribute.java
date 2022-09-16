package template.attribute.document.autodata;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import template.attribute.base.BooleanAttribute;
import template.attribute.base.IntegerAttribute;
import template.attribute.base.StringSetAttribute;
import template.attribute.document.DocumentAttribute;

@Getter
@ToString
@SuperBuilder
@DynamoDbImmutable(builder = EventDataAttribute.EventDataAttributeBuilder.class)
public class EventDataAttribute extends DocumentAttribute {

    @Getter(onMethod = @__({ @DynamoDbAttribute("filter[videoType]") }))
    private StringSetAttribute videoTypeFilter;

    private BooleanAttribute suggestNextEpisodeForCompleted;

    @Getter(onMethod = @__({ @DynamoDbAttribute("filter[viewingHistory.viewed]") }))
    private BooleanAttribute viewingHistoryViewed;

    @Getter(onMethod = @__({ @DynamoDbAttribute("page[size]") }))
    private IntegerAttribute pageSize;
}
