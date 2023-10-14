package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema positive() {
        validators.add(x -> ((x == null) || ((x instanceof Number) && ((Integer) x > 0))));
        return this;
    }

    public NumberSchema range(int begin, int end) {
        validators.add(x -> ((x instanceof Number) && ((Integer) x >= begin)
                && ((Integer) x <= end)));
        return this;
    }

    public boolean isValid(Object validationObject) {
        validators.add(x -> ((x == null) || (x instanceof Number)));
        return super.isValid(validationObject);
    }
}
