package logic.TestQuestions;

import data.AnswerABCDSrc;
import data.QuestionSrc;
import data.bean.AnswerABCDSrcBean;
import data.bean.QuestionSrcBean;
import data.interfaces.IQuestionSrc;
import data.staticClass.TableObjectFactory;
import data.staticClass.TableObjectFactoryTest;
import data.staticClass.TypeCalculator;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.question.AnswerABCD;
import logic.question.AnswerGap;
import logic.question.Answers;
import logic.question.Question;
import logic.question.Questions;
import logic.question.interfaces.IAnswerABCD;
import logic.question.interfaces.IQuestion;
import logic.question.interfaces.IQuestions;
import logic.staticClass.TypeConverter;
import org.junit.* ;
import static org.junit.Assert.* ;

public class QuestionsTest {


/*
   @Test
   public void test_addWithoutAnswers() throws ClassNotFoundException, ClassNotFoundException, SQLException {

        Questions ques=new Questions();
        Question quesAd=new Question(TypeCalculator.setABCD(),"ala",1);
       //   ques.add(quesAd);
   }

      @Test
   public void test_addWithAnswers() throws ClassNotFoundException, ClassNotFoundException, SQLException {

        Questions ques=new Questions();
        Question quesAd=new Question(TypeCalculator.setABCD(),"DODANE",1);

        Answers ans=new Answers();
       
        AnswerABCD abcd1=new AnswerABCD("kot",1,true);
        AnswerABCD abcd2=new AnswerABCD("pies",2,false);
        AnswerGap gap1=new AnswerGap(1,"",1);
        AnswerGap gap2=new AnswerGap(2,"",1);

        ans.addAnswer(abcd1);
        ans.addAnswer(abcd2);
        ans.addAnswer(gap1);
        ans.addAnswer(gap2);

        quesAd.setAnswers(ans);
        ques.add(quesAd);

   }

*/
    
    //Test zawiera wszystko od dodawania z odpowiedziami po szukanei pytan i
    //usuwanie ich rowniez z odpowiedziami
      @Test
      public void test_getQuestion() throws SQLException
      {

            Questions ques=new Questions();
            Question quesAd=new Question(TypeCalculator.setGap()+
            TypeCalculator.setABCD(),"DODANE",1);
            Answers ans=new Answers();

            AnswerABCD abcd1=new AnswerABCD("kot",1,true);
            AnswerABCD abcd2=new AnswerABCD("pies",2,false);
            AnswerGap gap1=new AnswerGap(1,"",1);
            AnswerGap gap2=new AnswerGap(2,"",1);

            ans.addAnswer(abcd1);
            ans.addAnswer(abcd2);
            ans.addAnswer(gap1);
            ans.addAnswer(gap2);

            quesAd.setAnswers(ans);
            ques.add(quesAd);


            List<IQuestion> listQues = ques.get(new HashMap());
            assertTrue(listQues.size()==1);
            assertTrue(listQues.get(0).getAnswers().getAnswersGap().size()==2);
            assertTrue(listQues.get(0).getAnswers().getAnswersABCD().size()==2);

            ques.delete(listQues.get(0).getId());
            listQues=ques.get(new HashMap());
            assertTrue(listQues.size()==0);
      }

      @Test
      public void testUpdate() throws SQLException
      {
            Questions quesMan=new Questions();

            Question quesAd=new Question(TypeCalculator.setGap()+
            TypeCalculator.setABCD(),"DODANE",1);
            Answers ans=new Answers();

            AnswerABCD abcd1=new AnswerABCD("kot",1,true);
            AnswerABCD abcd2=new AnswerABCD("pies",2,false);
            AnswerGap gap1=new AnswerGap(1,"",1);
            AnswerGap gap2=new AnswerGap(2,"",1);

            ans.addAnswer(abcd1);
            ans.addAnswer(abcd2);
            ans.addAnswer(gap1);
            ans.addAnswer(gap2);

            quesAd.setAnswers(ans);
            quesMan.add(quesAd);
            List<IQuestion> listQues = quesMan.get(new HashMap());

            quesAd=(Question) listQues.get(0);
            IAnswerABCD tempAns1 = quesAd.getAnswers().getAnswersABCD().get(0);
            IAnswerABCD tempAns2 = quesAd.getAnswers().getAnswersABCD().get(1);

            tempAns1.setContent("UGABUGA");
            quesAd.getAnswers().deleteAnswer(tempAns2);
            quesMan.update(quesAd);
            
            listQues = quesMan.get(new HashMap());
            assertTrue(listQues.size()==1);
            assertTrue(listQues.get(0).getAnswers().getAnswersABCD().size()==1);
            assertTrue(listQues.get(0).getAnswers().getAnswersGap().size()==2);

            assertTrue(listQues.get(0).getAnswers().getAnswersABCD().get(0).getContent().equals("UGABUGA"));

            quesMan.delete(listQues.get(0).getId());

      }

    /*
      @Test
      public void test_delete() throws SQLException
      {
            Questions ques=new Questions();

            Question quesAd=new Question(TypeCalculator.setABCD(),"DODANE",1);
            ques.add(quesAd);

            List<IQuestion> get = ques.get();
            assertTrue(get.size()==1);
            int id=get.get(0).getId();

            ques.delete(id);
            get=ques.get();

            assertTrue(get.size()==0);
      }
*/
    /*
      @Test
      public void test_deleteAnswers() throws SQLException
      {
            Questions ques=new Questions();
            Question quesAd=new Question(TypeCalculator.setABCD(),"DODANE",1);

            Answers ans=new Answers();

            AnswerABCD abcd1=new AnswerABCD("kot",1,true);
            AnswerABCD abcd2=new AnswerABCD("pies",2,false);
            AnswerGap gap1=new AnswerGap(1,"",1);
            AnswerGap gap2=new AnswerGap(2,"",1);

            ans.addAnswer(abcd1);
            ans.addAnswer(abcd2);
            ans.addAnswer(gap1);
            ans.addAnswer(gap2);

            quesAd.setAnswers(ans);
            ques.add(quesAd);
            List<IQuestion> listQues = ques.get();
            
            assertTrue(listQues.size()==1);
            assertTrue(listQues.get(0).getAnswers().getAnswersABCD().size()==2);
//            assertTrue(listQues.get(0).getAnswers().getAnswersGap().size()==2);
  /*
            int id=listQues.get(0).getId();
            ques.delete(id);
            listQues=ques.get();

            assertTrue(listQues.size()==0);
      }
*/

}