
package beans.imports;



/**
 *
 * @author yarpo
 */
public class SectionABCD extends Section {

    protected int all;
    protected int correct;

    public SectionABCD(int id, int examId, int category, int type,
                        int questionsNo, int gradeMin, int gradeMax,
                        int points, int all, int correct, String name) {
        super(id, examId, category, type, questionsNo, gradeMin, gradeMax, points, name);
	this.all = all;
        this.correct = correct;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
