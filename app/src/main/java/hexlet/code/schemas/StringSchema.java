package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema minLength(int length) {
        validators.add(x -> (x.toString().length() >= length));
        return this;
    }

    public StringSchema contains(String strCon) {
        validators.add(x -> (x.toString().contains(strCon)));
        return this;
    }
    public StringSchema required() {
        super.required();
        validators.add(x -> (!x.toString().isEmpty()));
        return this;
    }

    public boolean isValid(Object validationObject) {
        validators.add(x -> ((x == null) || (x instanceof String)));
        return super.isValid(validationObject);
    }
}
