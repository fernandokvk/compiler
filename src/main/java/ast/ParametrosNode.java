package ast;

import java.util.ArrayList;

public class ParametrosNode extends ASTNode {

    public ArrayList<ParametroNode> parametrosNodes = new ArrayList<>();

    public ParametrosNode(ParametroNode parametroNode) {
        parametrosNodes.add(parametroNode);
    }

    public ParametrosNode() {

    }
}
