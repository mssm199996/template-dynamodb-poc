package template.rules;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@DynamoDBDocument
public class ArrayValidationRules<T> {

    private Integer minSize, maxSize;
    private List<ContentType> allowedContentTypes, disallowedContentTypes;
}
