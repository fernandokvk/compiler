package ast;

import misc.TokenInfo;

public class OperadorComparacaoNode extends  OperadorNode{
    public TokenInfo tokenInfo;


    public OperadorComparacaoNode(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
