package template.rules;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
@DynamoDBDocument
public class ValueRestrictionInheritanceRule implements InheritanceRule {

    private String field;
    private Set<String> allowedValues;
}
