/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.exams;

import java.util.Date;
import java.util.List;

/**
 *
 * @author yarpo
 */
public interface IExam
{
    public void addSection (ISection section)
            throws Exception;

    public void save ()
            throws Exception;

    public void delete ()
            throws Exception;

    public int getId ();

    public String getName ();

    public Date getDate ();

    public List<ISection> getSections ();

    public void setId (int id);

    public void setName (String name);

    public void setDate (Date date);
}
