package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addCheck("checkTypeNumber", x -> ((x == null) || (x instanceof Number)));
    }
    public NumberSchema positive() {
        addCheck("positiveNumber", x -> ((x == null) || ((x instanceof Number) && ((Integer) x > 0))));
        return this;
    }

    public NumberSchema range(int begin, int end) {
        addCheck("rangeNumber", x -> ((x instanceof Number) && ((Integer) x >= begin)
                && ((Integer) x <= end)));
        return this;
    }
}
