package compilador;

import java.util.Comparator;

public class OrderLine implements Comparator<Tokenizer>{
    public int compare(Tokenizer o1, Tokenizer o2) {
        if((o1.getLine()<o2.getLine())){
            return -1;
        } else if ((o1.getLine()<o2.getLine())){
            return 0;
        } else {
            return 1;
        }
    }
}
