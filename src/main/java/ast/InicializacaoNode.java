package ast;

import misc.TokenInfo;

public class InicializacaoNode extends InstrucaoNode{
    TipoNode tipoNode;
    TokenInfo id;
    ExpressoesNode expressoesNode;

    public InicializacaoNode(TipoNode tipoNode, TokenInfo id, ExpressoesNode expressoesNode) {
        this.tipoNode = tipoNode;
        this.id = id;
        this.expressoesNode = expressoesNode;
    }
}
