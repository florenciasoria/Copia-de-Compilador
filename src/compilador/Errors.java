
package compilador;

public class Errors {
    private final String value, typeError;
    private final Tokens type;
    private final int line, column;
    
    public Errors(String value, Tokens type, int line, int column, String typeError) {
    this.value = value;
    this.type = type;
    this.line = line;
    this.column = column;
    this.typeError = typeError;
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
    
      public String getTypeError() {
        return typeError;
    }
    
    public String toString(){
        return "Errors(valor= "+value+", tiopo= "+type+ ", linea= "+line+", columna= "+column+")";
    }
}
