package hexlet.code.schemas;

import java.util.ArrayList;

public class StringSchema extends BaseSchema {
//    private boolean required = false;
    private boolean minLengthEnabled = false;
    private int minLength = 0;
    private ArrayList<String> containsStr = new ArrayList<>();

//    public StringSchema required() {
//        this.required = true;
//        return this;
//    }

    public StringSchema minLength(int length) {
        this.minLengthEnabled = true;
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String strCon) {
        if (containsStr.contains(strCon)) {
            return this;
        }
        containsStr.add(strCon);
        return this;
    }

//    public boolean isRequiredNotNull() {
//        return required;
//    }
    public int getMinLength() {
        return minLength;
    }

    public ArrayList<String> getContainsStr() {
        return containsStr;
    }

    public boolean isMinLengthEnabled() {
        return minLengthEnabled;
    }

    public boolean isValid(Object validationObject) {
        boolean result = true;
        if (!super.isValid(validationObject)) {
            result = false;
        }
//        if ((validationObject == null) && isRequiredNotNull()) {
//            result = false;
//        }
        if (result && !(validationObject == null) && (!(validationObject instanceof String))) {
            result = false;
        }
        if (result && (validationObject instanceof String)
                && (validationObject.toString().isEmpty()) && isRequiredNotNull()) {
            result = false;
        }
        if (result && (validationObject instanceof String) && isMinLengthEnabled()
                && (validationObject.toString().length() < getMinLength())) {
            result = false;
        }
        if (result && (validationObject instanceof String) && (!getContainsStr().isEmpty())) {
            for (String oneStr: getContainsStr()) {
                if (!((String) validationObject).contains(oneStr)) {
                    result = false;
                }
            }
        }
        return result;
    }
}
