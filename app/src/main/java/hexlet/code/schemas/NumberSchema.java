package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
//    private boolean required = false;
    private boolean positive = false;
    private boolean rangeIsEnabled = false;
    private int beginRange = 0;
    private int endRange = 0;

//    public NumberSchema required() {
//        this.required = true;
//        return this;
//    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int begin, int end) {
        this.rangeIsEnabled = true;
        this.beginRange = begin;
        this.endRange = end;
        return this;
    }

//    public boolean isRequiredNotNull() {
//        return required;
//    }

    public boolean isPositive() {
        return positive;
    }

    public boolean isRangeIsEnabled() {
        return rangeIsEnabled;
    }

    public int getBeginRange() {
        return beginRange;
    }

    public int getEndRange() {
        return endRange;
    }

    public boolean isValid(Object validationObject) {
        boolean result = super.isValid(validationObject);
        if (result && !(validationObject == null) && !(validationObject instanceof Number)) {
            result = false;
        }
        if (result && (validationObject instanceof Number) && isPositive()
                && ((Integer) validationObject <= 0)) {
            result = false;
        }
        if (result && (validationObject instanceof Number) && isRangeIsEnabled()
                && (((Integer) validationObject < getBeginRange())
                || ((Integer) validationObject > getEndRange()))) {
            result = false;
        }
        return result;
    }
}
