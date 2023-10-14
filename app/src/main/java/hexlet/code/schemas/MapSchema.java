package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private Map<String, BaseSchema> mapSchemas = new HashMap<>();

    public MapSchema sizeof(int sizeMap) {
        validators.add(x -> ((HashMap<?, ?>) x).size() == sizeMap);
        return this;
    }

    public Map<String, BaseSchema> shape(Map<String, BaseSchema> listSchemas) {
        if (!(listSchemas == null)) {
            mapSchemas.putAll(listSchemas);
        }
        return mapSchemas;
    }

    public boolean isValid(Object validationObject) {
        validators.add(x -> ((x == null) || (x instanceof Map<?, ?>)));
        if (!super.isValid(validationObject)) {
            return false;
        }
        // теперь проверка вложенных объектов
        if (!mapSchemas.isEmpty()) {
            for (Map.Entry<String, BaseSchema> entryCurrentSchema: mapSchemas.entrySet()) {
                String currentKey = entryCurrentSchema.getKey();
                BaseSchema currentSchema = entryCurrentSchema.getValue();
                for (Map.Entry<String, Object> entryCurrentObjectValidation
                        : ((Map<String, Object>) validationObject).entrySet()) {
                    if (entryCurrentObjectValidation.getKey().equals(currentKey)) {
                        if (!currentSchema.isValid(entryCurrentObjectValidation.getValue())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
