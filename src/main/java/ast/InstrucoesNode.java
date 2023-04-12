package ast;

import java.util.ArrayList;
import java.util.List;

public class InstrucoesNode extends ASTNode{
    public List<InstrucaoNode> instrucoes;

    public InstrucoesNode() {
        instrucoes = new ArrayList<>();
    }

    public void addInstrucao(InstrucaoNode instrucao) {
        instrucoes.add(instrucao);
    }

    public void addAllInstrucoes(List<InstrucaoNode> instrucoesList) {
        instrucoes.addAll(instrucoesList);
    }
}
