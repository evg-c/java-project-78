package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        validators.add(x -> ((x == null) || (x instanceof String)));
    }

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
}
