package ast;

import parser.State;

public class ConditionalExpression extends Expression {

    public Expression e1, e2 ,e3;

    public ConditionalExpression(Expression e1, Expression e2, Expression e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    @Override
    public String gen() {
        return e1.gen()+ "?" + e2.gen()+":"+e3.gen();
    }

    @Override
    public int eval(State<Integer> state,State<FuncDef> stateFunc) throws Exception
    {
        return e1.eval(state,stateFunc) == 0 ? e3.eval(state,stateFunc) : e2.eval(state,stateFunc);
    }
}
