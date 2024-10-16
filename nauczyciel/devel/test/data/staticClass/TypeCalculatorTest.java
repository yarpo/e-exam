package data.staticClass;

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
public class TypeCalculatorTest {

    public TypeCalculatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    @Test
    public void OpenTest() {
	assertTrue(TypeCalculator.isOpen(1));
	assertTrue(TypeCalculator.isOpen(5));
	assertFalse(TypeCalculator.isOpen(2));
	assertFalse(TypeCalculator.isOpen(6));
	assertEquals(1, TypeCalculator.setOpen());
    }

    @Test
    public void GapTest() {
	assertTrue(TypeCalculator.isGap(2));
	assertTrue(TypeCalculator.isGap(3));
	assertFalse(TypeCalculator.isGap(4));
	assertFalse(TypeCalculator.isGap(5));
	assertEquals(2, TypeCalculator.setGap());
    }

    @Test
    public void ABCDTest() {
	assertTrue(TypeCalculator.isABCD(4));
	assertTrue(TypeCalculator.isABCD(7));
	assertFalse(TypeCalculator.isABCD(3));
	assertFalse(TypeCalculator.isABCD(9));
	assertEquals(4, TypeCalculator.setABCD());
    }
}