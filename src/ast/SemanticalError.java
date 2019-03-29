package ast;

public class SemanticalError extends RuntimeException {

    public SemanticalError(String msg)
    {
        super(msg);
    }

    public SemanticalError()
    {
        super();
    }
}
