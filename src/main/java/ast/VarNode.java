package ast;

import misc.TokenInfo;

public class VarNode {
    public TokenInfo id;
    public LiteralNode literalNode;

    public VarNode(TokenInfo id) {
        this.id = id;
    }

    public VarNode(LiteralNode literalNode) {
        this.literalNode = literalNode;
    }

    @Override
    public String toString() {
        if (literalNode == null){
            return id.lexema;
        } else {
            return literalNode.toString();
        }
    }
}
