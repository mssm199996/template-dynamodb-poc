package dynamodb;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import template.attribute.Attribute;
import template.attribute.AttributeType;
import template.rules.UIRules;
import template.rules.ValidationRules;
import template.template.CollectionTemplateVersion;

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
                .revision(0)
                .createdAt(1000L)
                .publishedAt(2000L)
                .modifiedBy("user_1")
                .title(Attribute.<String>builder()
                        .type(AttributeType.STRING)
                        .value("Collection Template V1")
                        .uiRules(UIRules
                                .builder()
                                .hidden(false)
                                .build()
                        )
                        .validationRules(ValidationRules.<String>
                                        builder()
                                .required(true)
                                .overridable(false)
                                .allowedValues(Set.of("V1", "V3"))
                                .build()
                        )
                        .build()
                )
                .async(Attribute.<Boolean>builder()
                        .type(AttributeType.BOOLEAN)
                        .value(false)
                        .build()
                )
                .state(CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED)
                .build(),

                collectionTemplateVersion2 = CollectionTemplateVersion.builder()
                        .templateId(templateId)
                        .revision(1)
                        .createdAt(3000L)
                        .publishedAt(3500L)
                        .modifiedBy("user_1")
                        .title(Attribute.<String>builder()
                                .type(AttributeType.STRING)
                                .value("Collection Template V2")
                                .uiRules(UIRules
                                        .builder()
                                        .hidden(false)
                                        .build()
                                )
                                .validationRules(ValidationRules.<String>
                                                builder()
                                        .required(true)
                                        .overridable(false)
                                        .allowedValues(Set.of("V1", "V2", "V3"))
                                        .build()
                                )
                                .build()
                        )
                        .async(Attribute.<Boolean>builder()
                                .type(AttributeType.BOOLEAN)
                                .value(true)
                                .build()
                        )
                        .state(CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED)
                        .build(),

                collectionTemplateVersion3 = CollectionTemplateVersion.builder()
                        .templateId(templateId)
                        .revision(2)
                        .createdAt(4000L)
                        .publishedAt(7000L)
                        .modifiedBy("user_1")
                        .title(Attribute.<String>builder()
                                .type(AttributeType.STRING)
                                .value("Collection Template V3")
                                .uiRules(UIRules
                                        .builder()
                                        .hidden(true)
                                        .build()
                                )
                                .validationRules(ValidationRules.<String>
                                                builder()
                                        .required(true)
                                        .overridable(false)
                                        .allowedValues(Set.of("V1", "V2", "V3", "V4"))
                                        .build()
                                )
                                .build()
                        )
                        .async(Attribute.<Boolean>builder()
                                .type(AttributeType.BOOLEAN)
                                .value(false)
                                .build()
                        )
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

        Assertions.assertIterableEquals(expectedCollectionTemplateVersionList, actualCollectionTemplateVersionList);

        CollectionTemplateVersion collectionTemplateVersion1Bis = this.client.findByPartitionKeyAndSortKey(collectionTemplateVersion1.getTemplateId(), collectionTemplateVersion1.getSortKey());

        Assertions.assertEquals(collectionTemplateVersion1, collectionTemplateVersion1Bis);
    }
}
