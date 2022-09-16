package template.attribute;

public interface Attribute {

    AttributeType type();

    enum AttributeType {
        STRING,
        NUMBER,
        BOOLEAN,
        DOCUMENT,
        STRING_ARRAY
    }
}
