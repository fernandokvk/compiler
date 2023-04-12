package ast;

import misc.TokenInfo;

public class OperadorAtribuicaoNode extends  OperadorNode{
    public TokenInfo tokenInfo;
    public OperadorAtribuicaoNode(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
