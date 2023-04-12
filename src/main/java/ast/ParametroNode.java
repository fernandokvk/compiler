package ast;

import misc.TokenInfo;

public class ParametroNode extends  ASTNode{

    public TipoNode tipoNode;
    public TokenInfo id;

    public ParametroNode(TipoNode tipoNode, TokenInfo id) {
        this.tipoNode = tipoNode;
        this.id = id;
    }
}
