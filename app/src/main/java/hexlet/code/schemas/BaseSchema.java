package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Базовая схнма валидации данных
 *
 * Схема представляет собой объект, который содержит правила и ограничения для данных,
 * такие как обязательность заполнения, минимальную и максимальную длину строки и так далее.
 * Настройка схемы валидации может быть произведена при помощи различных методов, которые предоставляет схема.
 * Разные типы данных проверяются при помощи своих схем.
 * Данная схема является базовой и общей для следующих схем:
 * schemas/StringSchema.java - схема валидации строковых данных
 * schemas/NumberSchema.java - схема валидации числовых данных
 * schemas/MapSchema.java - схема валидации данных типа Map
 */
public class BaseSchema {
    protected Map<String, Predicate> validators = new HashMap<>();
    protected boolean required = false;

    /**
     * Метод required делает данные обязательными для заполнения.
     * Иными словами добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
     * Входные параметры отсутствуют.
     * @return Возвращает объект схемы с установленным данным ограничением.
     */
    public BaseSchema required() {
        this.required = true;
        return this;
    }

    /**
     * Метод isValid проверяет данные на валидность (правильность) заполнения.
     * Для этого нужно вызвать метод isValid() на сконфигурированной схеме.
     * @param validationObject - Данные, которые нужно провалидировать.
     * @return Результатом работы будет логическое значение true, если данные соответствуют всем заданным
     * в схеме правилам, или false, если не соответствуют.
     */
    public boolean isValid(Object validationObject) {
        if (this.required && (validationObject == null)) {
            return false;
        }
        for (Map.Entry<String, Predicate> oneCheck: validators.entrySet()) {
            Predicate currentPredicate = oneCheck.getValue();
            if (!currentPredicate.test(validationObject)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Метод addCheck добавляет в схему ограничение.
     * @param checkName - имя ограничения в формате строки;
     * @param check - условие ограничения в формате предиката;
     * Заполняет объект схемы с установленным данным ограничением.
     */
    public void addCheck(String checkName, Predicate check) {
        validators.put(checkName, check);
    }
}
