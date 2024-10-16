/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans.imports;

/**
 *
 * @author yarpo
 */
public class SectionGap extends Section {

    protected boolean underline;
    protected boolean shownChars;
    protected int howMuchChars;
    protected int charsMode;

    public SectionGap(int id, int examId, int category, int type,
                        int questionsNo, int gradeMin, int gradeMax,
                        int points, boolean underline, boolean shownChars,
                        int howMuchChars, int charsMode, String name) {
        super(id, examId, category, type, questionsNo, gradeMin, gradeMax, points, name);
	this.underline = underline;
        this.shownChars = shownChars;
        this.howMuchChars = howMuchChars;
        this.charsMode = charsMode;
    }

    public int getCharsMode() {
        return charsMode;
    }

    public void setCharsMode(int charsMode) {
        this.charsMode = charsMode;
    }

    public int getHowMuchChars() {
        return howMuchChars;
    }

    public void setHowMuchChars(int howMuchChars) {
        this.howMuchChars = howMuchChars;
    }

    public boolean isShownChars() {
        return shownChars;
    }

    public void setShownChars(boolean shownChars) {
        this.shownChars = shownChars;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }
}
