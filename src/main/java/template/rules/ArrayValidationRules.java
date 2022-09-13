package template.rules;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class ArrayValidationRules<T> extends ValidationRules<T> {

    private Integer minSize, maxSize;
    private List<ContentType> allowedContentTypes, disallowedContentTypes;
}
