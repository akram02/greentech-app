package tech.coderhub.basic;

public class MyParser {
    public static Double parseDouble(String number) {
        if (number==null || number.isEmpty() || number.equals(".")) {
            number = "0.0";
        }
        return Double.parseDouble(number);
    }
    public static int parseInteger(String number) {
        if (number==null || number.isEmpty() || number.equals(".")) {
            number = "0";
        }
        return Integer.parseInt(number);
    }
}