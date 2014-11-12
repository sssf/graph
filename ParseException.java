public class ParseException extends Exception {
    public ParseException(String line, int lineNumber) {
        super("Invalid data on line: "+lineNumber+"\n"+ line);
    }
}
