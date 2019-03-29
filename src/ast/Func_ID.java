package ast;


import parser.State;

public class Func_ID extends Expression {

    public String value;

    @Override
    public int eval(State<Integer> state, State<FuncDef> funcDefState) {
        return 0;
    }

    @Override
    public String gen() {
        return value;
    }

    public Func_ID(String value)
    {
        this.value = value;
    }
}
