package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean sizeOfMapEnabled = false;
    private int sizeOfMap = 0;

    public boolean isSizeOfMapEnabled() {
        return sizeOfMapEnabled;
    }

    public int getSizeOfMap() {
        return sizeOfMap;
    }

    public MapSchema sizeof(int sizeMap) {
        this.sizeOfMapEnabled = true;
        this.sizeOfMap = sizeMap;
        return this;
    }

    public boolean isValid(Object objectValidation) {
        boolean result = super.isValid(objectValidation);
        if (result && !(objectValidation == null) && !(objectValidation instanceof Map<?, ?>)) {
            result = false;
        }
        if (result && (objectValidation instanceof Map<?, ?>) && isSizeOfMapEnabled()
            && (((Map<?, ?>) objectValidation).size() < getSizeOfMap())) {
            result = false;
        }
        return result;
    }
}
