/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.exams;

/**
 *
 * @author yarpo
 */
public interface ISection {

    /**
     * stale dla atrybutu type
     * Pierdolne zaraz - j0jo
     */
    static final int OPENED = 1;
    static final int GAP = 2;
    static final int ABCD = 4;


    public void setExamId(int examId);

    public int getExamId();

    /**
     * zwraca identyfikator typu sekcji
     */
    public int getType();

    public int getGradeMin();
    public int getGradeMax();
    public int getPoints();
    public int getQuestionsNo();
    public int getId();
    public String getName();

    /**
     * zapisuje sekcje
     */
    public void save() throws Exception;

    /**
     * zapisuje zmiany w sekcji
     */
    public void update() throws Exception;

    /**
     * usuwa sekcje
     */
    public void delete() throws Exception;

    /**
     * Pobiera sekcje po zadanym id
     */
    public void getById(int id);

    public void setCategory(int category);

    public int getCategory();
}
