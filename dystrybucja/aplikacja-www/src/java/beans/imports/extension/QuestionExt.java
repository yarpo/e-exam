/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans.imports.extension;

import beans.imports.Question;
import beans.imports.Section;
import beans.imports.interfaces.IAnswers;
import beans.imports.interfaces.IQuestion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author misiakw
 */
public class QuestionExt implements IQuestion {

    private IQuestion _question;
    private List<Section> _sections;

    private int _points;

    public QuestionExt(IQuestion question){
        this._question = question;
        _sections = new ArrayList<Section>();
    }

    public IQuestion duplicate(){
        QuestionExt ret = new QuestionExt(_question);
        ret.setPoints(_points);
        return ret;
    }

    public void addSection(Section sekcja){
        _sections.add(sekcja);
    }

    public List<Section> getSections(){
        return _sections;
    }

    public int getPoints(){
        return _points;
    }

    public void setPoints(int points){
        this._points = points;
    }

    public int getId() {
        return _question.getId();
    }

    public String getContent() {
        return _question.getContent();
    }

    public int getGrade() {
        return _question.getGrade();
    }

    public int getType() {
        return _question.getType();
    }

    public boolean isTypeOpened() {
        return _question.isTypeOpened();
    }

    public boolean isTypeABCD() {
        return _question.isTypeABCD();
    }

    public boolean isTypeGap() {
        return _question.isTypeGap();
    }

    public void setId(int id) {
        _question.setId(id);
    }

    public void setContent(String content) {
        _question.setContent(content);
    }

    public void setGrade(int grade) {
        _question.setGrade(grade);
    }

    public void setType(int type) {
        _question.setType(type);
    }

    public void setTypeOpened(boolean isOpened) {
        _question.setTypeOpened(isOpened);
    }

    public void setTypeABCD(boolean isABCD) {
        _question.setTypeABCD(isABCD);
    }

    public void setTypeGap(boolean isGap) {
        _question.setTypeGap(isGap);
    }

    public IAnswers getAnswers() {
        return _question.getAnswers();
    }

    public void setAnsQuesId(int quesId) {
        _question.setAnsQuesId(quesId);
    }

    public void setAnswers(IAnswers ans) {
        _question.setAnswers(ans);
    }

    public String getComment() {
        return _question.getComment();
    }

    public void setComment(String comment) {
        _question.setComment(comment);
    }

    public IQuestion getQuestion() {
	return _question;
    }

    public void setQuestion(IQuestion _question) {
	this._question = _question;
    }

    public void setCategory(String category) {
	this._question.setCategory(category);
    }

    public String getCategory() {
	return this._question.getCategory();
    }

}
