package ast;

import misc.TokenInfo;

public class OperadorAritmeticoNode extends  OperadorNode{
    public TokenInfo tokenInfo;

    public OperadorAritmeticoNode(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
