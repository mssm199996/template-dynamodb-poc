package dynamodb;

import cms.CollectionType;
import cms.VideoType;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import template.attribute.base.BooleanAttribute;
import template.attribute.base.IntegerAttribute;
import template.attribute.base.StringAttribute;
import template.attribute.base.StringSetAttribute;
import template.attribute.document.autodata.AutodataAttribute;
import template.attribute.document.autodata.EventDataAttribute;
import template.attribute.document.component.Component;
import template.attribute.document.component.CustomAttributes;
import template.rules.ui.GenericUIRules;
import template.rules.validation.GenericValidationRules;
import template.rules.validation.RangeValidationRules;
import template.template.CollectionTemplateVersion;
import template.template.CollectionTemplateVersionMetadata;

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

        CollectionTemplateVersion collectionTemplateVersion0 = CollectionTemplateVersion.builder()
                .templateId(templateId)
                .sortKey(0)
                .revision(0)
                .createdAt(1000L)
                .publishedAt(2000L)
                .state(CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED)
                .build(),

                collectionTemplateVersion11 = CollectionTemplateVersion.builder()
                        .templateId(templateId)
                        .sortKey(11)
                        .revision(11)
                        .createdAt(3000L)
                        .publishedAt(3500L)
                        .metadata(CollectionTemplateVersionMetadata.builder().modifiedBy("user_1").build())
                        .state(CollectionTemplateVersion.CollectionTemplateVersionState.DRAFT)
                        .build(),

                collectionTemplateVersion2 = CollectionTemplateVersion.builder()
                        .templateId(templateId)
                        .sortKey(2)
                        .revision(2)
                        .createdAt(3000L)
                        .publishedAt(3500L)
                        .metadata(CollectionTemplateVersionMetadata.builder().modifiedBy("user_1").build())
                        .state(CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED)
                        .build(),

                collectionTemplateVersion1 = CollectionTemplateVersion.builder()
                        .templateId(templateId)
                        .sortKey(1)
                        .revision(1)
                        .createdAt(3000L)
                        .publishedAt(3500L)
                        .metadata(CollectionTemplateVersionMetadata.builder().modifiedBy("user_1").build())
                        .state(CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED)
                        .build(),

                collectionTemplateVersionDraft = CollectionTemplateVersion.builder()
                        .templateId(templateId)
                        .sortKey(3)
                        .revision(3)
                        .createdAt(4000L)
                        .publishedAt(7000L)
                        .metadata(CollectionTemplateVersionMetadata.builder().modifiedBy("user_1").build())
                        .state(CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED)
                        .build();

        this.client.createEntity(collectionTemplateVersion11);
        this.client.createEntity(collectionTemplateVersion2);
        this.client.createEntity(collectionTemplateVersion0);
        this.client.createEntity(collectionTemplateVersion1);
        this.client.createEntity(collectionTemplateVersionDraft);

        // --->

        PageIterable<CollectionTemplateVersion> actualCollectionTemplateVersionList = this.client.findAllByPartitionKey(templateId);
        List<CollectionTemplateVersion> expectedCollectionTemplateVersionList = new ArrayList<>(List.of(new CollectionTemplateVersion[] {
                collectionTemplateVersion0,
                collectionTemplateVersion1,
                collectionTemplateVersion2,
                collectionTemplateVersion11,
                collectionTemplateVersionDraft
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
                        .build()
                )
                .async(BooleanAttribute.builder()
                        .value(true)
                        .genericValidationRules(GenericValidationRules.builder()
                                .overridable(false)
                                .required(true)
                                .build()
                        )
                        .genericUiRules(GenericUIRules.builder()
                                .hidden(true)
                                .build()
                        )
                        .build()
                )
                .kind(StringAttribute.builder()
                        .value(CollectionType.AUTOMATIC)
                        .genericValidationRules(GenericValidationRules.builder()
                                .required(true)
                                .overridable(false)
                                .build()
                        )
                        .build()
                )
                .component(Component.builder()
                        .id(StringAttribute.builder()
                                .value("content-grid")
                                .genericValidationRules(GenericValidationRules.builder()
                                        .required(true)
                                        .overridable(false)
                                        .build()
                                )
                                .build())
                        .templateId(StringAttribute.builder()
                                .value("extended-primary")
                                .genericValidationRules(GenericValidationRules.builder()
                                        .overridable(false)
                                        .required(true)
                                        .build())
                                .build()
                        )
                        .customAttributes(CustomAttributes.builder()
                                .broadcastTile(BooleanAttribute.builder()
                                        .value(true)
                                        .genericValidationRules(GenericValidationRules.builder()
                                                .overridable(false)
                                                .required(true)
                                                .build()
                                        )
                                        .genericUiRules(GenericUIRules.builder()
                                                .hidden(true)
                                                .build()
                                        ).build())
                                .shouldRefresh(BooleanAttribute.builder()
                                        .value(true)
                                        .genericValidationRules(GenericValidationRules.builder()
                                                .overridable(false)
                                                .required(true)
                                                .build()
                                        )
                                        .genericUiRules(GenericUIRules.builder()
                                                .hidden(true)
                                                .build()
                                        ).build())
                                .build()
                        )
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
                                .build()
                        )
                        .event(StringAttribute.builder()
                                .value("content.videos.playbackhistory")
                                .genericValidationRules(GenericValidationRules.builder()
                                        .overridable(false)
                                        .required(true)
                                        .build()
                                )
                                .build()
                        )
                        .eventData(EventDataAttribute.builder()
                                .videoTypeFilter(StringSetAttribute.builder()
                                        .value(Set.of(VideoType.EPISODE, VideoType.STANDALONE))
                                        .genericValidationRules(GenericValidationRules.builder()
                                                .overridable(false)
                                                .required(true)
                                                .build()
                                        )
                                        .build()
                                )
                                .taxonomyNodeIdFilter(StringAttribute.builder()
                                        .value("taxonomy1+taxonomy2+!taxonomy3")
                                        .genericValidationRules(GenericValidationRules.builder()
                                                .required(true)
                                                .overridable(true)
                                                .build()
                                        ).build()
                                )
                                .viewingHistoryViewedFilter(BooleanAttribute.builder()
                                        .value(true)
                                        .genericValidationRules(GenericValidationRules.builder()
                                                .required(true)
                                                .overridable(false)
                                                .build()
                                        )
                                        .build()
                                )
                                .pageSize(IntegerAttribute.builder()
                                        .value(10)
                                        .rangeValidationRules(RangeValidationRules.builder()
                                                .min(2)
                                                .max(20)
                                                .build()
                                        ).build()
                                )
                                .suggestNextEpisodeForCompleted(BooleanAttribute.builder()
                                        .value(true)
                                        .genericValidationRules(GenericValidationRules.builder()
                                                .required(true)
                                                .overridable(false)
                                                .build()
                                        )
                                        .genericUiRules(GenericUIRules.builder()
                                                .hidden(true)
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

    /*@Test
    public void saveLatestEpisodes() {
        this.deleteTable();
        this.createTable();

        this.createDraftCollectionTemplateVersion()
                .title(StringAttribute.builder()
                        .value("Latest episodes")
                        .genericValidationRules(GenericValidationRules.builder()
                                .overridable(false)
                                .required(true)
                                .build()
                        )
                        .build()
                )
                .kind(StringAttribute.builder()
                        .value(CollectionType.AUTOMATIC)
                        .genericValidationRules(GenericValidationRules.builder()
                                .required(true)
                                .overridable(false)
                                .build()
                        )
                        .allowedValues(Set.of(CollectionType.AUTOMATIC, CollectionType.MANUAL, CollectionType.MERGED))
                        .build()
                )
                .build();

        // TODO: to be continued
    }*/

   /*@Test
    public void saveJustAdded() {
        this.deleteTable();
        this.createTable();

        this.createDraftCollectionTemplateVersion()
                .title(StringAttribute.builder()
                        .value("Just Added")
                        .genericValidationRules(GenericValidationRules.builder()
                                .overridable(false)
                                .required(true)
                                .build()
                        )
                        .build()
                )
                .kind(StringAttribute.builder()
                        .value(CollectionType.AUTOMATIC)
                        .genericValidationRules(GenericValidationRules.builder()
                                .required(true)
                                .overridable(false)
                                .build()
                        )
                        .allowedValues(Set.of(CollectionType.AUTOMATIC, CollectionType.MANUAL, CollectionType.MERGED))
                        .build()
                )
                .autodata(AutodataAttribute.builder()
                        .
                .template(StringAttribute.builder()
                        .value("content-grid")
                        .build()
                ).build())
                .build();
    }*/

    private CollectionTemplateVersion.CollectionTemplateVersionBuilder createDraftCollectionTemplateVersion() {
        String templateId = UUID.randomUUID().toString();

        return CollectionTemplateVersion.builder()
                .templateId(templateId)
                .sortKey(0)
                .revision(0)
                .state(CollectionTemplateVersion.CollectionTemplateVersionState.DRAFT)
                .createdAt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                .publishedAt(null)
                .metadata(CollectionTemplateVersionMetadata.builder().modifiedBy("unknown_user").build());
    }
}
