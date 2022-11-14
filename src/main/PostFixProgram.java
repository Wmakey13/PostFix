package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Stack;

public class PostFixProgram
{
    static String[][] equationsByCell;
    static String[][] results;
    static int rowNumber;
    static int columnNumber;

    public static void main(String[] args)
    {
        String filePath = args[0];
        readCSVFile(filePath);
        traverseCells();
        writeCSVFile(filePath);
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

    public static void writeCSVFile(String csvFile)
    {
        try
        {
            writeFile(csvFile);
        }
        catch (Exception e)
        {
            System.out.println("File Not writable");
        }
    }

    public static void traverseCells()
    {
        results = new String[rowNumber][columnNumber];

        for (int row = 0; row < rowNumber; row++)
        {
            for (int column = 0; column < columnNumber; column++)
            {
                if (equationsByCell[row][column] != null)
                {
                    results[row][column] = calculateEquations(equationsByCell[row][column]);
                }
                else
                {
                    results[row][column] = "#ERR";
                }
            }
        }

        for (int row = 0; row < rowNumber; row++)
        {
            for (int column = 0; column < columnNumber; column++)
            {
                if (results[row][column] != null && !results[row][column].matches("[0-9]+"))
                {
                    results[row][column] = calculateEquations(equationsByCell[row][column]);
                }
            }
        }
    }

    public static String calculateEquations(String string)
    {
        if (string == null || string.isEmpty() || string.equals(" "))
        {
            return "#ERR";
        }

        String[] items = string.split(" ");
        Stack<Float> values = new Stack<>();
        for (String item : items)
        {
            if (!item.isEmpty())
            {
                if (item.length() == 2 && item.matches(".*[a-zA-Z].*"))
                {
                    String[] array = item.split("");
                    char[] charArray = item.toCharArray();
                    int column = Character.toLowerCase(charArray[0]) - 'a';
                    int row = Integer.parseInt(array[1]) - 1;
                    if (row < rowNumber && column < columnNumber && results[row][column] != null
                            && !results[row][column].equals("#ERR"))
                    {
                        values.add(Float.parseFloat(results[row][column]));
                    }
                }
                else if (item.equals("+") || item.equals("/") || item.equals("*") || item.equals("-"))
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
        }
        if (values.size() > 1 || values.isEmpty())
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
        String[][] numbers = new String[10][10];
        File file = new File(fileString);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        rowNumber = 0;
        while ((line = bufferedReader.readLine()) != null)
        {
            String[] itemsLine;
            itemsLine = line.split(",");
            if (itemsLine.length > columnNumber)
            {
                columnNumber = itemsLine.length;
            }
            int tempColumn = 0;
            for (String item : itemsLine)
            {
                if (item != null)
                {
                    numbers[rowNumber][tempColumn] = item;
                    tempColumn++;
                }
                else
                {
                    break;
                }
            }
            rowNumber++;
        }
        bufferedReader.close();

        return numbers;
    }

    public static void writeFile(String fileString) throws FileNotFoundException, IOException
    {
        String filePath = fileString.substring(0, fileString.lastIndexOf("."));
        File file = new File(filePath + "Output.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);

        for (int row = 0; row < rowNumber; row++)
        {
            StringBuilder resultLine = new StringBuilder();
            for (int column = 0; column < columnNumber; column++)
            {
                resultLine.append(results[row][column]);
                if (column < results[row].length - 1)
                {
                    resultLine.append(",");
                }
            }
            String resultsLine = resultLine.toString();
            bufferedWriter.write(resultsLine);
            bufferedWriter.newLine();
            System.out.println(resultsLine);
        }

        bufferedWriter.close();
    }

    // For Testing Purposes
    public void setEquationsByCell(String[][] testEquations, int rowNumber, int columnNumber)
    {
        equationsByCell = testEquations;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public String[][] getResults()
    {
        return results;
    }
}
