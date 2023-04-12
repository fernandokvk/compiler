package ast;

import misc.TokenInfo;

public class OperadorLogicoNode extends  ASTNode {
    public TokenInfo tokenInfo;

    public OperadorLogicoNode(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
