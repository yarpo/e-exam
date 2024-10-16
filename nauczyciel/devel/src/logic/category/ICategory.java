/*
 * Interfejs dla logiki klasy kategorii
 */
package logic.category;
/**
 *
 * @author yarpo
 */
public interface ICategory
{
    public void add (String name) throws Exception;

    public boolean find (String name) throws Exception;
}
