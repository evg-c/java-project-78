package hexlet.code.schemas;
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
    private boolean required = false;

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
     * Метод isRequiredNotNull служит для определения наличия или отсутствия ограничения обязательности заполнения.
     * Входные параметры отсутствуют.
     * @return Возвращает boolean логическое значение наличия (true) или отстуствия (false) ограничения NotNull.
     */
    public boolean isRequiredNotNull() {
        return required;
    }

    /**
     * Метод isValid проверяет данные на валидность (правильность) заполнения.
     * Для этого нужно вызвать метод isValid() на сконфигурированной схеме.
     * @param validationObject - Данные, которые нужно провалидировать.
     * @return Результатом работы будет логическое значение true, если данные соответствуют всем заданным
     * в схеме правилам, или false, если не соответствуют.
     */
    public boolean isValid(Object validationObject) {
        boolean result = true;
        if ((validationObject == null) && isRequiredNotNull()) {
            result = false;
        }
        return result;
    }

    /**
     * Метод contains в данном классе используется для валидации типов данных Object, когда еще неизвестно,
     * строка в этом объекте или нет.
     * Предназначен только для проверки строковых данных, применяется - schemas/StringSchema.java
     * Добавляет в схему ограничение по содержимому строки.
     * @param ya - Строка должна содержать определённую подстроку.
     * @return Возвращает объект схемы с установленным данным ограничением.
     */
    public BaseSchema contains(String ya) {
        return this;
    }

    /**
     * Метод minLength в данном классе используется для валидации типов данных Object, когда еще неизвестно,
     * строка в этом объекте или нет.
     * Предназначен только для проверки строковых данных, применяется - schemas/StringSchema.java
     * Добавляет в схему ограничение минимальной длины для строки.
     * @param i - Строка должна быть равна или длиннее указанного числа.
     * @return Возвращает объект схемы с установленным данным ограничением.
     */
    public BaseSchema minLength(int i) {
        return this;
    }
}
