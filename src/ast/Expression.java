package ast;

import parser.State;

public abstract class Expression extends AST {

    public abstract int eval(State<Integer> state, State<FuncDef> funcDefState) throws Exception;
}
