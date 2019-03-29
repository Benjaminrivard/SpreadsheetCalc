package parser;

import ast.*;
import ast.Boolean;

import java.util.ArrayList;
import java.util.List;

public class ASTVisitor extends CalcBaseVisitor<AST> {


    public AST visitBody(CalcParser.BodyContext ctx) {
        //retrieve ASTs for definitions
        List<CalcParser.Var_defContext> varDefCtxs = ctx.var_def();
        List<VarDef> varDefs = new ArrayList<>();
        for (CalcParser.Var_defContext varDefCtx : varDefCtxs)
            varDefs.add((VarDef) visit(varDefCtx));

        //retrieve AST for expression
        Expression expr = (Expression) visit(ctx.expression());

        return new Body(varDefs, expr);
    }

    @Override
    public AST visitLiteral(CalcParser.LiteralContext ctx) {
        return new Literal(Integer.parseInt(ctx.getText()));
    }

    @Override
    public AST visitBinaryExpression(CalcParser.BinaryExpressionContext ctx) {
        Op op = Op.parseOP(ctx.getChild(1).getText());
        List<CalcParser.ExpressionContext> expressionCtxs = ctx.expression();
        List<Expression> expressions = new ArrayList<>();
        for (CalcParser.ExpressionContext expressionContext : expressionCtxs)
            expressions.add((Expression) visit(expressionContext));

        return new BinaryExpression(op, expressions.get(0), expressions.get(1));
    }

    @Override
    public AST visitConditionalExpression(CalcParser.ConditionalExpressionContext ctx) {

        List<CalcParser.ExpressionContext> expressionCtxs = ctx.expression();
        List<Expression> expressions = new ArrayList<>();
        for (CalcParser.ExpressionContext expressionContext : expressionCtxs)
            expressions.add((Expression) visit(expressionContext));

        return new ConditionalExpression(expressions.get(0), expressions.get(1), expressions.get(2));
    }

    @Override
    public AST visitUnaryMinus(CalcParser.UnaryMinusContext ctx) {
        Expression expression = (Expression)visit(ctx.expression());
        return new UnaryMinus(expression);
    }



    @Override
    public AST visitVar_def(CalcParser.Var_defContext ctx) {

        Variable variable = (Variable) visit(ctx.var_id());
        Expression expression = (Expression)visit(ctx.expression());

        return new VarDef(variable,expression);
   }

    @Override
    public AST visitHead(CalcParser.HeadContext ctx) {
        Func_ID var = (Func_ID) visit(ctx.func_id());
        List<CalcParser.Var_idContext> variableContext = ctx.var_id();
        List<Variable> variables = new ArrayList<>();
        for(CalcParser.Var_idContext variableIdContext : variableContext)
        {
            variables.add((Variable)visit(variableIdContext));
        }

        return new Head(var, variables);
    }


    @Override
    public AST visitVar_id(CalcParser.Var_idContext ctx) {
        return new Variable(ctx.getText());
    }

    @Override
    public AST visitFuncdef(CalcParser.FuncdefContext ctx) {

        Body body = (Body)visit(ctx.body());
        Head head = (Head)visit(ctx.head());

        return new FuncDef(head, body);
    }

    @Override
    public AST visitVariable(CalcParser.VariableContext ctx)  {
        return new Variable(ctx.getText());
    }

    @Override
    public AST visitFunc_id(CalcParser.Func_idContext ctx) {
        return new Func_ID(ctx.getText());
    }

    @Override
    public AST visitProgram(CalcParser.ProgramContext ctx) {
        List<CalcParser.FuncdefContext> functionContextList = ctx.funcdef();
        List<FuncDef> funcDefs = new ArrayList<>();

        for(CalcParser.FuncdefContext functionCallContext : functionContextList)
        {
            funcDefs.add((FuncDef)visit(functionCallContext));
        }
        Body body = (Body)visit(ctx.body());

        return new Program(funcDefs, body);
    }

    @Override
    public AST visitParExp(CalcParser.ParExpContext ctx) {
        Expression expression = (Expression)visit(ctx.expression());

        return new ParExp(expression);
    }

    @Override
    public AST visitFunctionExpression(CalcParser.FunctionExpressionContext ctx) {
        Func_ID func_id = (Func_ID)visit(ctx.func_id());
        List<CalcParser.ExpressionContext> expressionContexts = ctx.expression();
        List<Expression> expressions =new ArrayList<>();
        for(CalcParser.ExpressionContext expressionContext : expressionContexts)
        {
            expressions.add((Expression)visit(expressionContext));
        }

        return new FuncExpression(func_id,expressions);
    }

    @Override
    public AST visitBoolean(CalcParser.BooleanContext ctx) {
        return new Boolean(ctx.getText());
    }
}

