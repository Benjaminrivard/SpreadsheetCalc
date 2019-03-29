package ast;


import parser.State;

public class VarDef extends AST {

    public Variable varID;
    public Expression e;

    public VarDef(Variable varID, Expression e) {
        this.varID = varID;
        this.e = e;
    }

    @Override
    public String gen() {
        return "int "+ varID.gen() + "=" + e.gen() +"; \n";
    }

    public int eval(State<Integer> state, State<FuncDef> funcDefState) throws Exception {
        if(null == state.lookup(varID.value))
        {
            state.bind(varID.value, e.eval(state,funcDefState));
            return state.lookup(varID.value);
        }
        else {
            throw new Exception("variable is already defined");
        }
    }
}
