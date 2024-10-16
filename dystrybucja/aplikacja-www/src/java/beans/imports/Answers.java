package beans.imports;

import beans.imports.interfaces.IAnswers;
import beans.imports.interfaces.IAnswerGap;
import beans.imports.interfaces.IAnswerABCD;
import beans.imports.interfaces.IAnswer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author michal
 */
public class Answers implements IAnswers {

    protected List<IAnswerGap> answersGap;
    protected List<IAnswerABCD> answersABCD;

    public Answers() {
	this.answersGap = new ArrayList<IAnswerGap>();
	this.answersABCD = new ArrayList<IAnswerABCD>();
    }

    public List<IAnswer> getAllAnswers() {
	ArrayList lista = new ArrayList<IAnswer>();
	lista.addAll(this.answersABCD);
	lista.addAll(this.answersGap);
	return lista;
    }

    public List<IAnswerABCD> getWrongAnswersABCD() {
	ArrayList lista = new ArrayList<IAnswerABCD>();
	Iterator it = this.answersABCD.iterator();
	while (it.hasNext()) {
	    IAnswerABCD bean = (IAnswerABCD) it.next();
	    if (!bean.isCorrect()) {
		lista.add(bean);
	    }
	}
	return lista;
    }

    public List<IAnswerABCD> getCorrectAnswersABCD() {
	ArrayList lista = new ArrayList<IAnswerABCD>();
	Iterator it = this.answersABCD.iterator();
	while (it.hasNext()) {
	    IAnswerABCD bean = (IAnswerABCD) it.next();
	    if (bean.isCorrect()) {
		lista.add(bean);
	    }
	}
	return lista;
    }

    public List<IAnswerGap> getAnswersGap() {
	ArrayList lista = new ArrayList<IAnswerGap>();
	lista.addAll(this.answersGap);
	return lista;
    }

    public List<IAnswerABCD> getAnswersABCD() {
	return this.answersABCD;
    }

    public void addAnswer(IAnswerABCD answer) {
	this.answersABCD.add(answer);
    }

    public void addAnswer(IAnswerGap answer) {
	this.answersGap.add(answer);
    }

    public void deleteAnswer(IAnswerABCD answer) {
	this.answersABCD.remove(answer);
    }

    public void deleteAnswer(IAnswerGap answer) {
	this.answersGap.remove(answer);
    }

        public void setAnswersABCD(List<IAnswerABCD> abcd) {
       answersABCD=abcd;
    }

    public void setAnswersGap(List<IAnswerGap> gaps) {
        answersGap=gaps;
    }
}
