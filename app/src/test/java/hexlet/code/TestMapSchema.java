package hexlet.code;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class TestMapSchema {
    @Test
    public void testMapIsNullAndNotRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testMapIsNullAndNotRequired2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
    }
    @Test
    public void testMapIsNullAndRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    public void testMapIsNullAndRequired2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
    }
    @Test
    public void testMapNormal() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertThat(schema.isValid(new HashMap())).isTrue();
    }

    @Test
    public void testSizeOfMap1() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue(); // true
    }

    @Test
    public void testSizeOfMap2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        schema.sizeof(2);
        assertThat(schema.isValid(data)).isFalse();
    }

    @Test
    public void testSizeOfMap3() {
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
        assertThat(schema.isValid(human1)).isTrue();
    }

    @Test
    public void testShapeOfMapForNameContains2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().contains("ya"));
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Mischa");
        assertThat(schema.isValid(human1)).isFalse();
    }

    @Test
    public void testMapCommon() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap())).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap())).isTrue();
        schema.sizeof(2);
        assertThat(schema.isValid(new HashMap())).isFalse();
        Map<String, String> actual1 = new HashMap<>();
        actual1.put("key1", "value1");
        assertThat(schema.isValid(actual1)).isFalse();
        actual1.put("key2", "value2");
        assertThat(schema.isValid(actual1)).isTrue();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().contains("ya"));
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
        Map<String, Object> actual2 = new HashMap<>();
        actual2.put("name", "Kolya");
        actual2.put("age", 100);
        assertThat(schema.isValid(actual2)).isTrue();
        Map<String, Object> actual3 = new HashMap<>();
        actual3.put("name", "Maya");
        actual3.put("age", null);
        assertThat(schema.isValid(actual3)).isTrue();
        Map<String, Object> actual4 = new HashMap<>();
        actual4.put("name", "");
        actual4.put("age", null);
        assertThat(schema.isValid(actual4)).isFalse();
        Map<String, Object> actual5 = new HashMap<>();
        actual5.put("name", "Valya");
        actual5.put("age", -5);
        assertThat(schema.isValid(actual5)).isFalse();
        Map<String, Object> actual6 = new HashMap<>();
        actual6.put("name", "Ada");
        actual6.put("age", 15);
        assertThat(schema.isValid(actual6)).isFalse();
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
        assertThat(schema.isValid(human1)).isFalse();
    }
}
