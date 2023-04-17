package compilador;

public class Tokenizer {
    private final String value;
    private final Tokens type;
    private final int line, column;
    
    public Tokenizer(String value, Tokens type, int line, int column) {
    this.value = value;
    this.type = type;
    this.line = line;
    this.column = column;
    }

    public String getValue() {
        return value;
    }

    public Tokens getType() {
        return type;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
    
    public String toString(){
        return "Tokenizer(valor= "+value+", tiopo= "+type+ ", linea= "+line+", columna= "+column+")";
    }
}
