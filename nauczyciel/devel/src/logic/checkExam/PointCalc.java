/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.checkExam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sokol
 * Klasa odpowiedzilana za zapisywanie pliku z wynikami i podlicznaie punktow
 * Pewnie wypadaloby rozbic na 2 klasy
 */
public class PointCalc
{
    protected static String SEPARATOR = ";";
    protected static String EOL = "\n";
    protected static String ESCAPE_CHAR = "\\";
    protected static String BOUND = "\"";
    protected static String HEADER_CSV = "\"Klucz studenta\"; punkty" + EOL;

    public void export (String fileName, List<String> keys,
            List<ArrayList<Integer>> points)
    {
        save(fileName, keys, calcPoints(points));
    }

    private void save (String fileName, List<String> keys, List<Integer> points)
    {
        BufferedWriter bufWrite;
        try
        {
            bufWrite = new BufferedWriter(new FileWriter(fileName));
            bufWrite.write(HEADER_CSV);

            for (int i = 0; i < keys.size(); i++)
            {
                String res = escapeCSV(keys.get(i)) + SEPARATOR
                        + Double.toString(points.get(i)) + EOL;
                bufWrite.write(res);
            }
            bufWrite.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Zabezpiecza przed podaniem stringa psujacego CSV
     */
    private String escapeCSV (String s)
    {
        s = replace(s, SEPARATOR, ESCAPE_CHAR + SEPARATOR);
        s = replace(s, BOUND, ESCAPE_CHAR + BOUND);
        s = replace(s, EOL, ESCAPE_CHAR + EOL);
        return BOUND + s + BOUND;
    }

    public String replace (String s, String f, String r)
    {
        if (s == null)
        {
            return s;
        }
        if (f == null)
        {
            return s;
        }
        if (r == null)
        {
            r = "";
        }

        int index01 = s.indexOf(f);
        while (index01 != -1)
        {
            s = s.substring(0, index01) + r + s.substring(index01 + f.length());
            index01 += r.length();
            index01 = s.indexOf(f, index01);
        }
        return s;
    }

    private List<Integer> calcPoints (List<ArrayList<Integer>> points)
    {
        List<Integer> calcPoints = new ArrayList<Integer>();

        for (int i = 0; i < points.size(); i++)
        {
            int res = 0;
            for (int j = 0; j < points.get(i).size(); j++)
            {
                res += points.get(i).get(j);
            }
            calcPoints.add(res);
        }

        return calcPoints;
    }
}
