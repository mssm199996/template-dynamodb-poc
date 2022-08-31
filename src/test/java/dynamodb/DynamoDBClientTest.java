package dynamodb;

import org.junit.Test;
import template.CollectionTemplate;
import template.CollectionTemplateVersion;
import template.rules.DisabilityRestrictionBasedInheritanceRule;
import template.rules.ValueRestrictionInheritanceRule;

import java.util.Set;

public class DynamoDBClientTest {

    private DynamoDBClient client = new DynamoDBClient();

    @Test
    public void createTable() {
        this.client.createTable(CollectionTemplate.class);
    }

    @Test
    public void saveCollectionTemplate() {
        CollectionTemplate collectionTemplate = new CollectionTemplate(
                "template-1",
                "template-1",
                "Template 1",
                "dplus",
                Set.of(),
                Set.of(
                        new DisabilityRestrictionBasedInheritanceRule("title", true),
                        new ValueRestrictionInheritanceRule("template", Set.of("page", "epg"))
                )
        );

        this.client.createEntity(collectionTemplate);

        CollectionTemplateVersion collectionTemplateVersion1 = new CollectionTemplateVersion(
                collectionTemplate.getId(), 0, 1000L, 2000L, "user_1",
                CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED
        ), collectionTemplateVersion2 = new CollectionTemplateVersion(
                collectionTemplate.getId(), 1, 3000L, 3500L, "user_1",
                CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED
        ), collectionTemplateVersion3 = new CollectionTemplateVersion(
                collectionTemplate.getId(), 2, 4000L, 7000L, "user_1",
                CollectionTemplateVersion.CollectionTemplateVersionState.DRAFT
        );

        this.client.createEntity(collectionTemplateVersion1);
        this.client.createEntity(collectionTemplateVersion2);
        this.client.createEntity(collectionTemplateVersion3);
    }
}
