package ast;

import misc.TokenInfo;

public class OperadorAtribuicaoNode extends  ASTNode{
    public TokenInfo tokenInfo;
    public OperadorAtribuicaoNode(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
