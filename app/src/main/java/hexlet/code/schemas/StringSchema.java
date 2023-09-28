package hexlet.code.schemas;

import java.util.ArrayList;

public class StringSchema {
    private boolean required = false;
    private int minLength = 0;
    private ArrayList<String> containsStr = new ArrayList<>();

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
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

    public boolean isRequired() {
        return required;
    }
    public int getMinLength() {
        return minLength;
    }

    public ArrayList<String> getContainsStr() {
        return containsStr;
    }

    public boolean isValid(Object str) {
        if ((str == null) && isRequired()) {
            return false;
        }
        if (!(str == null) && (!(str instanceof String))) {
            return false;
        }
        if (!(str == null) && (str instanceof String) && (str.toString().isEmpty()) && isRequired()) {
            return false;
        }
        if (!(str == null) && (str instanceof String) && (str.toString().length() < getMinLength())) {
            return false;
        }
        if (!(str == null) && (str instanceof String) && (!getContainsStr().isEmpty())) {
            for (String oneStr: getContainsStr()) {
                if (!((String) str).contains(oneStr)) {
                    return false;
                }
            }
        }
        return true;
    }
}
