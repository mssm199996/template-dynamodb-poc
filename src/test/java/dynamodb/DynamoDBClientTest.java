package dynamodb;

import org.junit.Test;
import template.CollectionTemplate;
import template.CollectionTemplateVersion;

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
                "sport",
                Set.of()
        );

        this.client.createEntity(collectionTemplate);

        CollectionTemplateVersion collectionTemplateVersion1 = new CollectionTemplateVersion(
                collectionTemplate.getId(), 0, 1000L, 2000L, "user_1",
                //null,
                //null,
                CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED

        ), collectionTemplateVersion2 = new CollectionTemplateVersion(
                collectionTemplate.getId(), 1, 3000L, 3500L, "user_1",
                //Attribute.<String>builder().type(AttributeType.STRING).value("Collection Template V2").build(),
                //Attribute.<Boolean>builder().type(AttributeType.BOOLEAN).value(false).build(),
                CollectionTemplateVersion.CollectionTemplateVersionState.PUBLISHED
        ), collectionTemplateVersion3 = new CollectionTemplateVersion(
                collectionTemplate.getId(), 2, 4000L, 7000L, "user_1",
                //Attribute.<String>builder().type(AttributeType.STRING).value("Collection Template V3").build(),
                //Attribute.<Boolean>builder().type(AttributeType.BOOLEAN).value(false).build(),
                CollectionTemplateVersion.CollectionTemplateVersionState.DRAFT
        );

        this.client.createEntity(collectionTemplateVersion1);
        //this.client.createEntity(collectionTemplateVersion2);
        //this.client.createEntity(collectionTemplateVersion3);
    }
}
