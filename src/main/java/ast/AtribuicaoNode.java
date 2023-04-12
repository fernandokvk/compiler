package ast;

import misc.TokenInfo;

public class AtribuicaoNode extends InstrucaoNode{
    public TokenInfo id;
    public OperadorAtribuicaoNode operador;
    public ExpressoesNode expressoesNode;

    public AtribuicaoNode(TokenInfo id, OperadorAtribuicaoNode operador, ExpressoesNode expressoesNode) {
        this.id = id;
        this.operador = operador;
        this.expressoesNode = expressoesNode;
    }
}
