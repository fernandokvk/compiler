package ast;

import java.util.ArrayList;
import java.util.Stack;

public class CondicoesNode extends  ASTNode{
    public ArrayList<ExpressaoNode> expressaoNodeStack = new ArrayList<>();
    public ArrayList<OperadorLogicoNode> operadorLogicoNodeStack = new ArrayList<>();
    public ArrayList<OperadorComparacaoNode> operadorComparacaoNodeStack = new ArrayList<>();

    public CondicoesNode(ArrayList<ExpressaoNode> expressaoNodeStack, ArrayList<OperadorLogicoNode> operadorLogicoNodeStack, ArrayList<OperadorComparacaoNode> operadorComparacaoNodeStack) {
        this.expressaoNodeStack = expressaoNodeStack;
        this.operadorLogicoNodeStack = operadorLogicoNodeStack;
        this.operadorComparacaoNodeStack = operadorComparacaoNodeStack;
    }

    public CondicoesNode(){
    }

    public CondicoesNode(ExpressaoNode expressaoNode){
        expressaoNodeStack.add(expressaoNode);
    }

    public CondicoesNode(ExpressaoNode expressaoNode, OperadorLogicoNode operadorLogicoNode) {
        this.expressaoNodeStack.add(expressaoNode);
        this.operadorLogicoNodeStack.add(operadorLogicoNode);
    }

    public CondicoesNode(ExpressaoNode expressaoNode, OperadorComparacaoNode operadorComparacaoNode, int flag) {
        this.expressaoNodeStack.add(expressaoNode);
        this.operadorComparacaoNodeStack.add(operadorComparacaoNode);
    }
}
