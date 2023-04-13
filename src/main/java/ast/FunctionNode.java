package ast;

import misc.TokenInfo;

public class FunctionNode extends InstrucaoNode{
    public ParametrosNode parametrosNode;
    public TipoNode tipoNodeRetorno;
    public BlocoNode blocoNode;
    public VarNode varNodeRetorno;

    public TokenInfo id;

    public FunctionNode(TokenInfo id, ParametrosNode parametrosNode, TipoNode tipoNodeRetorno, BlocoNode blocoNode, VarNode varNodeRetorno) {
        this.id = id;
        this.parametrosNode = parametrosNode;
        this.tipoNodeRetorno = tipoNodeRetorno;
        this.blocoNode = blocoNode;
        this.varNodeRetorno = varNodeRetorno;
    }
}
