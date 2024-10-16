package beans.imports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author yarpo
 */
public class Exam {

    protected int examId;
    protected Date date;
    protected String name;
    protected List<Section> sectionList;

    public Exam(int id, String name, Date date) throws Exception {
	this.examId = id;
	this.date = date;
	this.name = name;
	this.sectionList = new ArrayList<Section>();
    }

    public void addSection(Section section) {
	this.sectionList.add(section);
    }

    public int getId() {
	return examId;
    }

    public String getName() {
	return name;
    }

    public Date getDate() {
	return date;
    }

    public void setId(int id) {
	examId = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public List<Section> getSections() {
	return sectionList;
    }
}
