package ast;

public class IfNode extends InstrucaoNode{
    public CondicoesNode condicoesNode;
    public BlocoNode bloco;

    public IfNode(CondicoesNode condicoesNode, BlocoNode bloco) {
        this.condicoesNode = condicoesNode;
        this.bloco = bloco;
    }
}
