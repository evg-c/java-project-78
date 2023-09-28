package hexlet.code;

import hexlet.code.schemas.StringSchema;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class TestStringSchema {
    @Test
    public void testNotRequiredAndStringIsNull() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testRequiredAndStringIsNull() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
    }
    @Test
    public void testNotRequiredAndStringIsEmpty() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.isValid("")).isTrue();
    }

    @Test
    public void testRequiredAndStringIsEmpty() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();
        assertThat(schema.isValid("")).isFalse();
    }
    @Test
    public void testInteger() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.isValid(5)).isFalse();
    }

    @Test
    public void testStringNormal() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.isValid("hexlet")).isTrue();
    }
    @Test
    public void testNotMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.isValid("hexlet")).isTrue();
    }

    @Test
    public void testMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.minLength(7);
        assertThat(schema.isValid("hexlet")).isFalse();
    }

    @Test
    public void testStringNotContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.isValid("what does the fox say")).isTrue();
    }

    @Test
    public void testStringContainsTrue() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
    }

    @Test
    public void testStringContainsFalse() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();
    }

    @Test
    public void testStringTwoContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.contains("wh").contains("what").isValid("what does the fox say")).isTrue();
    }

    @Test
    public void testStringThreeContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.contains("wh").contains("what").contains("whatthe").
                isValid("what does the fox say")).isFalse();
    }
}
