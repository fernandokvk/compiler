package ast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class ExpressoesNode extends ASTNode {

    public ArrayList<ExpressaoNode> expressoes = new ArrayList<>();
    public ArrayList<OperadorAritmeticoNode> operadorNodes = new ArrayList<>();

    public ExpressoesNode(ExpressaoNode expressaoNode) {
        expressoes.add(expressaoNode);
    }

    public ExpressoesNode(ExpressaoNode expressaoNode, OperadorAritmeticoNode operadorNode) {
        expressoes.add(expressaoNode);
        operadorNodes.add(operadorNode);
    }

}
