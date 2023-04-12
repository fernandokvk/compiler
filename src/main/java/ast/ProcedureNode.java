package ast;

public class ProcedureNode extends InstrucaoNode{
    public ParametrosNode parametrosNode;
    public BlocoNode blocoNode;

    public ProcedureNode(ParametrosNode parametrosNode, BlocoNode blocoNode) {
        this.parametrosNode = parametrosNode;
        this.blocoNode = blocoNode;
    }
}
