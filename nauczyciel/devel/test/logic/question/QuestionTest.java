/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.question;

import data.staticClass.TypeCalculatorTest;
import logic.question.interfaces.IQuestion;
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
public class QuestionTest {

    public QuestionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
	this.question = new Question(3, "dupa", 3);
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
    public void testSetOpen() {
	assertTrue(this.question.isTypeGap());
	assertTrue(this.question.isTypeOpened());
	this.question.setTypeOpened(false);
	assertTrue(this.question.isTypeGap());
	assertFalse(this.question.isTypeOpened());
	this.question.setTypeOpened(true);
	assertTrue(this.question.isTypeGap());
	assertTrue(this.question.isTypeOpened());
    }
    private IQuestion question;
}