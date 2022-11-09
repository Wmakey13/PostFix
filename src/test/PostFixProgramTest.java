package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.PostFixProgram;

public class PostFixProgramTest
{
    PostFixProgram postFixProgram = new PostFixProgram();
    String[][] equationsByCell = new String[3][3];
    String[][] successData = new String[3][3];

    @Test
    public void testRunMainArgsMethod()
    {
        setSuccessTestData();
        String[] args = { "C:/Users/WK052226/PGit/PostFix/src/test/SWE.csv" };
        postFixProgram.main(args);
        for (int row = 0; row < equationsByCell.length; row++)
        {
            for (int column = 0; column < equationsByCell[row].length; column++)
            {
                if (postFixProgram.getResults()[row][column] != null)
                {
                    assertTrue(postFixProgram.getResults()[row][column].equals(successData[row][column]));
                }
            }
        }
    }

    @Test
    public void testRunEachMethod()
    {
        setSuccessTestData();
        postFixProgram.readCSVFile("C:/Users/WK052226/PGit/PostFix/src/test/SWE.csv");
        postFixProgram.calculate();
        for (int row = 0; row < equationsByCell.length; row++)
        {
            for (int column = 0; column < equationsByCell[row].length; column++)
            {
                if (postFixProgram.getResults()[row][column] != null)
                {
                    assertTrue(postFixProgram.getResults()[row][column].equals(successData[row][column]));
                }
            }
        }
    }

    @Test
    public void testStandardAddition()
    {
        equationsByCell[0][0] = "4 3 +";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("7"));
    }

    @Test
    public void testStandardMuliplication()
    {
        equationsByCell[0][0] = "4 3 *";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("12"));
    }

    @Test
    public void testStandardSubtraction()
    {
        equationsByCell[0][0] = "4 3 -";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("1"));
    }

    @Test
    public void testStandardDivision()
    {
        equationsByCell[0][0] = "4 2 /";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("2"));
    }

    @Test
    public void testMinusAddition()
    {
        equationsByCell[0][0] = "-4 3 +";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("-1"));
    }

    @Test
    public void testMinusSubtraction()
    {
        equationsByCell[0][0] = "-4 3 -";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("-7"));
    }

    @Test
    public void testMinusMultiplication()
    {
        equationsByCell[0][0] = "-4 3 *";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("-12"));
    }

    @Test
    public void testMinusDivision()
    {
        equationsByCell[0][0] = "-4 2 /";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("-2"));
    }

    @Test
    public void testDecimalAddition()
    {
        equationsByCell[0][0] = "4.5 3.7 +";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("8.2"));
    }

    @Test
    public void testDecimalSubtraction()
    {
        equationsByCell[0][0] = "4.5 3.7 -";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("0.8"));
    }

    @Test
    public void testDecimalMultiplication()
    {
        equationsByCell[0][0] = "4.5 3.7 *";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("16.6"));
    }

    @Test
    public void testDecimalDivision()
    {
        equationsByCell[0][0] = "8.8 2.2 /";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("4"));
    }

    @Test
    public void testThreeDigitAndDecimalCalculation()
    {
        equationsByCell[0][0] = "105 1.4 -";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("103.6"));
    }

    @Test
    public void testTwoOperatorCalculation()
    {
        equationsByCell[0][0] = "4 3 + 6 -";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("1"));
    }

    @Test
    public void testTwoOperatorEndCalculation()
    {
        equationsByCell[0][0] = "4 3 6 + *";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("36"));
    }

    @Test
    public void testMultipleOperators()
    {
        equationsByCell[0][0] = "4 3 6 5 7 + + + + ";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("25"));
    }

    @Test
    public void testDoubleDigitMultipleCalculation()
    {
        equationsByCell[0][0] = "40 30 60 50 70 + - + * ";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.getResults()[0][0].equals("-1200"));
    }

    @Test
    public void testPrepareStringMethodRetrievesValues()
    {
        equationsByCell[0][0] = "4 3 +";
        equationsByCell[1][0] = "3 2 +";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.prepareString("A1 A2 +").equals("7 5 + "));
    }

    @Test
    public void testErrorWhenEmptyCell()
    {
        equationsByCell[0][0] = "";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNullCell()
    {
        equationsByCell[0][0] = null;
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenTooManyOperatorsFound()
    {
        equationsByCell[0][0] = "3 5 7 + - * - *";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNumberIsAtTheEnd()
    {
        equationsByCell[0][0] = "3 5 7 + - + 7";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNumberAfterOperators()
    {
        equationsByCell[0][0] = "+ - + 3 5 7 7";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorOnInvalidArguments()
    {
        equationsByCell[0][0] = "W 2 -";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorOnLaterInvalidArguments()
    {
        equationsByCell[0][0] = "4 3 +";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        equationsByCell[0][2] = "A1 W +";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.getResults()[0][2].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNoOperatorsFound()
    {
        equationsByCell[0][2] = "3 4 3";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.getResults()[0][2].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNotEnoughOperatorsFound()
    {
        equationsByCell[0][0] = "40 30 60 50 70 + + + ";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();

        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNoReplacementValueFound()
    {
        equationsByCell[0][0] = "A1";
        postFixProgram.setEquationsByCell(equationsByCell);
        postFixProgram.calculate();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    public void setSuccessTestData()
    {
        successData[0][0] = "10";
        successData[0][1] = "4";
        successData[0][2] = "-1";
        successData[1][0] = "40";
        successData[1][1] = "10";
        successData[1][2] = "-0.9";
        successData[2][0] = "#ERR";
        successData[2][1] = "#ERR";
        successData[2][2] = "#ERR";

    }
}
