package semantics;

import ast.ProgramaNode;

public class SemanticAnalyzer {
    ProgramaNode raiz;

    public SemanticAnalyzer(Object raiz) {
        this.raiz = (ProgramaNode) raiz;
    }

    public void run() {
        System.out.println("Programa: " + raiz.initProgramaNode.tokenInfo);

    }
}
