package ast;

import misc.TokenInfo;

public class OperadorLogicoNode extends OperadorNode {
    public TokenInfo tokenInfo;

    public OperadorLogicoNode(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
