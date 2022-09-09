package template.rules;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@DynamoDBDocument
public class RangeValidationRules extends ValidationRules<Number> {

    private Number min, max;
}
