package ast;

import misc.TokenInfo;

public class InitProgramaNode extends ASTNode {
    public TokenInfo tokenInfo;

    public InitProgramaNode(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
