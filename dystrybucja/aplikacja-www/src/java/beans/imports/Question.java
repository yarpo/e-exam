package beans.imports;

import beans.imports.interfaces.IAnswers;
import beans.imports.interfaces.IAnswerGap;
import beans.imports.interfaces.IQuestion;
import beans.imports.interfaces.IAnswerABCD;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author michal
 */
public class Question implements IQuestion, Serializable {
    protected int id;
    protected int type;
    protected String content;
    protected int grade;
    protected IAnswers answers;
    protected String comment;
    protected String category;

    public Question(int Id, int Type, String Content, int Grade, String comment) {
	this.id = Id;
	this.type = Type;
	this.content = Content;
	this.grade = Grade;
	this.comment = comment;
    }

    public IQuestion duplicate(){
	IQuestion dup = new Question(id, type, content, grade, comment);
	dup.setAnswers(answers);
	dup.setCategory(category);
        return dup;
    }

    public int getId() {
	return this.id;
    }

    public String getContent() {
	return this.content;
    }

    public int getGrade() {
	return this.grade;
    }

    public int getType() {
	return this.type;
    }

    public String getComment() {
	return this.comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public boolean isTypeOpened() {
	return TypeCalculator.isOpen(type);
    }

    public boolean isTypeABCD() {
	return TypeCalculator.isABCD(type);
    }

    public boolean isTypeGap() {
	return TypeCalculator.isGap(type);
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public void setGrade(int grade) {
	this.grade = grade;
    }

    public void setType(int type) {
	this.type = type;
    }

    public void setTypeOpened(boolean isOpened) {
	if (isOpened) {
	    this.type = this.type | TypeCalculator.setOpen();
	} else {
	    this.type = this.type & (~TypeCalculator.setOpen());
	}
    }

    public void setTypeABCD(boolean isABCD) {
	if (isABCD) {
	    this.type = this.type | TypeCalculator.setABCD();
	} else {
	    this.type = this.type & (~TypeCalculator.setABCD());
	}
    }

    public void setTypeGap(boolean isGap) {
	if (isGap) {
	    this.type = this.type | TypeCalculator.setGap();
	} else {
	    this.type = this.type & (~TypeCalculator.setGap());
	}
    }

    public IAnswers getAnswers() {
	return answers;
    }

 
    public void setAnsQuesId(int quesId)
    {
        if(answers!=null)
        {
            List<IAnswerABCD> ansAbcd=answers.getAnswersABCD();
            List<IAnswerGap> ansGap=answers.getAnswersGap();
            
            for(int i=0; i<ansAbcd.size(); i++)
            {
                ansAbcd.get(i).setQuestionId(quesId);
            }
            for(int i=0; i<ansGap.size(); i++)
            {
                ansGap.get(i).setQuestionId(quesId);
            }
        }
    }

    public void setAnswers(IAnswers ans) {
        answers=ans;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

}
