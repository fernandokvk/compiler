package ast;

public class WhileNode extends InstrucaoNode{
    public BlocoNode blocoNode;
    public CondicoesNode condicoesNode;

    public WhileNode(BlocoNode blocoNode, CondicoesNode condicoesNode) {
        this.blocoNode = blocoNode;
        this.condicoesNode = condicoesNode;
    }
}
