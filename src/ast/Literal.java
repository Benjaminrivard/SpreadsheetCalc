package ast;

import parser.State;

public class Literal extends Expression {
    public int value;

    public Literal(int value) {
        this.value = value;
    }

    @Override
    public String gen() {
        return Integer.toString(value);
    }

    public int eval(State<Integer> state, State<FuncDef> funcDefState)
    {
        return this.value;
    }
}
