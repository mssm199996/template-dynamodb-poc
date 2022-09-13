package dynamodb;

import org.junit.Test;
import template.attribute.Attribute;
import template.attribute.AttributeType;
import template.rules.UIRules;
import template.rules.ValidationRules;
import template.template.CollectionTemplateVersion;
import template.template.CollectionTemplateVersionDefinition;

import java.util.Set;
import java.util.UUID;

public class DynamoDBClientTest {

    private DynamoDBClient client = new DynamoDBClient();

    @Test
    public void createTable() {
        this.client.createTable(CollectionTemplateVersionDefinition.class);
    }

    @Test
    public void deleteTable() {
        this.client.deleteTable(CollectionTemplateVersionDefinition.class);
    }

    @Test
    public void saveCollectionTemplate() {
        String templateId = UUID.randomUUID().toString();

        CollectionTemplateVersion collectionTemplateVersion1 = new CollectionTemplateVersion(
                templateId, 0, 1000L, 2000L, "user_1",
                Attribute
                        .<String>builder()
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
                        .build(),
                Attribute.<Boolean>builder().type(AttributeType.BOOLEAN).value(false).build(),
                CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED

        ), collectionTemplateVersion2 = new CollectionTemplateVersion(
                templateId, 1, 3000L, 3500L, "user_1",
                Attribute
                        .<String>builder()
                        .type(AttributeType.STRING)
                        .value("Collection Template V2")
                        .uiRules(UIRules
                                .builder()
                                .hidden(true)
                                .build()
                        )
                        .validationRules(ValidationRules
                                .<String>builder()
                                .required(true)
                                .overridable(false)
                                .allowedValues(Set.of("V1", "V2", "V3"))
                                .build()
                        )
                        .build(),
                Attribute
                        .<Boolean>builder()
                        .type(AttributeType.BOOLEAN)
                        .value(true)
                        .build(),
                CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED
        ), collectionTemplateVersion3 = new CollectionTemplateVersion(
                templateId, 2, 4000L, 7000L, "user_1",
                Attribute
                        .<String>builder()
                        .type(AttributeType.STRING)
                        .value("Collection Template V3")
                        .uiRules(UIRules
                                .builder()
                                .hidden(true)
                                .build()
                        )
                        .validationRules(ValidationRules
                                .<String>builder()
                                .required(true)
                                .overridable(false)
                                .allowedValues(Set.of("V1", "V2", "V3", "V4"))
                                .build()
                        )
                        .build(),
                Attribute
                        .<Boolean>builder()
                        .type(AttributeType.BOOLEAN)
                        .value(false)
                        .build(),
                CollectionTemplateVersion.CollectionTemplateVersionState.DRAFT
        );

        this.client.createEntity(collectionTemplateVersion1);
        this.client.createEntity(collectionTemplateVersion2);
        this.client.createEntity(collectionTemplateVersion3);
    }
}
