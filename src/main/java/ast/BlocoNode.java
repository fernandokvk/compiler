package ast;

import java.util.ArrayList;
import java.util.List;

public class BlocoNode extends  ASTNode{
    public List<InstrucaoNode> instrucoes;
    public BlocoNode(InstrucoesNode instrs){
        instrucoes = new ArrayList<>();
    }

    public void addInstrucao(InstrucaoNode instrucaoNode){
        instrucoes.add(instrucaoNode);
    }
}
