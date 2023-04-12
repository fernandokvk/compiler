package ast;

import misc.TokenInfo;

public class TipoNode extends  ASTNode{
    public TokenInfo tokenInfo;

    public TipoNode(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
