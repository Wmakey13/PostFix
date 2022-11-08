package main;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class Calculator
{

    public String calculate(String string)
    {
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

    public float calculateItems(float a, float b, String c)
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

}
