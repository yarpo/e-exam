/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.question;

import data.bean.AnswerABCDSrcBean;
import data.bean.AnswerGapSrcBean;
import data.bean.QuestionSrcBean;
import data.interfaces.IAnswerABCDSrc;
import data.interfaces.IAnswerGapSrc;
import data.interfaces.IQuestionSrc;
import data.staticClass.TableObjectFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logic.question.interfaces.IAnswerABCD;
import logic.question.interfaces.IAnswerGap;
import logic.question.interfaces.IAnswers;
import logic.question.interfaces.IQuestion;
import logic.question.interfaces.IQuestions;
import logic.staticClass.TypeConverter;

/**
 *
 * @author michal
 */
public class Questions implements IQuestions
{
    private IQuestionSrc quesMan;
    private IAnswerABCDSrc abcdMan;
    private IAnswerGapSrc gapMan;

    public Questions ()
    {
        try
        {
            quesMan = TableObjectFactory.getInstance().getQuestion();
            abcdMan = TableObjectFactory.getInstance().getAnswerABCD();
            gapMan = TableObjectFactory.getInstance().getAnswerGap();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * pobranie listy wszystkich pytan
     * @return lista pytan
     * @throws SQLException
     */
    public List<IQuestion> get (HashMap filter)
            throws SQLException
    {
        List<IQuestion> listQues = new ArrayList<IQuestion>();
        Question ques;

        List<QuestionSrcBean> beans = quesMan.get(filter);


        for (int i = 0; i < beans.size(); i++)
        {
            ques = new Question(0, 0, "null", 0);
            TypeConverter.QuesBeanToQues(ques, beans.get(i), null);

            IAnswers ans = new Answers();
            if (ques.isTypeABCD())
            {
                List<AnswerABCDSrcBean> beanABCD = abcdMan.get(ques.getId());
                List<IAnswerABCD> abcd = new ArrayList<IAnswerABCD>();

                AnswerABCD tempABCD;
                for (int j = 0; j < beanABCD.size(); j++)
                {
                    tempABCD = new AnswerABCD();
                    TypeConverter.ABCDBeanToAnswer(tempABCD, beanABCD.get(j));
                    abcd.add(tempABCD);
                }
                ans.setAnswersABCD(abcd);
            }
            if (ques.isTypeGap())
            {
                List<AnswerGapSrcBean> beanGaps = gapMan.get(ques.getId());
                List<IAnswerGap> gaps = new ArrayList<IAnswerGap>();
                AnswerGap tempGap;
                for (int j = 0; j < beanGaps.size(); j++)
                {
                    tempGap = new AnswerGap();
                    TypeConverter.GapBeanToAnswer(tempGap, beanGaps.get(j));
                    gaps.add(tempGap);
                }
                ans.setAnswersGap(gaps);
            }
            ques.setAnswers(ans);
            listQues.add(ques);
        }

        return listQues;
    }

    /**
     * usuniecie pytania ze wszysktimi odpowiedziami
     * @param ques pytanie
     * @throws SQLException
     */
    public void delete (int quesId)
            throws SQLException
    {

        deleteOldAnswers(quesId);
        quesMan.delete(quesId);
    }

    /**
     * Zmiana pytania wraz z jego odpowiedziami
     * @param ques zmienione pytanie
     * @throws SQLException
     */
    public void update (IQuestion ques)
            throws SQLException
    {

        QuestionSrcBean bean = new QuestionSrcBean();
        TypeConverter.QuesToQuesBean(bean, ques);
        quesMan.update(bean);

        //odpowiedzi
        deleteOldAnswers(ques.getId());
        if (ques.getAnswers() != null)
        {
            ques.setAnsQuesId(ques.getId());
            addAnswers(ques);
        }
    }

    /**
     * Dodawanie pytania wraz z odpowiedziami
     * @param ques
     * @throws SQLException
     */
    public void add (IQuestion ques)
            throws SQLException
    {

        QuestionSrcBean bean = new QuestionSrcBean();
        TypeConverter.QuesToQuesBean(bean, ques);

        System.out.println("CatQues" + bean.getCategory());
        quesMan.add(bean);

        int quesId = quesMan.getLastQuesId();

        System.out.print("QUESID:" + quesId);
        if (ques.getAnswers() != null)
        {
            ques.setAnsQuesId(quesId);
            addAnswers(ques);
        }
    }

    /**
     * W moemncie "updateowania" pytania, nalezy usunac wszystkie jego stare
     * odpowiedzi
     * @param quesId
     * @throws SQLException
     */
    private void deleteOldAnswers (int quesId)
            throws SQLException
    {
        List<AnswerGapSrcBean> gapBeans = gapMan.get(quesId);
        List<AnswerABCDSrcBean> abcdBeans = abcdMan.get(quesId);

        for (int i = 0; i < gapBeans.size(); i++)
        {
            gapMan.delete(gapBeans.get(i).getId());
        }
        for (int i = 0; i < abcdBeans.size(); i++)
        {
            abcdMan.delete(abcdBeans.get(i).getId());
        }
    }

    /**
     * Dodaj odpowiedzi zwiazane z okreslonym pytaniem
     * @param ques pytanie, do ktorego zostana dolaczone odpowiedzi
     */
    private void addAnswers (IQuestion ques)
            throws SQLException
    {
        List<IAnswerGap> ansGap = ques.getAnswers().getAnswersGap();
        List<IAnswerABCD> ansAbcd = ques.getAnswers().getAnswersABCD();

        AnswerABCDSrcBean beanAbcd = new AnswerABCDSrcBean();
        AnswerGapSrcBean beanGap = new AnswerGapSrcBean();

        for (int i = 0; i < ansAbcd.size(); i++)
        {
            beanAbcd = new AnswerABCDSrcBean();
            TypeConverter.AnswertoABCDBean(beanAbcd, ansAbcd.get(i));
            System.out.println("QUESID:" + beanAbcd.getQuestionId() + "CON:" + beanAbcd.
                    getContent()
                    + "TYPE:" + beanAbcd.isCorrect());
            abcdMan.add(beanAbcd);
        }
        for (int i = 0; i < ansGap.size(); i++)
        {
            beanGap = new AnswerGapSrcBean();
            TypeConverter.AnswertoGapBean(beanGap, ansGap.get(i));

            gapMan.add(beanGap);
        }
    }
}
