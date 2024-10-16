package beans.imports;

/**
 * Klasa statyczna, służy do zamiany typów w postaci flag bitowych na wartości logiczne i na odwrót.
 * Ewentualne zmiany w zapisie typów dla pytań w bazie danych powinny zawierać się tylko w tej klasie.
 *
 * @author michal
 */
public class TypeCalculator {

    private static final int typeOpen = 1;
    private static final int typeGap  = 2;
    private static final int typeABCD = 4;

    private static final int gapHasLetters = 0x01;
    private static final int gapHasUnderlines = 0x02;

    /**
     * Zwraca wartość, która oznacza, że pytanie jest typu Open.
     * Format zapisywany w bazie danych i przekazywany do obiektów operujących w warstwie danych.<br />
     * Przykład: <br />
     * Ustawiamy typ pytania na Open i Gap<br />
     * int type  = (TypeCalculator.setOpen() | TypeCalculator.setGap());
     * @return integer, który jest liczbą oznaczającą typ pytań Open.
     */
    public static int setOpen() {
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
    public static int setGap() {
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
    public static int setABCD() {
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
    public static boolean isOpen(int type) {
	return (type == typeOpen);
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
    public static boolean isGap(int type) {
        return (type == typeGap);
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
    public static boolean isABCD(int type) {
	return (type == typeABCD);
    }

    // --------------------- Answer GAP ----------------------------------------

    /**
     * Sprawdza czy w odpowiedzi GAP mają być wypełnione literami
     * @param type typ odpowiedzi w postaci flag bitowych;
     * @return true, jeżeli typ zawiera flagę oznaczającą odpowiedzi z literami
     */
    public static boolean hasLetters(int type){
        return ((type & gapHasLetters)>0);
    }

    /**
     * Sprawdza czy w odpowiedzi GAP mają mieć podkreśloną ilosc liter
     * literami
     * @param type typ odpowiedzi w postaci flag bitowych;
     * @return true, jeżeli typ zawiera flagę oznaczającą odpowiedzi z
     *        podkresleniami
     */
    public static boolean hasUnderlines(int type){
        return ((type & gapHasUnderlines)>0);
    }

    /**
     * Zwraca wartość, która oznacza, że odpowiedz zawiera litery.
     * Format zapisywany w bazie danych i przekazywany do obiektów operujących w warstwie danych.
     * Przykład:
     * Ustawiamy odpowiedz z podkresleniami i literami
     * int type  = (TypeCalculator.setLetters() | TypeCalculator.setUnderlines());
     * @return integer, który jest liczbą oznaczającą odpowiedzi z literami
     */
    public static int setLetters(){
        return gapHasLetters;
    }
    /**
     * Zwraca wartość, która oznacza, że odpowiedz zawiera podkreślenia.
     * Format zapisywany w bazie danych i przekazywany do obiektów operujących w warstwie danych.
     * Przykład:
     * Ustawiamy odpowiedz z podkresleniami i literami
     * int type  = (TypeCalculator.setLetters() | TypeCalculator.setUnderlines());
     * @return integer, który jest liczbą oznaczającą odpowiedzi z podkreśleniami
     */
    public static int setUnderlines(){
        return gapHasUnderlines;
    }
}
