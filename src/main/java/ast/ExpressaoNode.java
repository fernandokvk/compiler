package ast;

import misc.TokenInfo;

public class ExpressaoNode extends  ASTNode{
    public VarNode varA, varB;
    public OperadorAritmeticoNode op;

    public TokenInfo id;
    public ArgumentosNode argumentosNode;

    public ExpressaoNode(VarNode varA, VarNode varB, OperadorAritmeticoNode op) {
        this.varA = varA;
        this.varB = varB;
        this.op = op;
    }

    public ExpressaoNode(VarNode varA) {
        this.varA = varA;
    }

    public ExpressaoNode(TokenInfo id, ArgumentosNode argumentosNode) {
        this.id = id;
        this.argumentosNode = argumentosNode;
    }
}
