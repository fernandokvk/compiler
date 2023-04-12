package ast;

import java.util.Stack;

public class CondicoesNode extends  ASTNode{
    public Stack<ExpressaoNode> expressaoNodeStack = new Stack<>();
    public Stack<OperadorLogicoNode> operadorLogicoNodeStack = new Stack<>();
    public Stack<OperadorComparacaoNode> operadorComparacaoNodeStack = new Stack<>();

    public CondicoesNode(Stack<ExpressaoNode> expressaoNodeStack, Stack<OperadorLogicoNode> operadorLogicoNodeStack, Stack<OperadorComparacaoNode> operadorComparacaoNodeStack) {
        this.expressaoNodeStack = expressaoNodeStack;
        this.operadorLogicoNodeStack = operadorLogicoNodeStack;
        this.operadorComparacaoNodeStack = operadorComparacaoNodeStack;
    }

    public CondicoesNode(){
    }

    public CondicoesNode(ExpressaoNode expressaoNode, OperadorLogicoNode operadorLogicoNode) {
        this.expressaoNodeStack.push(expressaoNode);
        this.operadorLogicoNodeStack.push(operadorLogicoNode);
    }

    public CondicoesNode(ExpressaoNode expressaoNode, OperadorComparacaoNode operadorComparacaoNode, int flag) {
        this.expressaoNodeStack.push(expressaoNode);
        this.operadorComparacaoNodeStack.push(operadorComparacaoNode);
    }
}
