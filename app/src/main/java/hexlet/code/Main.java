package hexlet.code;
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

public class Main {
    public static void main(String[] args) {
        Validator v = new Validator();
        StringSchema schema = v.string();
        System.out.println(schema.isValid("")); // true
        System.out.println(schema.isValid(null)); // true
        schema.required();
        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid("")); // false
        System.out.println(schema.isValid(5)); // false
        System.out.println(schema.isValid("hexlet"));
        schema.minLength(7);
        System.out.println(schema.isValid("hexlet"));
        System.out.println(schema.isValid("what does the fox say")); // true
        System.out.println(schema.contains("wh").isValid("what does the fox say")); // true
        System.out.println(schema.contains("what").isValid("what does the fox say")); // true
        System.out.println(schema.contains("whatthe").isValid("what does the fox say")); // false
        System.out.println(schema.isValid("what does the fox say")); // false
//        StringSchema schema = v.string().required().minLength(5).contains("hex");
    }
}
