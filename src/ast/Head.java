package ast;

import java.util.List;

public class Head extends AST {

    public Func_ID func_id;
    public List<Variable> parameters;

    @Override
    public String gen() {
        String result =  "int\n"
                +func_id.gen() +" (";
        for(Variable var : parameters)
        {
            result += "int "+var.gen();
        };

        result += ")";

        return result;
    }

    public Head(Func_ID func_id, List<Variable> parameters)
    {
        this.func_id = func_id;
        this.parameters = parameters;
    }


}
