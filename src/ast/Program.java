package ast;



import parser.State;

import java.util.List;

public class Program extends AST {

    public List<FuncDef> fdefList;
    public Body body;

    @Override
    public String gen() {
        String result = "#include <stdio.h>\n"+
                "\n"+ body.gen();


        return result;
    }

    public Program(List<FuncDef> fdefList, Body body)
    {
        this.fdefList = fdefList;
        this.body = body;
    }


    public  int eval() throws Exception {
        State<Integer> stateInteger =  new State<>();
        State<FuncDef> stateFuncDef = new State<>();
        for(FuncDef func_def : this.fdefList) {
            func_def.eval(stateFuncDef);
        }

        return body.eval(stateInteger, stateFuncDef);
    }
}
