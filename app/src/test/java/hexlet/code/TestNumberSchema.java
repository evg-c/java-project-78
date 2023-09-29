package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestNumberSchema {
    @Test
    public void testNumberIsNullAndNotRequiredNotNull() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testNumberIsNullAndRequiredNotNull() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    public void testString() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertThat(schema.isValid("5")).isFalse();
    }

    @Test
    public void numberNormal() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertThat(schema.isValid(10)).isTrue();
    }

    @Test
    public void numberIsNegativeAndNotRequiredPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertThat(schema.isValid(-10)).isTrue();
    }

    @Test
    public void numberIsNegativeAndRequiredPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.positive();
        assertThat(schema.isValid(-10)).isFalse();
    }

    @Test
    public void numberIsZeroAndNotRequiredPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertThat(schema.isValid(0)).isTrue();
    }

    @Test
    public void numberIsZeroAndRequiredPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.positive();
        assertThat(schema.isValid(0)).isFalse();
    }

    @Test
    public void numberInRange() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.range(5, 10);
        assertThat(schema.isValid(5)).isTrue();
    }

    @Test
    public void numberOutOfRange() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.range(5, 10);
        assertThat(schema.isValid(4)).isFalse();
    }
}
