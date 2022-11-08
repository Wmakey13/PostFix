package main;


import java.sql.Array;
import java.util.HashMap;
import java.util.Map;

public class PostFixProgram
{
    CSVParser csvParser;
    static Calculator calculator = new Calculator();
    static String[][] equationsByCell;
    public static String[][] results;

    public static void main(String[] args)
    {
        readCSVFile(args[0]);
        calculate();

        for (int row = 0; row < equationsByCell.length; row++)
        {
            for (int column = 0; column < equationsByCell[row].length; column++)
            {
                System.out.print(results[row][column] + ",");
            }
            System.out.println("");
        }
    }

    public static void readCSVFile(String csvFile)
    {
        try
        {
            equationsByCell = new CSVParser().parseFile(csvFile);
        }
        catch (Exception e)
        {
            System.out.println("File Not Found or unreadable");
        }
    }

    public static void calculate()
    {
        int size = equationsByCell.length;
        results = new String[size][size];

        for (int row = 0; row < equationsByCell.length; row++)
        {
            for (int column = 0; column < equationsByCell[row].length; column++)
            {
                if (equationsByCell[row][column] != null)
                {
                    results[row][column] = calculator.calculate(prepareString(equationsByCell[row][column]));
                }
            }
        }

        for (int row = 0; row < equationsByCell.length; row++)
        {
            for (int column = 0; column < equationsByCell[row].length; column++)
            {
                if (results[row][column] != null && !results[row][column].matches("[0-9]+"))
                {
                    results[row][column] = calculator.calculate(prepareString(equationsByCell[row][column]));
                }
            }
        }
    }

    public static String prepareString(String string)
    {
        StringBuilder preparedString = new StringBuilder();
        String[] items = string.split(" ");
        for (String item : items)
        {
            if (item.length() == 2 && item.matches(".*[a-zA-Z].*"))
            {
                String[] array = item.split("");
                char[] charArray = item.toCharArray();
                int column = Character.toLowerCase(charArray[0]) - 'a';
                int row = Integer.parseInt(array[1]) - 1;
                if (results[row][column] != null)
                {
                    preparedString.append(results[row][column] + " ");
                }
            }
            else
            {
                preparedString.append(item + " ");
            }
        }
        return preparedString.toString();
    }

    // For Testing Purposes
    public void setEquationsByCell(String[][] testEquations)
    {
        equationsByCell = testEquations;
    }

    public String[][] getResults()
    {
        return results;
    }
}
