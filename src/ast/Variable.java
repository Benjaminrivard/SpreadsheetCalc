package ast;

import parser.State;

public class Variable extends Expression {

    public String value;

    @Override
    public String gen() {
        return value;
    }

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public int eval(State<Integer> state, State<FuncDef> funcDefState) throws Exception {
        Integer etat = state.lookup(value);
        if(etat != null)
        {
            return etat;
        }
        else {
            throw new Exception("Value not defined");
        }
    }
}
