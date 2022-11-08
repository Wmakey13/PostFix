package main;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CSVParser
{
    protected BufferedReader bufferedReader = null;

    public String[][] parseFile(String fileString) throws FileNotFoundException, IOException
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
}
