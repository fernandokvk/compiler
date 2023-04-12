package ast;

import misc.TokenInfo;

public class DeclaracaoNode extends  InstrucaoNode{
    public TipoNode tipoNode;
    public TokenInfo id;

    public DeclaracaoNode(TipoNode tipoNode, TokenInfo id) {
        this.tipoNode = tipoNode;
        this.id = id;
    }
}
