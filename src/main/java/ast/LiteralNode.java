package ast;

import misc.TokenInfo;

public class LiteralNode extends ASTNode{
    public TokenInfo tokenInfo;

    public LiteralNode(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    @Override
    public String toString() {
        return tokenInfo.lexema;
    }
}
