package ast;

import parser.State;

import java.util.ArrayList;
import java.util.List;

public class FuncDef extends AST {

    public Body body;
    public Head head;

    @Override
    public String gen() {
        return body.gen();
    }

    public FuncDef(Head head, Body body)
    {
        this.head = head;
        this.body = body;
    }

    public int eval(List<Integer> args, State<FuncDef> stFunc) throws Exception
    {
        if(stFunc.lookup(head.func_id.value) != null)
        {
            List<String> formals = new ArrayList<>();
            for(Variable variable : head.parameters)
            {
                formals.add(variable.value);
            }
            State<Integer> state = this.bindArguments(formals,args);
            return this.body.eval(state, stFunc);
        } else {
            return 0;
        }
    }

    public void eval(State<FuncDef> state) throws Exception
    {
        if(state.lookup(head.func_id.value) != null)
        {
            throw new Exception("Fonction is not defined");
        }else {
            state.bind(head.func_id.value, this);
        }
    }

    private State<Integer> bindArguments(List<String> formals, List<Integer> args) throws SyntaxError
    {
        State<Integer> state = new State<>();
        if(formals.size() == args.size())
        {
            for(int i =0; i < formals.size() ; i++)
            {
                state.bind(formals.get(i),args.get(i));
            }
            return state;
        }
        else {
            throw new SyntaxError("Invalid Argument");
        }
    }


}
