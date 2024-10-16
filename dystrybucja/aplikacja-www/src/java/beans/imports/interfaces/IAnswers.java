package beans.imports.interfaces;

import java.util.List;

/**
 *
 * @author michal
 */
public interface IAnswers {
    /**
     *
     * @return listę wszystkich odpowiedzi, ktore dotyczą danego pytania
     */
    List<IAnswer> getAllAnswers();
    /**
     *
     * @return 
     */
    List<IAnswerABCD> getWrongAnswersABCD();
    /**
     *
     * @return
     */
    List<IAnswerABCD> getCorrectAnswersABCD();
    /**
     *
     * @return
     */
    List<IAnswerGap> getAnswersGap();

    List<IAnswerABCD> getAnswersABCD();

    void setAnswersABCD(List<IAnswerABCD> abcd);

    void setAnswersGap(List<IAnswerGap> gaps);
    /**
     *
     * @param answer
     */
    void addAnswer(IAnswerABCD answer);
    /**
     *
     * @param answer
     */
    void addAnswer(IAnswerGap answer);
    /**
     *
     * @param answer
     */
    void deleteAnswer(IAnswerABCD answer);
    /**
     *
     * @param answer
     */
    void deleteAnswer(IAnswerGap answer);
}
