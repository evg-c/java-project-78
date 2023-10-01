package hexlet.code;
import hexlet.code.schemas.BaseSchema;
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

    @Test
    public void testShapeOfMapForName() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        assertThat(schema.isValid(human1)).isTrue(); // true
    }

    @Test
    public void testShapeOfMapForName2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "");
        assertThat(schema.isValid(human1)).isFalse();
    }

    @Test
    public void testShapeOfMapForName3() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", 5);
        assertThat(schema.isValid(human1)).isFalse();
    }

    @Test
    public void testShapeOfMapForAge() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("age", 100);
        assertThat(schema.isValid(human1)).isTrue(); // true
    }

    @Test
    public void testShapeOfMapForAge2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("age", null);
        assertThat(schema.isValid(human1)).isTrue();
    }

    @Test
    public void testShapeOfMapForAge3() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("age", -5);
        assertThat(schema.isValid(human1)).isFalse();
    }

    @Test
    public void testShapeOfMapForRange() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("age", v.number().positive().range(5, 10));
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("age", 4);
        assertThat(schema.isValid(human1)).isFalse();
    }

    @Test
    public void testShapeOfMapForRange2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("age", v.number().positive().range(5, 10));
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("age", 11);
        assertThat(schema.isValid(human1)).isFalse();
    }

    @Test
    public void testShapeOfMapForRange3() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("age", v.number().positive().range(5, 10));
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("age", 8);
        assertThat(schema.isValid(human1)).isTrue();
    }

    @Test
    public void testShapeOfMapForAgeRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("age", v.number().positive().range(5, 10).required());
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("age", null);
        assertThat(schema.isValid(human1)).isFalse();
    }

    @Test
    public void testShapeOfMapForAgeRequired2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("age", v.number().positive().range(5, 10).required());
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("age", 9);
        assertThat(schema.isValid(human1)).isTrue();
    }

    @Test
    public void testShapeOfMapForNameContains() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().contains("ya"));
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        assertThat(schema.isValid(human1)).isTrue(); // true
    }

    @Test
    public void testShapeOfMapForNameMinLength() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().minLength(7).contains("ya"));
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        assertThat(schema.isValid(human1)).isFalse(); // true
    }
}
