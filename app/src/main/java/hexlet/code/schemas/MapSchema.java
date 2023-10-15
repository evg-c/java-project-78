package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        validators.add(x -> ((x == null) || (x instanceof Map<?, ?>)));
    }

    public MapSchema sizeof(int sizeMap) {
        validators.add(x -> ((HashMap<?, ?>) x).size() == sizeMap);
        return this;
    }

    public void shape(Map<String, BaseSchema> listSchemas) {
        for (Map.Entry<String, BaseSchema> entryCurrentSchema: listSchemas.entrySet()) {
            String currentKey = entryCurrentSchema.getKey();
            BaseSchema currentSchema = entryCurrentSchema.getValue();
            Predicate nestedSchema = o -> {
                Map<String, Object> objectForCheck = (Map<String, Object>) o;
                for (Map.Entry<String, Object> entryObjectForCheck: objectForCheck.entrySet()) {
                    String keyEntry = entryObjectForCheck.getKey();
                    Object valueEntry = entryObjectForCheck.getValue();
                    if (keyEntry.equals(currentKey)) {
                        return currentSchema.isValid(valueEntry);
                    }
                }
                return true;
            };
            validators.add(nestedSchema);
        }
    }
}
