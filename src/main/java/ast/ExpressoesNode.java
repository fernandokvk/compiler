package ast;

import java.util.ArrayList;
import java.util.Stack;

public class ExpressoesNode extends ASTNode {

    public Stack<ExpressaoNode> expressoes = new Stack<>();
    public Stack<OperadorAritmeticoNode> operadorNodes = new Stack<>();

    public ExpressoesNode(ExpressaoNode expressaoNode) {
        expressoes.push(expressaoNode);
    }

    public ExpressoesNode(ExpressaoNode expressaoNode, OperadorAritmeticoNode operadorNode) {
        expressoes.push(expressaoNode);
        operadorNodes.push(operadorNode);
    }

}
