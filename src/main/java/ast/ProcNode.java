package ast;

public class ProcNode extends InstrucaoNode{
    public ArgumentosNode argumentosNode;

    public ProcNode(ArgumentosNode argumentosNode) {
        this.argumentosNode = argumentosNode;
    }
}
