/*
 * Klasa sprawdza poprawnosc podanej nazwy egzaminu
 */
package presentation.helpers;
/**
 *
 * @author yarpo
 */
public class ExamNameValidator
{
    public final static String PATTERN = "^[_A-Za-z0-9-\\.]{3,20}";

    public static boolean valid (String name)
    {
        return name.matches(PATTERN);
    }
}
