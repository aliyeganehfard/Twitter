package controller.exception;



import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExceptionHandling {

    public static void isWord(String word) {
        for (char ch : word.toLowerCase().toCharArray())
            if (!(ch >= 97 && ch <= 122))
                throw new WordException();
    }


    public static void isDigit(String digit) {
        for (char chr : digit.toCharArray())
            if (!Character.isDigit(chr))
                throw new DigitException();
    }

    public static void isDigit(Integer digit) {
        isDigit(String.valueOf(digit));
    }

}
