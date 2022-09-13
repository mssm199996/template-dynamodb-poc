package template.template;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class CollectionTemplate {

    private String id;
    private String alias, name, productLineId, productCategoryId;

    private Set<CollectionTemplateVersion> collectionTemplateVersions;
}
