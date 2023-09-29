package hexlet.code;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;;

public class TestMapSchema {
    @Test
    public void testMapIsNullAndNotRequiredNotNull() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testMapIsNullAndRequiredNotNull() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    public void testMapNormal() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertThat(schema.isValid(new HashMap())).isTrue();
    }

    @Test
    public void testSizeOfMapNotRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue(); // true
    }

    @Test
    public void testSizeOfMapRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        schema.sizeof(2);
        assertThat(schema.isValid(data)).isFalse();
    }

    @Test
    public void testSizeOfMapRequiredTrue() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        schema.sizeof(2);
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();
    }
}
