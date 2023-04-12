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
        if (id == null){
            return literalNode.tokenInfo.lexema;
        } else {
            return id.lexema;
        }
    }
}
