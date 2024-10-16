package beans.imports.interfaces;

/**
 *
 * @author michal
 */
public interface IAnswer {
    int getId();
    String getContent();
    int getQuestionId();

    void setId(int id);
    void setContent(String content);
    void setQuestionId(int questionId);
}
