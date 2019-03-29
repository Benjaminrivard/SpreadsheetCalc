package ast;

import parser.State;

public class BinaryExpression extends Expression {

    public Expression e1, e2;
    public Op op;

    public BinaryExpression(Op op,Expression e1, Expression e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }


    @Override
    public int eval(State<Integer> state, State<FuncDef> stateFunc) throws Exception
    {
        switch (op) {
            case MINUS:
                return e1.eval(state,stateFunc) - e2.eval(state,stateFunc);
            case PLUS:
                return e1.eval(state,stateFunc) + e2.eval(state,stateFunc);
            case TIMES:
                return e1.eval(state,stateFunc) * e2.eval(state,stateFunc);
            case DIVIDE:
                return e1.eval(state,stateFunc) / e2.eval(state,stateFunc);
            case EQUAL:
                return e1.eval(state,stateFunc) == (e2.eval(state,stateFunc)) ? 1 : 0;
            case LESS:
                return e1.eval(state,stateFunc) < e2.eval(state,stateFunc) ? 1 : 0;
            case LESSERORE:
                return e1.eval(state,stateFunc) <= e2.eval(state,stateFunc) ? 1 : 0;
            case OR:
                return (e1.eval(state,stateFunc) !=0  ||  e2.eval(state,stateFunc) != 0 ) ? 1 : 0;
            case AND:
                return (e1.eval(state,stateFunc) != 0 && e2.eval(state,stateFunc) != 0 ) ? 1 : 0;
            case GREATER:
                return e1.eval(state,stateFunc) > e2.eval(state, stateFunc) ? 1 : 0;
            case NOTEQUAL:
                return e1.eval(state, stateFunc) != e2.eval(state,stateFunc) ? 1 : 0;
            case GREATERORE:
                return (e1.eval(state, stateFunc) >= e2.eval(state,stateFunc)) ? 1 : 0 ;
            default:
                throw new UnsupportedOperationException();
        }

    }

    @Override
    public String gen() {
        return e1.gen() + op.gen() + e2.gen();
    }
}
