package ast;

public enum Op {
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/"),
    EQUAL("=="),
    LESS("<"),
    NOTEQUAL("!="),
    GREATER(">"),
    GREATERORE(">="),
    LESSERORE("<="),
    AND("&&"),
    OR("||");

    Op(String op)
    {
        this.symbole = op;
    }

    private String symbole;

    public static Op parseOP(String Op)
    {
        switch (Op) {
            case "+" :
                return PLUS;
            case "-":
                return MINUS;
            case "*":
                return TIMES;
            case "/":
                return DIVIDE;
            case "==":
                return EQUAL;
            case "<":
                return LESS;
            case "&&":
                return AND;
            case "||":
                return OR;
            case "!=":
                return NOTEQUAL;
            case ">":
                return GREATER;
            case "=>":
                return GREATERORE;
            case "<=":
                return LESSERORE;
            default:
                throw new RuntimeException("Expected operator , found " + Op); //TODO : refine
        }
    }

    public String gen()
    {
        return symbole;
    }


}
