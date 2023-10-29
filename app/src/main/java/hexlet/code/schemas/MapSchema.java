package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        addCheck("checkTypeMap", x -> ((x == null) || (x instanceof Map<?, ?>)));
    }

    public MapSchema sizeof(int sizeMap) {
        addCheck("sizeOfMap", x -> ((HashMap<?, ?>) x).size() == sizeMap);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck(
                "shape",
                value -> {
                    return schemas.entrySet().stream().allMatch(e -> {
                        Object v = ((Map) value).get(e.getKey());
                        return e.getValue().isValid(v);
                    });
                }
        );
        return this;
    }
}
