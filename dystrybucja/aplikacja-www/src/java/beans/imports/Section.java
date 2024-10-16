package beans.imports;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michal
 */
public class Section {

    protected int id;
    protected int examId;
    protected int category;
    protected int type;
    protected int questionsNo;
    protected int gradeMin;
    protected int gradeMax;
    protected int points;
    protected String name;
    protected List<Integer> questionsInSection;

     // domylsny konstruktor - nic nie rzekazujemu
    public Section(){
    }

    public Section(int id, int examId, int category, int type, int questionsNo, int gradeMin, int gradeMax, int points, String name) {
	this.id = id;
	this.examId = examId;
	this.category = category;
	this.type = type;
	this.questionsNo = questionsNo;
	this.gradeMin = gradeMin;
	this.gradeMax = gradeMax;
	this.points = points;
	this.name = name;
	this.questionsInSection = new ArrayList<Integer>();
    }

    public Section duplicate(){
        Section ret =  new Section (id, examId, category, type, questionsNo, gradeMin, gradeMax, points, name);
        for (int i=0; i<questionsInSection.size(); i++)
            ret.addQuestionInSection(questionsInSection.get(id));
        return ret;
    }

    public void getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getCategory() {
	return category;
    }

    public void setCategory(int category) {
	this.category = category;
    }

    public int getExamId() {
	return examId;
    }

    public void setExamId(int examId) {
	this.examId = examId;
    }

    public int getGradeMin() {
	return this.gradeMin;
    }

    public void setGradeMin(int gradeMin) {
	this.gradeMin = gradeMin;
    }

    public int getGradeMax() {
	return this.gradeMax;
    }

    public void setGradeMax(int gradeMax) {
	this.gradeMax = gradeMax;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getPoints() {
	return points;
    }

    public void setPoints(int points) {
	this.points = points;
    }

    public int getQuestionsNo() {
	return questionsNo;
    }

    public void setQuestionsNo(int questionsNo) {
	this.questionsNo = questionsNo;
    }

    public int getType() {
	return type;
    }

    public void setType(int type) {
	this.type = type;
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
    
    /**
     *
     * @return Lista Integer z id pytań, które pasują do sekcji
     */
    public List<Integer> getQuestionsInSection() {
	return this.questionsInSection;
    }

    public void setQuestionsInSection(List<Integer> questions) {
	this.questionsInSection = questions;
    }

    public void addQuestionInSection(int id) {
	this.questionsInSection.add(new Integer(id));
    }
}
