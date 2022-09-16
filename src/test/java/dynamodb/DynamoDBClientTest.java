package dynamodb;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import template.attribute.base.BooleanAttribute;
import template.attribute.base.IntegerAttribute;
import template.attribute.base.StringAttribute;
import template.attribute.base.StringSetAttribute;
import template.attribute.document.autodata.AutodataAttribute;
import template.attribute.document.autodata.EventDataAttribute;
import template.rules.ui.GenericUIRules;
import template.rules.validation.GenericValidationRules;
import template.rules.validation.RangeValidationRules;
import template.template.CollectionTemplateVersion;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DynamoDBClientTest {

    private final DynamoDBClient client = new DynamoDBClient();

    @Test
    public void createTable() {
        this.client.createTable();
    }

    @Test
    public void deleteTable() {
        this.client.deleteTable();
    }

    @Test
    public void saveCollectionTemplate() {
        this.deleteTable();
        this.createTable();

        String templateId = UUID.randomUUID().toString();

        CollectionTemplateVersion collectionTemplateVersion1 = CollectionTemplateVersion.builder()
                .templateId(templateId)
                .sortKey("0")
                .revision(0)
                .createdAt(1000L)
                .publishedAt(2000L)
                .modifiedBy("user_1")
                .title(StringAttribute.builder()
                        .value("Continue watching")
                        .allowedValues(Set.of("V1", "V3"))
                        .genericUiRules(GenericUIRules
                                .builder()
                                .hidden(false)
                                .build()
                        )
                        .genericValidationRules(GenericValidationRules.<String>
                                        builder()
                                .required(true)
                                .overridable(false)
                                .build()
                        )
                        .build()
                )
                .async(BooleanAttribute.builder()
                        .value(false)
                        .build()
                )
                .state(CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED)
                .build(),

                collectionTemplateVersion2 = CollectionTemplateVersion.builder()
                        .templateId(templateId)
                        .sortKey("1")
                        .revision(1)
                        .createdAt(3000L)
                        .publishedAt(3500L)
                        .modifiedBy("user_1")
                        /*.title(Attribute.<String>builder()
                                .type(AttributeType.STRING)
                                //.value("Collection Template V2")
                                .uiRules(UIRules
                                        .builder()
                                        .hidden(false)
                                        .build()
                                )
                                /*.validationRules(ValidationRules.<String>
                                                builder()
                                        .required(true)
                                        .overridable(false)
                                        .allowedValues(Set.of("V1", "V2", "V3"))
                                        .build()
                                )*/
                        //.build()
                        //)
                        /*.async(Attribute.<Boolean>builder()
                                .type(AttributeType.BOOLEAN)
                                //.value(true)
                                .build()
                        )*/
                        .state(CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED)
                        .build(),

                collectionTemplateVersion3 = CollectionTemplateVersion.builder()
                        .templateId(templateId)
                        .sortKey("DRAFT")
                        .revision(2)
                        .createdAt(4000L)
                        .publishedAt(7000L)
                        .modifiedBy("user_1")
                        /*.title(Attribute.<String>builder()
                                .type(AttributeType.STRING)
                                //.value("Collection Template V3")
                                .uiRules(UIRules
                                        .builder()
                                        .hidden(true)
                                        .build()
                                )
                                /*.validationRules(ValidationRules.<String>
                                                builder()
                                        .required(true)
                                        .overridable(false)
                                        .allowedValues(Set.of("V1", "V2", "V3", "V4"))
                                        .build()
                                )*/
                        //.build()
                        //)
                        /*.async(Attribute.<Boolean>builder()
                                .type(AttributeType.BOOLEAN)
                                //.value(false)
                                .build()
                        )*/
                        .state(CollectionTemplateVersion.CollectionTemplateVersionState.DRAFT)
                        .build();

        this.client.createEntity(collectionTemplateVersion1);
        this.client.createEntity(collectionTemplateVersion2);
        this.client.createEntity(collectionTemplateVersion3);

        // --->

        PageIterable<CollectionTemplateVersion> actualCollectionTemplateVersionList = this.client.findAllByPartitionKey(templateId);
        List<CollectionTemplateVersion> expectedCollectionTemplateVersionList = new ArrayList<>(List.of(new CollectionTemplateVersion[] {
                collectionTemplateVersion1,
                collectionTemplateVersion2,
                collectionTemplateVersion3
        }));
        expectedCollectionTemplateVersionList.sort(Comparator.comparing(CollectionTemplateVersion::getSortKey));

        Assertions.assertIterableEquals(expectedCollectionTemplateVersionList, actualCollectionTemplateVersionList.items());

        CollectionTemplateVersion collectionTemplateVersion1Bis = this.client.findByPartitionKeyAndSortKey(collectionTemplateVersion1.getTemplateId(), collectionTemplateVersion1.getSortKey());

        Assertions.assertEquals(collectionTemplateVersion1, collectionTemplateVersion1Bis);
    }

    @Test
    public void saveContinueWatching() {
        this.deleteTable();
        this.createTable();

        CollectionTemplateVersion continueWatchingCollectionTemplate = this.createDraftCollectionTemplateVersion()
                .title(StringAttribute.builder()
                        .value("Continue watching")
                        .genericValidationRules(GenericValidationRules.builder()
                                .required(true)
                                .overridable(false)
                                .build()
                        )
                        .genericUiRules(GenericUIRules.builder()
                                .hidden(false)
                                .build()
                        )
                        .build()
                )
                .broadcastTitle(BooleanAttribute.builder()
                        .value(true)
                        .genericValidationRules(GenericValidationRules.builder()
                                .overridable(false)
                                .required(true)
                                .build()
                        )
                        .genericUiRules(GenericUIRules.builder()
                                .hidden(false)
                                .build()
                        ).build()
                )
                .shouldRefreshOnPageReappearance(BooleanAttribute.builder()
                        .value(true)
                        .genericValidationRules(GenericValidationRules.builder()
                                .overridable(false)
                                .required(true)
                                .build()
                        )
                        .genericUiRules(GenericUIRules.builder()
                                .hidden(false)
                                .build()
                        ).build()
                )
                .kind(StringAttribute.builder()
                        .value("automatic")
                        .genericUiRules(GenericUIRules.builder()
                                .hidden(false)
                                .build()
                        )
                        .genericValidationRules(GenericValidationRules.builder()
                                .required(true)
                                .overridable(false)
                                .build()
                        )
                        .allowedValues(Set.of("automatic", "manual", "merged"))
                        .build()
                )
                .autodata(AutodataAttribute.builder()
                        .template(StringAttribute.builder()
                                .value("continueWatching")
                                .genericValidationRules(GenericValidationRules.builder()
                                        .overridable(false)
                                        .required(true)
                                        .build()
                                )
                                .genericUiRules(GenericUIRules.builder()
                                        .hidden(false)
                                        .build()
                                )
                                .build()
                        )
                        .event(StringAttribute.builder()
                                .value("content.videos.playbackhistory")
                                .genericValidationRules(GenericValidationRules.builder()
                                        .overridable(false)
                                        .required(true)
                                        .build()
                                )
                                .genericUiRules(GenericUIRules.builder()
                                        .hidden(false)
                                        .build()
                                )
                                .build()
                        )
                        .eventData(EventDataAttribute.builder()
                                .pageSize(IntegerAttribute.builder()
                                        .value(10)
                                        .rangeValidationRules(RangeValidationRules.builder()
                                                .min(2)
                                                .max(20)
                                                .build()
                                        ).build()
                                )
                                .viewingHistoryViewed(BooleanAttribute.builder()
                                        .value(true)
                                        .genericValidationRules(GenericValidationRules.builder()
                                                .required(true)
                                                .overridable(false)
                                                .build()
                                        )
                                        .genericUiRules(GenericUIRules.builder()
                                                .hidden(false)
                                                .build()
                                        )
                                        .build()
                                )
                                .suggestNextEpisodeForCompleted(BooleanAttribute.builder()
                                        .value(true)
                                        .genericValidationRules(GenericValidationRules.builder()
                                                .required(true)
                                                .overridable(false)
                                                .build()
                                        )
                                        .genericUiRules(GenericUIRules.builder()
                                                .hidden(false)
                                                .build()
                                        )
                                        .build()
                                )
                                .videoTypeFilter(StringSetAttribute.builder()
                                        .value(Set.of("EPISODE", "STANDALONE"))
                                        .genericValidationRules(GenericValidationRules.builder()
                                                .overridable(false)
                                                .required(true)
                                                .build()
                                        )
                                        .genericUiRules(GenericUIRules.builder()
                                                .hidden(false)
                                                .build()
                                        )
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build();
        this.client.createEntity(continueWatchingCollectionTemplate);
    }

    private CollectionTemplateVersion.CollectionTemplateVersionBuilder createDraftCollectionTemplateVersion() {
        String templateId = UUID.randomUUID().toString();

        CollectionTemplateVersion.CollectionTemplateVersionBuilder collectionTemplateVersionBuilder = CollectionTemplateVersion.builder()
                .templateId(templateId)
                .sortKey(CollectionTemplateVersion.CollectionTemplateVersionState.DRAFT.name())
                .revision(0)
                .state(CollectionTemplateVersion.CollectionTemplateVersionState.DRAFT)
                .createdAt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .publishedAt(null)
                .modifiedBy("unknown-user");

        return collectionTemplateVersionBuilder;
    }
}
