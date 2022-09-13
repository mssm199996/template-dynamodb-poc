package template.rules;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class ValidationRules<T> {

    private Boolean required;
    private Boolean overridable;
    private List<T> allowedValues;
}