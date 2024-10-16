/*
 * Klasa Category
 * Obsluguje logike zwiazana z kategoriami
 */
package logic.category;

import data.interfaces.ICategorySrc;
import data.staticClass.TableObjectFactory;
import java.util.List;
import logic.category.exception.CategoryExistsException;

/**
 *
 * @author yarpo
 */
public class Category implements ICategory
{
    private ICategorySrc category;

    public Category ()
    {
        try
        {
            category = TableObjectFactory.getInstance().getCategory();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Dodaje kategorie
     */
    public void add (String name)
            throws Exception
    {
        if (find(name))
        {
            throw new CategoryExistsException(name);
        }

        category.add(name);
    }

    /**
     * Sprawdza, czy istnieje kategoria o podanej nazwie
     */
    public boolean find (String name)
            throws Exception
    {
        List<String> categories = category.get();
        for (String myCategory : categories)
        {
            if (myCategory.equals(name))
            {
                return true;
            }
        }
        return false;
    }
}
