/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.question;

import java.util.List;
import logic.question.interfaces.IAnswerABCD;
import logic.question.interfaces.IAnswers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michal
 */
public class AnswersTest {

    public AnswersTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
	this.answers = new Answers();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void testDeleteABCD() {
	this.answers.addAnswer(new AnswerABCD("dupa", 1, true));
	this.answers.addAnswer(new AnswerABCD("zbita", 1, false));

	List lista = this.answers.getCorrectAnswersABCD();
	IAnswerABCD bean = (IAnswerABCD) lista.get(0);
	this.answers.deleteAnswer(bean);
	lista = this.answers.getCorrectAnswersABCD();
	assertTrue(lista.isEmpty());
    }

    private IAnswers answers;
}