import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DemoTest {

    // --- POSITIVE TESTS (Valid Triangles) ---
    @Test
    public void testEquilateralTriangle() {
        assertTrue(Demo.isTriangle(5.0, 5.0, 5.0));
    }

    @Test
    public void testIsoscelesTriangle() {
        assertTrue(Demo.isTriangle(5.0, 5.0, 7.0));
    }

    @Test
    public void testScaleneTriangle() {
        assertTrue(Demo.isTriangle(3.0, 4.0, 5.0)); // Right-angled triangle
    }

    // --- NEGATIVE TESTS (Invalid Triangles & Boundaries) ---
    @Test
    public void testViolatesFirstInequality() {
        // 1 + 2 is NOT > 3
        assertFalse(Demo.isTriangle(1.0, 2.0, 3.0));
    }

    @Test
    public void testViolatesSecondInequality() {
        // 1 + 3 is NOT > 5
        assertFalse(Demo.isTriangle(5.0, 1.0, 3.0));
    }

    @Test
    public void testViolatesThirdInequality() {
        // 2 + 3 is NOT > 6
        assertFalse(Demo.isTriangle(6.0, 2.0, 3.0));
    }

    @Test
    public void testWithZero() {
        assertFalse(Demo.isTriangle(0.0, 5.0, 7.0));
    }

    @Test
    public void testWithNegativeNumbers() {
        assertFalse(Demo.isTriangle(-2.0, 5.0, 7.0));
    }

    // --- MUTATION KILLER BOUNDARY TESTS ---
    @Test
    public void testBoundarySumEqualsSideC() {
        // a + b == c -> 1 + 2 == 3. Should be false! If a mutant changes '>' to '>=', this might incorrectly pass.
        assertFalse(Demo.isTriangle(1.0, 2.0, 3.0));
    }

    @Test
    public void testBoundarySumEqualsSideB() {
        // a + c == b -> 1 + 3 == 4.
        assertFalse(Demo.isTriangle(1.0, 4.0, 3.0));
    }

    @Test
    public void testBoundarySumEqualsSideA() {
        // b + c == a -> 2 + 3 == 5.
        assertFalse(Demo.isTriangle(5.0, 2.0, 3.0));
    }

    @Test
    public  void testMainValidTriangle(){
        //Simulate some valid inputs and set inputStream
        String inputs = "3\n4\n5\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(inputs.getBytes());
        System.setIn(inContent);

        //set outputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //Invoke the main method and validate test
        Demo.main(new String[0]);
        String output = outContent.toString();
        assertTrue(output.contains("This is a triangle."));
    }
}