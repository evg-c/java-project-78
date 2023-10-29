package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        addCheck("checkTypeString", x -> ((x == null) || (x instanceof String)));
    }

    public StringSchema minLength(int length) {
        addCheck("minLengthString", x -> (x.toString().length() >= length));
        return this;
    }

    public StringSchema contains(String strCon) {
        addCheck("containsString", x -> (x.toString().contains(strCon)));
        return this;
    }
    public StringSchema required() {
        super.required();
        addCheck("requiredString", x -> (!x.toString().isEmpty()));
        return this;
    }
}
