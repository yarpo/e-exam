/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.staticClass;

import data.bean.AnswerABCDSrcBean;
import data.bean.AnswerGapSrcBean;
import data.bean.QuestionSrcBean;
import logic.question.Question;
import logic.question.interfaces.IAnswerABCD;
import logic.question.interfaces.IAnswerGap;
import logic.question.interfaces.IAnswers;
import logic.question.interfaces.IQuestion;

public class TypeConverter
{
    public static void QuesBeanToQues (Question ques, QuestionSrcBean quesBean,
            IAnswers iAns)
    {
        ques.setId(quesBean.getId());
        ques.setType(quesBean.getType());
        ques.setContent(quesBean.getContent());
        ques.setGrade(quesBean.getGrade());
        ques.setComment(quesBean.getComment());
        ques.setCategory(quesBean.getCategory());

        if (iAns != null)
        {
            ques.setAnswers(iAns);
        }
    }

    public static void QuesToQuesBean (QuestionSrcBean quesBean, IQuestion ques)
    {
        quesBean.setId(ques.getId());
        quesBean.setType(ques.getType());
        quesBean.setContent(ques.getContent());
        quesBean.setGrade(ques.getGrade());
        quesBean.setComment(ques.getComment());
        quesBean.setCategory(ques.getCategory());
    }

    public static void GapBeanToAnswer (IAnswerGap ans, AnswerGapSrcBean gapBean)
    {
        ans.setId(gapBean.getId());
        ans.setGapNumber(gapBean.getGapNumber());
        ans.setContent(gapBean.getContent());
        ans.setQuestionId(gapBean.getQuestionId());
    }

    public static void ABCDBeanToAnswer (IAnswerABCD ans,
            AnswerABCDSrcBean abcdBean)
    {
        ans.setId(abcdBean.getId());
        ans.setContent(abcdBean.getContent());
        ans.setQuestionId(abcdBean.getQuestionId());
        ans.setCorrect(abcdBean.isCorrect());
    }

    public static void AnswertoGapBean (AnswerGapSrcBean gapBean, IAnswerGap ans)
    {
        gapBean.setId(ans.getId());
        gapBean.setGapNumber(ans.getGapNumber());
        gapBean.setContent(ans.getContent());
        gapBean.setQuestionId(ans.getQuestionId());
    }

    public static void AnswertoABCDBean (AnswerABCDSrcBean abcdBean,
            IAnswerABCD ans)
    {
        abcdBean.setId(ans.getId());
        abcdBean.setContent(ans.getContent());
        abcdBean.setQuestionId(ans.getQuestionId());
        abcdBean.setCorrect(ans.isCorrect());
    }
}
