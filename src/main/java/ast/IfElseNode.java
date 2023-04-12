package ast;

public class IfElseNode extends InstrucaoNode{
    public CondicoesNode condicoesNode;
    public BlocoNode blocoNodeIf, blocoNodeElse;

    public IfElseNode(CondicoesNode condicoesNode, BlocoNode blocoNodeIf, BlocoNode blocoNodeElse) {
        this.condicoesNode = condicoesNode;
        this.blocoNodeIf = blocoNodeIf;
        this.blocoNodeElse = blocoNodeElse;
    }
}
