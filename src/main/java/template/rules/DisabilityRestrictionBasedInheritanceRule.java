package template.rules;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@DynamoDBDocument
public class DisabilityRestrictionBasedInheritanceRule implements InheritanceRule{

    private String field;
    private Boolean disabled;
}
