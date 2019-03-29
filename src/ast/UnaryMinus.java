package ast;

import parser.State;

public class UnaryMinus extends Expression {

    Expression e;
    public UnaryMinus(Expression e){
        this.e = e;
    }

    @Override
    public String gen() {

        if(e instanceof ast.Boolean) {
            return "!" + e.gen();
        }

        return "-"+e.gen();

    }

    @Override
    public int eval(State<Integer> state, State<FuncDef> stateFunc) throws Exception
    {

        return -e.eval(state,stateFunc);
    }

}
