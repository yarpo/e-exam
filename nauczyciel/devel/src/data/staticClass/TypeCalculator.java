package data.staticClass;
/**
 * Klasa statyczna, służy do zamiany typów w postaci flag bitowych na wartości logiczne i na odwrót.
 * Ewentualne zmiany w zapisie typów dla pytań w bazie danych powinny zawierać się tylko w tej klasie.
 *
 * @author michal
 */
public class TypeCalculator
{
    private static int typeOpen = 1;
    private static int typeGap = 2;
    private static int typeABCD = 4;

    /**
     * Zwraca wartość, która oznacza, że pytanie jest typu Open.
     * Format zapisywany w bazie danych i przekazywany do obiektów operujących w warstwie danych.<br />
     * Przykład: <br />
     * Ustawiamy typ pytania na Open i Gap<br />
     * int type  = (TypeCalculator.setOpen() | TypeCalculator.setGap());
     * @return integer, który jest liczbą oznaczającą typ pytań Open.
     */
    public static int setOpen ()
    {
        return typeOpen;
    }

    /**
     * Zwraca wartość, która oznacza, że pytanie jest typu Open.
     * Format zapisywany w bazie danych i przekazywany do obiektów operujących w warstwie danych.<br />
     * Przykład <br />
     * Ustawiamy typ pytania na Open i Gap<br />
     * int type  = (TypeCalculator.setOpen() | TypeCalculator.setGap());
     * @return integer, który jest liczbą oznaczającą typ pytań Gap.
     */
    public static int setGap ()
    {
        return typeGap;
    }

    /**
     * Zwraca wartość, która oznacza, że pytanie jest typu Open.
     * Format zapisywany w bazie danych i przekazywany do obiektów operujących w warstwie danych.<br />
     * Przykład <br />
     * Ustawiamy typ pytania na Open i ABCD<br />
     * int type  = (TypeCalculator.setOpen() | TypeCalculator.setABCD());
     * @return integer, który jest liczbą oznaczającą typ pytań ABCD.
     */
    public static int setABCD ()
    {
        return typeABCD;
    }

    /**
     * Sprawdza, czy pytanie zadanego type jest typu otwartego.<br />
     * Przykład:<br />
     * QuestionSrcBean pytanie = new QuestionSrcBean(...);<br />
     * if (TypeCalculator.isOpen(pytanie.getType())) {<br />
     *	    //pytanie jest typu otwartego<br />
     * }
     * @param type typ pytania w postaci flag bitowych;
     * @return true, jeżeli typ zawiera flagę oznaczającą pytania otwarte
     */
    public static boolean isOpen (int type)
    {
        return ((type & typeOpen) > 0);
    }

    /**
     * Sprawdza, czy pytanie zadanego type jest typu Gap.<br />
     * Przykład:<br />
     * QuestionSrcBean pytanie = new QuestionSrcBean(...);<br />
     * if (TypeCalculator.isGap(pytanie.getType())) {<br />
     *	    //pytanie jest typu Gap<br />
     * }
     * @param type typ pytania w postaci flag bitowych;
     * @return true, jeżeli typ zawiera flagę oznaczającą pytania z luką
     */
    public static boolean isGap (int type)
    {
        return ((type & typeGap) > 0);
    }

    /**
     * Sprawdza, czy pytanie zadanego type jest typu ABCD.<br />
     * Przykład:<br />
     * QuestionSrcBean pytanie = new QuestionSrcBean(...);<br />
     * if (TypeCalculator.isABCD(pytanie.getType())) {<br />
     *	    //pytanie jest typu ABCD<br />
     * }
     * @param type typ pytania w postaci flag bitowych;
     * @return true, jeżeli typ zawiera flagę oznaczającą pytania ABCD
     */
    public static boolean isABCD (int type)
    {
        return ((type & typeABCD) > 0);
    }
}
