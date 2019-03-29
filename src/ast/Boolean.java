package ast;

import parser.State;

public class Boolean extends Expression
{

    public String value;

    public Boolean(String value)
    {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String gen()
    {
        switch (value){
            case "true":
                return "1";
            case "false":
                return "0";
            default:
                throw new SyntaxError("Boolean value expected, found " + value);
        }
    }

    @Override
    public int eval(State<Integer> state, State<FuncDef> funcDefState) throws Exception {

        switch (value) {
            case "true":
                return 1;
            case "false":
                return 0;
            default:
                throw new SyntaxError("Boolean value expected, found " + value);
        }

    }
}
