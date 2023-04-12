package ast;

import misc.TokenInfo;

public class ForNode extends InstrucaoNode{
    public BlocoNode blocoNode;
    public CondicoesNode condicoesNode;
    public AtribuicaoNode atribuicaoNodeDireita, atribuicaoNodeEsquerda;
    public InicializacaoNode inicializacaoNode;
    public TokenInfo id;

    public ForNode(BlocoNode blocoNode, CondicoesNode condicoesNode, AtribuicaoNode atribuicaoNodeDireita, AtribuicaoNode atribuicaoNodeEsquerda) {
        this.blocoNode = blocoNode;
        this.condicoesNode = condicoesNode;
        this.atribuicaoNodeDireita = atribuicaoNodeDireita;
        this.atribuicaoNodeEsquerda = atribuicaoNodeEsquerda;
    }

    public ForNode(BlocoNode blocoNode, CondicoesNode condicoesNode, AtribuicaoNode atribuicaoNodeDireita, InicializacaoNode inicializacaoNode) {
        this.blocoNode = blocoNode;
        this.condicoesNode = condicoesNode;
        this.atribuicaoNodeDireita = atribuicaoNodeDireita;
        this.inicializacaoNode = inicializacaoNode;
    }

    public ForNode(BlocoNode blocoNode, CondicoesNode condicoesNode, AtribuicaoNode atribuicaoNodeDireita, TokenInfo id) {
        this.blocoNode = blocoNode;
        this.condicoesNode = condicoesNode;
        this.atribuicaoNodeDireita = atribuicaoNodeDireita;
        this.id = id;
    }
}
