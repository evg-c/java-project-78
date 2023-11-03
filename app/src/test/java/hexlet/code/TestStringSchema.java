package hexlet.code;

import hexlet.code.schemas.StringSchema;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class TestStringSchema {
    @Test
    public void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(5)).isFalse();
        assertThat(schema.isValid("hexlet")).isTrue();
        schema.minLength(7);
        assertThat(schema.isValid("hexlet")).isFalse();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").
                isValid("what does the fox say")).isFalse();
        assertThat(schema.contains("wh").contains("what").
                isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("wh").contains("what").contains("whatthe").
                isValid("what does the fox say")).isFalse();
    }
}
