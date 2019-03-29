package ast;

import parser.State;

import java.util.ArrayList;
import java.util.List;

public class FuncExpression extends Expression {

    public Func_ID func_id;
    public List<Expression> expressionList;

    public FuncExpression(Func_ID func_id, List<Expression> expressions)
    {
        this.func_id = func_id;
        this.expressionList = expressions;
    }

    @Override
    public String gen() {
        return "";
    }

    public int eval(State<Integer> state, State<FuncDef> stateFunc) throws Exception
    {
        if(stateFunc.lookup(func_id.value) != null)
        {
            List<Integer> varValues = new ArrayList<>();
            for(Expression e : expressionList)
            {
                varValues.add(e.eval(state, stateFunc));
            }
            FuncDef funcDef = stateFunc.lookup(func_id.value);
            return funcDef.eval(varValues, stateFunc);
        }
        else {
            throw new SyntaxError("Eorr : function not defined");
        }
    }
}
