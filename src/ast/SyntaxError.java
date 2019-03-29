package ast;

public class SyntaxError extends RuntimeException{

    public SyntaxError(String msg)
    {
        super(msg);
    }
    public SyntaxError(){super();}
}
