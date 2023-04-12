package ast;

import java.util.ArrayList;

public class BlocoNode extends  ASTNode{
    public InstrucoesNode instrucoes;
    public BlocoNode(InstrucoesNode instrs){
        this.instrucoes = instrs;
        System.out.println("BlocoNode");
    }

}
