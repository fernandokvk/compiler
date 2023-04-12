package ast;

import misc.TokenInfo;

public class ProgramaNode extends ASTNode{
    public InitProgramaNode initProgramaNode;
    public BlocoNode blocoNode;

    public ProgramaNode(InitProgramaNode initProgramaNode, BlocoNode blocoNode) {
        this.initProgramaNode = initProgramaNode;
        this.blocoNode = blocoNode;
    }
}
