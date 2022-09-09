package template.rules;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@DynamoDBDocument
public class ValidationRules<T> {

    private Boolean required;
    private Boolean overridable;
    private List<T> allowedValues;
}