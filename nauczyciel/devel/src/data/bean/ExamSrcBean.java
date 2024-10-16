package data.bean;

import java.util.Date;

/**
 *
 * @author michal
 */
public class ExamSrcBean
{
    protected int id;
    protected String name;
    protected Date date;

    public ExamSrcBean (int id, String name, Date date)
    {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public ExamSrcBean (String name, Date date)
    {
        this(0, name, date);
    }

    public Date getDate ()
    {
        return date;
    }

    public void setDate (Date date)
    {
        this.date = date;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }
}
