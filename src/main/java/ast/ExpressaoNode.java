package ast;

public class ExpressaoNode extends  ASTNode{
    public VarNode varA, varB;
    public OperadorAritmeticoNode op;

    public ExpressaoNode(VarNode varA, VarNode varB, OperadorAritmeticoNode op) {
        this.varA = varA;
        this.varB = varB;
        this.op = op;
    }

    public ExpressaoNode(VarNode varA) {
        this.varA = varA;
    }
}
