import junit.framework.TestCase;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Kemp on 4/29/2016.
 * test cases to test application functionality
 */
public class TestApplicationTests extends TestCase {
    private PrintStream myOut;
    private ByteArrayOutputStream myBAOS;
    private PrintStream oldStream;


    //redirect out stream for each case
    public void setUp(){
        oldStream = System.out;
        myBAOS = new ByteArrayOutputStream();
        myOut = new PrintStream(myBAOS);
    }

    //put out stream back to correct console stream
    public void tearDown() throws Exception{
        System.setOut(oldStream);
        myBAOS.flush();
        myBAOS.close();
    }

    public void testMain(){
        String myUserInputString = "5";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        System.setIn(myIn);
        System.setOut(myOut);
        Application.main(null);
        String toContain = "5. Exit";
        assertTrue(myBAOS.toString().contains(toContain));
    }

    public void testMenuExitChoice(){
        String myUserInputString = "5";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.startApp();
        String toContain = "5. Exit";
        assertTrue(myBAOS.toString().contains(toContain));
    }
    public void testMenuInvalidOptionThenExit(){
        String myUserInputString = "A"+"\n5";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.startApp();
        String toContain = "Error in choice, please reenter choice.";
        assertTrue(myBAOS.toString().contains(toContain));
    }

    public void testMenuSelectBMI(){
        String myUserInputString = "1"+"\n5";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.startApp();
        String toContain = "Welcome to Body Mass Index Menu";
        assertTrue(myBAOS.toString().contains(toContain));
    }
    public void testMenuSelectDistanceFormula(){
        String myUserInputString = "2"+"\nN"+"\n5";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.startApp();
        String toContain = "Welcome to Distance Formula Menu";
        assertTrue(myBAOS.toString().contains(toContain));
    }
    public void testMenuSelectRetirement(){
        String myUserInputString = "3"+"\n5";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.startApp();
        String toContain = "Welcome to Retirement Menu";
        assertTrue(myBAOS.toString().contains(toContain));
    }
    public void testMenuSelectEmailVerifier(){
        String myUserInputString = "4"+"\n5";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.startApp();
        String toContain = "Welcome to Email Verifier Menu";
        assertTrue(myBAOS.toString().contains(toContain));
    }

    public void testMenuOptionWithIncorrectValue(){
        int menuOption = 0;
        Application myApp = new Application();
        myApp.menuOptionHandler(menuOption);
        assertTrue(myApp.errorInChoice);
    }

    public void testMenuOptionWithCorrectValue(){
        int menuOption = 1;
        Application myApp = new Application(myOut);
        myApp.menuOptionHandler(menuOption);
        assertFalse(myApp.errorInChoice);
    }


    //http://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
    public void testGetUserInputWithPlainNumberOne(){
        String myUserInputString = "1";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        String messageToPass = "";
        Application myApp = new Application(myOut,myIn);
        String userInput = myApp.getUserInput(myApp.userInput,messageToPass);
        assertEquals("1",userInput);
    }

    public void testGetUserInputWithWord(){
        InputStream myIn = new ByteArrayInputStream("Hello".getBytes());
        System.setIn(myIn);
        String messageToPass = "";
        Application myApp = new Application(myOut,myIn);
        String userInput = myApp.getUserInput(new Scanner(myIn),messageToPass);
        assertEquals("Hello",userInput);
    }

    public void testUserInputIsIntegerPassCase(){
        assertTrue(Application.isInteger("1"));
    }

    public void testUserInputIsIntegerFailCase(){
        assertFalse(Application.isInteger("A"));
    }

    public void testVerifyUserInputWithInteger(){
        Application myApp = new Application();
        myApp.verifyUserInputIsInteger("1");
        assertFalse(myApp.errorInChoice);
        assertEquals(1,myApp.userChoice);
    }

    public void testVerifyUserInputWithLetter(){
        Application myApp = new Application();
        myApp.verifyUserInputIsInteger("A");
        assertTrue(myApp.errorInChoice);
    }

    public void testDistanceMenuAllCorrectValues(){
        String myUserInputString = "Y"+"\n1"+"\n1"+"\n2"+"\n2"+"\nN";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.distanceFormulaMenu();
        assertTrue(myBAOS.toString().contains("Distance is: 2.0"));
    }
    public void testDistanceMenuAllIncorrectX1(){
        String myUserInputString = "Y"+"\nA"+"\nN";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.distanceFormulaMenu();
        assertTrue(myBAOS.toString().contains("Error in input, please retry."));
    }
    public void testDistanceMenuAllIncorrectX2(){
        String myUserInputString = "Y"+"\n1"+"\n1"+"\nA"+"\nN";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.distanceFormulaMenu();
        assertTrue(myBAOS.toString().contains("Error in input, please retry."));
    }
    public void testDistanceMenuAllIncorrectY1(){
        String myUserInputString = "Y"+"\n1"+"\nA"+"\nN";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.distanceFormulaMenu();
        assertTrue(myBAOS.toString().contains("Error in input, please retry."));
    }
    public void testDistanceMenuAllIncorrectY2(){
        String myUserInputString = "Y"+"\n1"+"\n1"+"\n2"+"\nA"+"\nN";
        InputStream myIn = new ByteArrayInputStream(myUserInputString.getBytes());
        Application myApp = new Application(myOut,myIn);
        myApp.distanceFormulaMenu();
        assertTrue(myBAOS.toString().contains("Error in input, please retry."));
    }


    public void testBMIMenu(){

    }

    public void testEmailMenu(){

    }

    public void testRetirementMenu(){

    }
}
