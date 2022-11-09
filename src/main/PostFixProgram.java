package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class PostFixProgram
{
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
            equationsByCell = parseFile(csvFile);
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
                    results[row][column] = prepareAndCalculate(equationsByCell[row][column]);
                }
                else
                {
                    results[row][column] = "#ERR";
                }
            }
        }

        for (int row = 0; row < equationsByCell.length; row++)
        {
            for (int column = 0; column < equationsByCell[row].length; column++)
            {
                if (results[row][column] != null && !results[row][column].matches("[0-9]+"))
                {
                    results[row][column] = prepareAndCalculate(equationsByCell[row][column]);
                }
            }
        }
    }

    public static String prepareAndCalculate(String string)
    {
        if (string == null || string.isEmpty() || string.equals(" "))
        {
            return "#ERR";
        }
        else
        {
            return calculate(prepareString(string));
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

    public static String calculate(String string)
    {
        if (string == null || string.isEmpty())
        {
            return "#ERR";
        }
        String[] items = string.split(" ");
        Stack<Float> values = new Stack<>();
        for (String item : items)
        {
            if (item.equals("+") || item.equals("/") || item.equals("*") || item.equals("-"))
            {
                if (values.size() < 2)
                {
                    return "#ERR";
                }
                float a = values.pop();
                float b = values.pop();
                values.push(calculateItems(a, b, item));
            }
            else
            {
                try
                {
                    values.add(Float.parseFloat(item));
                }
                catch (Exception e)
                {
                    return "#ERR";
                }
            }
        }
        if (values.size() > 1)
        {
            return "#ERR";
        }

        BigDecimal total = new BigDecimal(Double.toString(values.pop()));
        total = total.setScale(1, RoundingMode.HALF_UP);

        return total.doubleValue() % 1 == 0 ? String.format("%.0f", total) : total.toString();
    }

    public static float calculateItems(float a, float b, String c)
    {
        switch (c)
        {
            case "+":
                return b + a;
            case "-":
                return b - a;
            case "/":
                return b / a;
            case "*":
                return b * a;
            default:
                return 0;
        }
    }

    public static String[][] parseFile(String fileString) throws FileNotFoundException, IOException
    {
        String[][] numbers = new String[3][3];
        File file = new File(fileString);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        int rowNumber = 0;
        int columnNumber = 0;
        while ((line = bufferedReader.readLine()) != null)
        {
            String[] itemsLine;
            itemsLine = line.split(",");
            for (String item : itemsLine)
            {
                if (item != null)
                {
                    numbers[rowNumber][columnNumber] = item;
                    columnNumber++;
                }
                else
                {
                    break;
                }
            }
            rowNumber++;
            columnNumber = 0;
        }
        bufferedReader.close();
        return numbers;
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
