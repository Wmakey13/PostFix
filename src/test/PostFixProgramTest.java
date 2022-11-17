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
    public void testRunMainArgsMethodWithCSVFile()
    {
        setSuccessTestData();
        String[] args = { "C:/Users/WK052226/PGit/PostFix/src/test/SWE.csv" };
        postFixProgram.main(args);
        String[][] results = postFixProgram.getResults();
        for (int row = 0; row < successData.length; row++)
        {
            for (int column = 0; column < successData[row].length; column++)
            {
                if (results[row][column] != null)
                {
                    assertTrue(postFixProgram.getResults()[row][column].equals(successData[row][column]));
                }
            }
        }
    }

    @Test
    public void testRunMainArgsMethodTxtFile()
    {
        setSuccessTestData();
        String[] args = { "C:/Users/WK052226/PGit/PostFix/src/test/SWE.txt" };
        postFixProgram.main(args);
        String[][] results = postFixProgram.getResults();
        for (int row = 0; row < successData.length; row++)
        {
            for (int column = 0; column < successData[row].length; column++)
            {
                if (results[row][column] != null)
                {
                    assertTrue(results[row][column].equals(successData[row][column]));
                }
            }
        }
    }

    @Test
    public void testRunMainArgsMethodTxtFile2()
    {
        setSuccessTestData();
        String[] args = { "C:/Users/WK052226/PGit/PostFix/src/test/SWE2.txt" };
        postFixProgram.main(args);
        String[][] results = postFixProgram.getResults();
        for (int row = 0; row < successData.length; row++)
        {
            for (int column = 0; column < successData[row].length; column++)
            {
                if (results[row][column] != null)
                {
                    assertTrue(postFixProgram.getResults()[row][column].equals(successData[row][column]));
                }
            }
        }
    }

    @Test
    public void testSizeOfResultsMatchesSizeFile()
    {
        String[] args = { "C:/Users/WK052226/PGit/PostFix/src/test/OneValue.csv" };
        postFixProgram.main(args);
        String[][] results = postFixProgram.getResults();
        assertTrue(results.length == 1);
        assertTrue(results[0].length == 1);
    }

    @Test
    public void testSizeOfResultsMatchesSizeFile5By2()
    {
        String[] args = { "C:/Users/WK052226/PGit/PostFix/src/test/5by2.csv" };
        postFixProgram.main(args);
        String[][] results = postFixProgram.getResults();
        assertTrue(results.length == 2);
        assertTrue(results[0].length == 5);
    }

    @Test
    public void testSizeOfResultsMatchesSizeOfLargeFile()
    {
        String[] args = { "C:/Users/WK052226/PGit/PostFix/src/test/SWELarge.csv" };
        postFixProgram.main(args);
        String[][] results = postFixProgram.getResults();
        assertTrue(results.length == 230);
        assertTrue(results[0].length == 320);
    }

    @Test
    public void testStandardAddition()
    {
        equationsByCell[0][0] = "4 3 +";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("7"));
    }

    @Test
    public void testStandardMuliplication()
    {
        equationsByCell[0][0] = "4 3 *";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("12"));
    }

    @Test
    public void testStandardSubtraction()
    {
        equationsByCell[0][0] = "4 3 -";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("1"));
    }

    @Test
    public void testStandardDivision()
    {
        equationsByCell[0][0] = "4 2 /";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("2"));
    }

    @Test
    public void testMinusAddition()
    {
        equationsByCell[0][0] = "-4 3 +";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("-1"));
    }

    @Test
    public void testMinusSubtraction()
    {
        equationsByCell[0][0] = "-4 3 -";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("-7"));
    }

    @Test
    public void testMinusMultiplication()
    {
        equationsByCell[0][0] = "-4 3 *";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("-12"));
    }

    @Test
    public void testMinusDivision()
    {
        equationsByCell[0][0] = "-4 2 /";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("-2"));
    }

    @Test
    public void testDecimalAddition()
    {
        equationsByCell[0][0] = "4.5 3.7 +";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        System.out.println(4.5 + 3.7 + "");
        assertTrue(postFixProgram.getResults()[0][0].equals("8.2"));
    }

    @Test
    public void testDecimalSubtraction()
    {
        equationsByCell[0][0] = "4.5 3.7 -";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        System.out.println(postFixProgram.getResults()[0][0]);
        assertTrue(postFixProgram.getResults()[0][0].equals("0.8"));
    }

    @Test
    public void testDecimalMultiplication()
    {
        equationsByCell[0][0] = "4.5 3.7 *";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        System.out.println(postFixProgram.getResults()[0][0]);
        assertTrue(postFixProgram.getResults()[0][0].equals("16.7"));
    }

    @Test
    public void testDecimalDivision()
    {
        equationsByCell[0][0] = "8.8 2.2 /";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("4"));
    }

    @Test
    public void testThreeDigitAndDecimalCalculation()
    {
        equationsByCell[0][0] = "105 1.4 -";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("103.6"));
    }

    @Test
    public void testTwoOperatorCalculation()
    {
        equationsByCell[0][0] = "4 3 + 6 -";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("1"));
    }

    @Test
    public void testTwoOperatorEndCalculation()
    {
        equationsByCell[0][0] = "4 3 6 + *";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("36"));
    }

    @Test
    public void testMultipleOperators()
    {
        equationsByCell[0][0] = "4 3 6 5 7 + + + + ";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("25"));
    }

    @Test
    public void testDoubleDigitMultipleCalculation()
    {
        equationsByCell[0][0] = "40 30 60 50 70 + - + * ";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("-1200"));
    }

    @Test
    public void testCodeRetrievesValuesFromCells()
    {
        equationsByCell[0][0] = "4 3 +";
        equationsByCell[1][0] = "3 2 +";
        equationsByCell[2][0] = "A1 A2 +";
        postFixProgram.setEquationsByCell(equationsByCell, 3, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[2][0].equals("12"));
    }

    @Test
    public void testCodeRetrievesValuesFromCellsWithDecimals()
    {
        equationsByCell[0][0] = "4 3 +";
        equationsByCell[1][0] = "3 2 +";
        equationsByCell[2][0] = "A1 A2 7.5 + -";
        postFixProgram.setEquationsByCell(equationsByCell, 3, 1);
        postFixProgram.traverseCells();
        System.out.println(postFixProgram.getResults()[2][0]);
        assertTrue(postFixProgram.getResults()[2][0].equals("-5.5"));
    }

    @Test
    public void testCodeRemovesSpacesBeforeCalculation()
    {
        equationsByCell[0][0] = "     4  3    + ";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("7"));
    }

    @Test
    public void testErrorWhenEmptyCell()
    {
        equationsByCell[0][0] = "";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNullCell()
    {
        equationsByCell[0][0] = null;
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenTooManyOperatorsFound()
    {
        equationsByCell[0][0] = "3 5 7 + - * - *";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNumberIsAtTheEnd()
    {
        equationsByCell[0][0] = "3 5 7 + - + 7";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNumberAfterOperators()
    {
        equationsByCell[0][0] = "+ - + 3 5 7 7";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNoGaps()
    {
        equationsByCell[0][0] = "123BC-";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorOnInvalidArguments()
    {
        equationsByCell[0][0] = "W 2 -";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorOnLaterInvalidArguments()
    {
        equationsByCell[0][0] = "4 3 +";
        equationsByCell[0][1] = "A1 W +";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 2);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][1].equals("#ERR"));
    }

    @Test
    public void testErrorOnMissingValues()
    {
        equationsByCell[0][0] = "4 3 +";
        equationsByCell[0][1] = "A1 B2 +";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 2);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][1].equals("#ERR"));
    }

    @Test
    public void testErrorWhenInvalidDigitsFound()
    {
        equationsByCell[0][0] = "3 5 7 ! + !";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNoOperatorsFound()
    {
        equationsByCell[0][0] = "3 4 3";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenOnlyOperatorsFound()
    {
        equationsByCell[0][0] = "+ - +";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNotEnoughOperatorsFound()
    {
        equationsByCell[0][0] = "40 30 60 50 70 + + + ";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();

        assertTrue(postFixProgram.getResults()[0][0].equals("#ERR"));
    }

    @Test
    public void testErrorWhenNoReplacementValueFound()
    {
        equationsByCell[0][0] = "A1";
        postFixProgram.setEquationsByCell(equationsByCell, 1, 1);
        postFixProgram.traverseCells();
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
