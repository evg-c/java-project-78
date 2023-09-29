package hexlet.code.schemas;

public class BaseSchema {
    private boolean required = false;

    public BaseSchema required() {
        this.required = true;
        return this;
    }

    public boolean isRequiredNotNull() {
        return required;
    }

    public boolean isValid(Object validationObject) {
        boolean result = true;
        if ((validationObject == null) && isRequiredNotNull()) {
            result = false;
        }
        return result;
    }
}
