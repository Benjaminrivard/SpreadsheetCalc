package ast;

import parser.State;

import java.util.List;

public class Body extends AST {

    public List<VarDef> varDefList;
    public Expression e;

    public Body(List<VarDef> vdefs, Expression e) {
        this.varDefList = vdefs;
        this.e = e;
    }

    @Override
    public String gen() {
       String result= "int main() {\n";

       for(VarDef var : varDefList)
       {
           result += var.gen();

       }

       result += "return printf(\"%i\\n\", "+ e.gen() +");\n" +
                "\n }";

        return result;
    }

    public  int eval(State<Integer> state, State<FuncDef> stFunc) throws Exception  {
        for(VarDef var_def : varDefList) {
            var_def.eval(state, stFunc);
        }
        return e.eval(state, stFunc);
    }

}
