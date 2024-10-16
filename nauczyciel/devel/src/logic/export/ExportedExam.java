package logic.export;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author yarpo
 */
public class ExportedExam
{
    protected int examId;
    protected Date date;
    protected String name;
    protected List<ExportedSection> sectionList;

    public ExportedExam (int id, String name, Date date)
            throws Exception
    {
        this.examId = id;
        this.date = date;
        this.name = name;
        this.sectionList = new ArrayList<ExportedSection>();
    }

    public void addSection (ExportedSection section)
    {
        this.sectionList.add(section);
    }

    public int getId ()
    {
        return examId;
    }

    public String getName ()
    {
        return name;
    }

    public Date getDate ()
    {
        return date;
    }

    public void setId (int id)
    {
        examId = id;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public void setDate (Date date)
    {
        this.date = date;
    }

    public List<ExportedSection> getSections ()
    {
        return sectionList;
    }
}
