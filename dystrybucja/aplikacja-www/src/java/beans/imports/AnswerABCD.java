package beans.imports;

import beans.imports.interfaces.IAnswerABCD;

/**
 *
 * @author michal
 */
public class AnswerABCD implements IAnswerABCD {

    protected int id;
    protected String content;
    protected int questionId;
    protected boolean correct;

    public AnswerABCD(String content, int questionId, boolean correct) {
	this.content = content;
	this.questionId = questionId;
	this.correct = correct;
    }

    public AnswerABCD(int id, String content, int questionId, boolean correct) {
        this(content, questionId, correct);
	this.id = id;
    }

    public AnswerABCD() {

    }

    public boolean isCorrect() {
	return this.correct;
    }

    public void setCorrect(boolean isCorrect) {
	this.correct = isCorrect;
    }

    public int getId() {
	return this.id;
    }

    public String getContent() {
	return this.content;
    }

    public int getQuestionId() {
	return this.questionId;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public void setQuestionId(int questionId) {
	this.questionId = questionId;
    }
}
