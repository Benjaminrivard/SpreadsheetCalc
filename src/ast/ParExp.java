package ast;

import parser.State;

public class ParExp extends Expression {

    Expression e;

    public ParExp(Expression e)
    {
        this.e = e;
    }

    @Override
    public String gen() {
        return "("+e.gen() +")";
    }

    @Override
    public int eval(State<Integer> state, State<FuncDef> funcDefState) throws Exception {
        return e.eval(state,funcDefState);
    }
}
