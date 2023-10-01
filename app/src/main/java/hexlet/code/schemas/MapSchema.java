package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private boolean sizeOfMapEnabled = false;
    private int sizeOfMap = 0;
    private Map<String, BaseSchema> mapSchemas = new HashMap<>();

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

    public Map<String, BaseSchema> getMapSchemas() {
        return mapSchemas;
    }

    public Map<String, BaseSchema> shape(Map<String, BaseSchema> listSchemas) {
        if (!(listSchemas == null)) {
            mapSchemas.putAll(listSchemas);
        }
        return mapSchemas;
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
        if (result && (objectValidation instanceof Map<?, ?>) && (!getMapSchemas().isEmpty())) {
            for (Map.Entry<String, BaseSchema> entryCurrentSchema: getMapSchemas().entrySet()) {
                String currentKeySchema = entryCurrentSchema.getKey();
                BaseSchema currentSchema = entryCurrentSchema.getValue();
                for (Map.Entry<String, Object> entryCurrentObjectValidation
                        : ((Map<String, Object>) objectValidation).entrySet()) {
                    if (entryCurrentObjectValidation.getKey().equals(currentKeySchema)) {
                        result = result && currentSchema.isValid(entryCurrentObjectValidation.getValue());
                    }
                }
            }
        }
        return result;
    }
}
