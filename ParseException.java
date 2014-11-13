public class ParseException extends Exception {
    public String line;
    public int lineNumber;

    public ParseException(String line, int lineNumber) {
        super("Invalid data on line: "+lineNumber+"\n"+ line);
        this.line = "" + line; // create copy of string
        this.lineNumber = lineNumber;
    }

    public String getLine() {
        return line;
    }

    public int getLine() {
        return lineNumber;
    }


}
