package logic.question.interfaces;

/**
 *
 * @author michal
 */
public interface IQuestion {
    //getery
    /**
     *
     * @return Identyfikator pytania w bazie danych
     */
    int getId();
    /**
     *
     * @return Zawartość pytania
     */
    String getContent();
    /**
     *
     * @return stopien trudnosci pytania
     */
    int getGrade();
    /**
     *
     * @return typ pytania (otwarte, ABCD, Gap)
     */
    int getType();
    /**
     *
     * @return true, jeżeli pytanie jest otwarte
     */
    boolean isTypeOpened();
    /**
     *
     * @return true, jeżeli pytanie jest typu ABCD
     */
    boolean isTypeABCD();
    /**
     *
     * @return true, jeżeli pytanie jest typu Gap
     */
    boolean isTypeGap();
    //setery
    /**
     *
     * @param id
     */
    void setId(int id);
    /**
     *
     * @param content
     */
    void setContent(String content);
    /**
     *
     * @param grade
     */
    void setGrade(int grade);
    /**
     *
     * @param type
     */
    void setType(int type);
    String getComment();
    void setComment(String comment);
    /**
     *
     * @param isOpened
     */
    void setTypeOpened(boolean isOpened);
    /**
     *
     * @param isABCD
     */
    void setTypeABCD(boolean isABCD);
    /**
     *
     * @param isGap
     */
    void setTypeGap(boolean isGap);
    /**
     * Zwraca obiekt, który przechowuje wszystkie odpowiedzi dotyczące tego pytania.
     * Z jego pomocą można modyfikować pytania, a tekże uzyskać tylko specyficzne z nich (np. tylko gap)
     * 
     * @return Obiekt z odpowiedziami dotyczącymi tego pytania
     */
    IAnswers getAnswers();
    /**
     *Dla wszystkich odpowiedzi danego pytania, ustaw takie id, by rownalo
     * sie id pytania
     * @param quesId
     */
    public void setAnsQuesId(int quesId);

    public String getCategory();

                


    void setAnswers(IAnswers ans);

}
