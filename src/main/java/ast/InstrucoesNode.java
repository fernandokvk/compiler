package ast;

import java.util.ArrayList;
import java.util.List;

public class InstrucoesNode extends ASTNode{
    public List<InstrucaoNode> instrucoes= new ArrayList<>();;

    public InstrucoesNode(InstrucaoNode instrucaoNode) {
        instrucoes.add(instrucaoNode);
    }

}
