package ast;

public class FunctionNode extends InstrucaoNode{
    public ParametrosNode parametrosNode;
    public TipoNode tipoNodeRetorno;
    public BlocoNode blocoNode;
    public VarNode varNodeRetorno;

    public FunctionNode(ParametrosNode parametrosNode, TipoNode tipoNodeRetorno, BlocoNode blocoNode, VarNode varNodeRetorno) {
        this.parametrosNode = parametrosNode;
        this.tipoNodeRetorno = tipoNodeRetorno;
        this.blocoNode = blocoNode;
        this.varNodeRetorno = varNodeRetorno;
    }
}
