/*
 * Wyjatek rzucany, gdy istnieje juz taka kategoria
 */
package logic.category.exception;
/**
 *
 * @author yarpo
 */
public class CategoryExistsException extends Exception
{
    public CategoryExistsException (String string)
    {
        super(string);
    }
}
