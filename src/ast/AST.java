package ast;


import java.lang.reflect.Field;

abstract public class AST {

    public String toString() {
        // retrieve class of node
        Class<? extends AST> tclass = this.getClass();
        // isolate relative name (starting after the rightmost '.')
        String absoluteClassName = tclass.toString();
        int dotIndex = absoluteClassName.lastIndexOf(".", absoluteClassName.length());
        String relativeClassName = absoluteClassName.substring(dotIndex+1);
        // retrieving fields (note that, unfortunately, they are not ordered)
        // TO DO : get rid of static fields (pb in case of singletons)
        Field[] fields = tclass.getDeclaredFields();
        // building string representation of the arguments of the nodes
        int arity = fields.length;
        String args = "";
        for(int index = 0; index < arity; index++) {
            String arg;
            try {
                arg = fields[index].get(this).toString(); // retrieve string representation of all arguments
            } catch (Exception e) {
                arg = "?"; // IllegalArgument or IllegalAccess Exception (this shouldn't happen)
            }
            if (index != 0) // a separator is required before each argument except the first
                args = args + ", " + arg;
//				args = args + " " + arg;
            else
                args = args + arg;
        }
        return relativeClassName + "(" + args + ")";
//		return "<" + relativeClassName + ">" + args + "</" + relativeClassName + ">";
    }

   public abstract String gen();
}



