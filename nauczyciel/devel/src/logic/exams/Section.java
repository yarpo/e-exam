/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.exams;

import data.bean.SectionSrcBean;
import data.interfaces.ISectionSrc;
import data.staticClass.TableObjectFactory;

/**
 *
 * @author yarpo
 */
public class Section implements ISection {

    protected int id;
    protected int examId;
    protected int category;
    protected int type;
    protected int questionsNo;
    protected int gradeMin;
    protected int gradeMax;
    protected int points;
    protected String name;


     // domylsny konstruktor - nic nie rzekazujemu
    public Section()
    {
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
    }

    public Section(int id, int examId, int category, int type, int questionsNo, int gradeMin, int gradeMax, int points) {
	this(id, examId, category, type, questionsNo, gradeMin, gradeMax, points, "");
    }

    public Section(SectionSrcBean bean)
    {
            this(bean.getId(), bean.getExamId(), bean.getCategoryId(),
                    bean.getType(), bean.getHowMuch(), bean.getGradeMin(),
                    bean.getGradeMax(), bean.getPoints(), bean.getName());
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

    public void save() throws Exception {
	_save();
    }

    protected void _save() throws Exception {
        ISectionSrc sectionSrc = TableObjectFactory.getInstance().getSection();
	SectionSrcBean section = new SectionSrcBean(
                this.examId, this.category, this.type,
                this.questionsNo, this.gradeMin, this.gradeMax, this.points);
        section.setName(this.name);
        sectionSrc.save(section);
        this.id =  sectionSrc.getLastSectionId();
    }

    public void update() throws Exception {
       _update();
    }

    protected void _update() throws Exception {
        ISectionSrc sectionSrc = TableObjectFactory.getInstance().getSection();
        SectionSrcBean bean = new SectionSrcBean(this.getId(), this.getExamId(),
                    this.getCategory(), this.getType(), this.getQuestionsNo(),
                    this.getGradeMin(), this.getGradeMax(), this.getPoints());
        bean.setName(this.name);
        sectionSrc.update(bean);
    }

    public void delete() throws Exception {
        _delete();
    }

    protected void _delete() throws Exception {
        ISectionSrc sectionSrc = TableObjectFactory.getInstance().getSection();
        sectionSrc.delete(this.getId());
    }
}
