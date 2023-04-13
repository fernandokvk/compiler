package semantics;

import ast.*;
import cup.sym;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class SemanticAnalyzer {
    ProgramaNode raiz;
    HashMap<TableSymbolVar, Integer> tabelaSimbolo = new HashMap<>();
    HashMap<TableProceduresVar, Integer> tableProceduresVarEscopo = new HashMap<>();
    int nivelEscopo = -1;

    public SemanticAnalyzer(Object raiz) {
        this.raiz = (ProgramaNode) raiz;
    }

    public void run() throws SemanticErrorException {
        System.out.println("Programa:\t" + raiz.initProgramaNode.tokenInfo);
        parseBlocoNode(raiz.blocoNode);
    }

    private boolean parseBlocoNode(BlocoNode blocoNode) throws SemanticErrorException {
        boolean correto = true;
        this.nivelEscopo = this.nivelEscopo + 1;
        BlocoNode bloco = blocoNode;
        ArrayList<InstrucaoNode> instrucoes = (ArrayList) bloco.instrucoes.instrucoes;
        //De trás pra frente por causa da pilha
        for (int i = instrucoes.size() - 1; i >= 0; i--) {
            correto = parseInstruction(instrucoes.get(i));
            if (!correto) return false;
        }
        this.nivelEscopo = this.nivelEscopo - 1;
        return true;
    }

    private boolean parseInstruction(InstrucaoNode instrucaoNode) throws SemanticErrorException {
        boolean debug = false;

        //Java 8 não suporta o switch case com o tipo de classe
        if (instrucaoNode instanceof InicializacaoNode) {
            //OK
            try {
                parseInicializacaoNode((InicializacaoNode) instrucaoNode);
            } catch (SemanticErrorException e) {
                System.exit(1);
            }
        } else if (instrucaoNode instanceof ForNode) {
            //OK
            try {
                parseForNode((ForNode) instrucaoNode);
            } catch (SemanticErrorException e) {
                System.exit(1);
            }
            if (debug) System.out.println("ForNode");
        } else if (instrucaoNode instanceof IfNode) {
            //OK
            try {
                parseIfNode((IfNode) instrucaoNode);
            } catch (SemanticErrorException e) {
                System.exit(1);
            }
            if (debug) System.out.println("IfNode");
        } else if (instrucaoNode instanceof AtribuicaoNode) {
            //OK
            try {
                parseAtribuicaoNode((AtribuicaoNode) instrucaoNode);
            } catch (SemanticErrorException e) {
                System.exit(1);
            }
            if (debug) System.out.println("AtribuicaoNode");
        } else if (instrucaoNode instanceof DeclaracaoNode) {
            //OK
            addTabelaSimbolo(((DeclaracaoNode) instrucaoNode).tipoNode.tokenInfo.sym, ((DeclaracaoNode) instrucaoNode).id.lexema, this.nivelEscopo);
            if (debug) System.out.println("DeclaracaoNode");
        } else if (instrucaoNode instanceof FunctionNode) {
            //OK
            try {
                parseFunctionNode((FunctionNode) instrucaoNode);
            } catch (SemanticErrorException e) {
                System.exit(1);
            }
            if (debug) System.out.println("FunctionNode");
        } else if (instrucaoNode instanceof IfElseNode) {
            //OK
            try {
                parseIfElseNode((IfElseNode) instrucaoNode);
            } catch (SemanticErrorException e) {
                System.exit(1);
            }
            if (debug) System.out.println("IfElseNode");
        } else if (instrucaoNode instanceof ProcedureNode) {
            //OK
            try {
                parseProcedureNode((ProcedureNode) instrucaoNode);
            } catch (SemanticErrorException e) {
                System.exit(1);
            }
            if (debug) System.out.println("ProcedureNode");
        } else if (instrucaoNode instanceof ProcNode) {
            try {
                parseProcNode((ProcNode) instrucaoNode);
            } catch (SemanticErrorException e) {
                System.exit(1);
            }
            if (debug) System.out.println("ProcNode");
        } else if (instrucaoNode instanceof WhileNode) {
            try {
                parseWhileNode((WhileNode) instrucaoNode);
            } catch (SemanticErrorException e) {
                System.exit(1);
            }
            if (debug) System.out.println("WhileNode");
        } else {
            // default case
        }
        return true;
    }

    private boolean parseWhileNode(WhileNode instrucaoNode) throws SemanticErrorException {
        //Bloco e condições
        if (parseBlocoNode(instrucaoNode.blocoNode) && parseCondicoesNode(instrucaoNode.condicoesNode)) {
            return true;
        } else {
            String message = "Erro semantico -" + " bloco procedure ";
            System.err.println(message);
            throw new SemanticErrorException(message);
        }
    }

    private boolean parseProcNode(ProcNode instrucaoNode) throws SemanticErrorException {
        if (instrucaoNode.argumentosNode != null) {
            if (parseArgumentos(instrucaoNode.argumentosNode)) {
                return true;
            } else {
                String message = "Erro semantico -" + " bloco procedure ";
                System.err.println(message);
                throw new SemanticErrorException(message);
            }
        } else {
            return true;
        }
    }

    private boolean parseArgumentos(ArgumentosNode argumentosNode) {
        boolean correto = true;
        int symTipo = -1, aux;
        for (int i = 0; i < argumentosNode.expressaoNodeStack.size(); i++) {
            aux = parseExpressao(argumentosNode.expressaoNodeStack.get(i));
            if (symTipo == -1) {
                symTipo = aux;
            }
            if (symTipo != -1 && symTipo != aux) {
                correto = false;
            }
        }
        return true;
    }

    private boolean parseProcedureNode(ProcedureNode instrucaoNode) throws SemanticErrorException {
        //Bloco e parametros
        if (parseParametrosNode(instrucaoNode.parametrosNode) && parseBlocoNode(instrucaoNode.blocoNode)) {
            return true;
        } else {
            String message = "Erro semantico -" + " bloco procedure ";
            System.err.println(message);
            throw new SemanticErrorException(message);
        }
    }

    private boolean parseIfElseNode(IfElseNode instrucaoNode) throws SemanticErrorException {

        if (parseBlocoNode(instrucaoNode.blocoNodeIf)
                && parseBlocoNode(instrucaoNode.blocoNodeElse)
                && parseCondicoesNode(instrucaoNode.condicoesNode)
        ) {
            return true;
        } else {
            int lineNumber;
            if (instrucaoNode.condicoesNode.operadorComparacaoNodeStack.size() == 0) {
                lineNumber = instrucaoNode.condicoesNode.operadorLogicoNodeStack.get(0).tokenInfo.linha;
            } else {
                lineNumber = instrucaoNode.condicoesNode.operadorComparacaoNodeStack.get(0).tokenInfo.linha;
            }
            lineNumber++;
            String message = "Erro semantico -" + " bloco if else " + " - linha: " + lineNumber;
            System.err.println(message);
            throw new SemanticErrorException(message);
        }
    }

    private boolean parseFunctionNode(FunctionNode instrucaoNode) throws SemanticErrorException {
        //validar parametros
        //validar bloco
        //validar tipo de retorno e retorno declarado
        int aux = checkTabelaFuncoes(instrucaoNode.id.lexema);

        if (aux != -1) {
            //já existe uma função com esse id no mesmo escopo
            int lineNumber = instrucaoNode.id.linha + 1;
            String message = "Erro semantico -" + " funcao com o mesmo id já definida no escopo" + " - linha: " + lineNumber;
            System.err.println(message);
            throw new SemanticErrorException(message);
        } else {
            if (parseParametrosNode(instrucaoNode.parametrosNode)
                    && parseBlocoNode(instrucaoNode.blocoNode)
                    && parseTipoRetornoFuncao(instrucaoNode.tipoNodeRetorno, instrucaoNode.varNodeRetorno)) {
                TableProceduresVar tableProceduresVar = new TableProceduresVar(instrucaoNode.id.lexema, instrucaoNode.tipoNodeRetorno.tokenInfo.sym);
                this.tableProceduresVarEscopo.put(tableProceduresVar, this.nivelEscopo);
                return true;
            } else {
                int lineNumber = instrucaoNode.id.linha + 1;
                String message = "Erro semantico -" + " funcao " + " - linha: " + lineNumber;
                System.err.println(message);
                throw new SemanticErrorException(message);
            }
        }


    }

    private boolean parseParametrosNode(ParametrosNode parametrosNode) {
     /*
     Não encontrei problemas semânticos na definição dos parâmetros, por exemplo:
            int a = 100;
            function foo(int a, int b, float c): int{
                   b = a * c;
            } return b;
     Acredito que funcionaria normalmente (considerando "a")
     Mas vou deixar a função criada para avaliar futuramente
      */

        if (parametrosNode != null) {
            for (int i = 0; i < parametrosNode.parametrosNodes.size(); i++) {
                addTabelaSimbolo(parametrosNode.parametrosNodes.get(i).tipoNode.tokenInfo.sym, parametrosNode.parametrosNodes.get(i).id.lexema, this.nivelEscopo + 1);
            }
        }
        return true;
    }

    private boolean parseTipoRetornoFuncao(TipoNode tipoNodeRetorno, VarNode varNodeRetorno) {
        int retorno = switchTipoVarParaVar(tipoNodeRetorno.tokenInfo.sym);
        int varRetorno = -1;
        if (varNodeRetorno.literalNode == null) {
            //é uma variável
            varRetorno = checkTabelaSimbolo(varNodeRetorno.id.lexema, true);
        } else {
            //é um literal
            varRetorno = varNodeRetorno.literalNode.tokenInfo.sym;

        }
        if (retorno == varRetorno) return true;
        return false;
    }

    private boolean parseIfNode(IfNode instrucaoNode) throws SemanticErrorException {
        if (parseBlocoNode(instrucaoNode.bloco) && parseCondicoesNode(instrucaoNode.condicoesNode)) {
            return true;
        } else {
            int lineNumber;
            if (instrucaoNode.condicoesNode.operadorComparacaoNodeStack.size() == 0) {
                lineNumber = instrucaoNode.condicoesNode.operadorLogicoNodeStack.get(0).tokenInfo.linha;
            } else {
                lineNumber = instrucaoNode.condicoesNode.operadorComparacaoNodeStack.get(0).tokenInfo.linha;
            }
            lineNumber++;
            String message = "Erro semantico -" + " bloco if " + " - linha: " + lineNumber;
            System.err.println(message);
            throw new SemanticErrorException(message);
        }
    }

    private boolean parseCondicoesNode(CondicoesNode condicoesNode) {
        //Com problemas
        if (condicoesNode.operadorLogicoNodeStack.size() == 0) {
            //verificar operadorComparacaoNodeStack
            return true;
        } else {
            //verificar operadorLogicoNodeStack
            return true;
        }
    }

    private boolean parseForNode(ForNode instrucaoNode) throws SemanticErrorException {
        boolean parteDeclarativa = false;
        if (instrucaoNode.atribuicaoNodeEsquerda == null && instrucaoNode.inicializacaoNode == null) {
            //id
            //assegurar que existe, é válido no escopo e é inteiro
            int aux = checkTabelaSimbolo(instrucaoNode.id.lexema);
            if (aux == sym.INTEIRO) parteDeclarativa = true;
        } else if (instrucaoNode.atribuicaoNodeEsquerda == null && instrucaoNode.id == null) {
            //inicializacao
            parteDeclarativa = parseInicializacaoNode(instrucaoNode.inicializacaoNode);
        } else {
            //atribuicao
            parteDeclarativa = parseAtribuicaoNode(instrucaoNode.atribuicaoNodeEsquerda);
        }

        if (parteDeclarativa && parseAtribuicaoNode(instrucaoNode.atribuicaoNodeDireita) && parseBlocoNode(instrucaoNode.blocoNode)) {
            return true;
        } else {
            int lineNumber = instrucaoNode.id.linha + 1;
            String message = "Erro semantico -" + " bloco for " + " - linha: " + lineNumber;
            System.err.println(message);
            throw new SemanticErrorException(message);
        }
    }

    private boolean parseAtribuicaoNode(AtribuicaoNode instrucaoNode) throws SemanticErrorException {
        int tipoExpressao = parseExpressoes(instrucaoNode.expressoesNode);
        int tipoId = checkTabelaSimbolo(instrucaoNode.id.lexema);

        if (tipoId != tipoExpressao) {
            int lineNumber = instrucaoNode.id.linha + 1;
            String message = "Erro semantico -" + " atribuicao " + " - linha: " + lineNumber;
            System.err.println(message);
            throw new SemanticErrorException(message);
        } else {
            return true;
        }
    }

    private void addTabelaSimbolo(int sym, String lexema, int nivelEscopo) {
        TableSymbolVar tableSymbolVar = new TableSymbolVar(sym, lexema);
        this.tabelaSimbolo.put(tableSymbolVar, nivelEscopo);
    }

    private boolean parseInicializacaoNode(InicializacaoNode inicializacaoNode) throws SemanticErrorException {
        int aux = parseExpressoes(inicializacaoNode.expressoesNode);
        int tipo = switchTipoVarParaVar(inicializacaoNode.tipoNode.tokenInfo.sym);
        if (aux == tipo) {
            addTabelaSimbolo(inicializacaoNode.tipoNode.tokenInfo.sym, inicializacaoNode.id.lexema, this.nivelEscopo);
            return true;
        } else {
            int lineNumber = inicializacaoNode.tipoNode.tokenInfo.linha + 1;
            String message = "Erro semantico -" + " inicializacao " + " - linha: " + lineNumber;
            System.err.println(message);
            throw new SemanticErrorException(message);
        }
    }

    private int parseExpressoes(ExpressoesNode expressoesNode) {
        boolean correto = true;
        int symTipo = -1, aux;
        for (int i = 0; i < expressoesNode.expressoes.size(); i++) {
            aux = parseExpressao(expressoesNode.expressoes.get(i));
            if (symTipo == -1) {
                symTipo = aux;
            }
            if (symTipo != -1 && symTipo != aux) {
                correto = false;
            }
        }
        if (expressoesNode.operadorNodes.size() > 0){
            //Deixar caso precise
        }
        return symTipo;
    }

    private int parseExpressao(ExpressaoNode expressaoNode) {
        int symA = -1, symB = -1;
        if (expressaoNode.varA != null && expressaoNode.varB != null) {
            if (expressaoNode.varA == null) {
                symA = expressaoNode.varA.literalNode.tokenInfo.sym;
            } else if (expressaoNode.varA != null && expressaoNode.varB != null) {
                if (expressaoNode.varA.literalNode != null && expressaoNode.varB.literalNode != null){
                    symA = expressaoNode.varA.literalNode.tokenInfo.sym;
                    symB = expressaoNode.varB.literalNode.tokenInfo.sym;
                    if (symA == sym.INTEIRO && symB == sym.INTEIRO) return sym.INTEIRO;
                    else if (symA == sym.FLUTUANTE && symB == sym.FLUTUANTE) return sym.FLUTUANTE;
                } else {
                    symA = switchTipoVarParaVar(checkTabelaSimbolo(expressaoNode.varA.id.lexema));
                    symB = switchTipoVarParaVar(checkTabelaSimbolo(expressaoNode.varB.id.lexema));
                    if (symA == sym.INTEIRO && symB == sym.INTEIRO) return sym.INTEIRO;
                    else if (symA == sym.FLUTUANTE && symB == sym.FLUTUANTE) return sym.FLUTUANTE;
                }

            } else {
                symA = switchTipoVarParaVar(checkTabelaSimbolo(expressaoNode.varA.id.lexema));
            }
            if (expressaoNode.varB == null) {
                symB = expressaoNode.varB.literalNode.tokenInfo.sym;
            } else {
                symB = switchTipoVarParaVar(checkTabelaSimbolo(expressaoNode.varB.id.lexema));
            }
            if (symA == sym.INTEIRO && symB == sym.INTEIRO) return sym.INTEIRO;
            else if (symA == sym.FLUTUANTE && symB == sym.FLUTUANTE) return sym.FLUTUANTE;
        } else if (expressaoNode.id != null && expressaoNode.argumentosNode != null) {
            int tipoDeRetornoDaFuncao = checkTabelaFuncoes(expressaoNode.id.lexema);
            return switchTipoVarParaVar(tipoDeRetornoDaFuncao);
        } else if (expressaoNode.varB == null) {
            if (expressaoNode.varA.id != null) {
                //ver se o identificador foi declarado
                int aux = checkTabelaSimbolo(expressaoNode.varA.id.lexema);
                return aux;
            } else {
                return expressaoNode.varA.literalNode.tokenInfo.sym;
            }
        }
        return -1;
    }

    private int checkTabelaSimbolo(String target) {
        //escopo diferente (maior) -> -1
        //não existe -> -1
        //existe -> retorna sym
        int aux = -1;
        int targetEscopo = -1;
        for (TableSymbolVar tableSymbolVar : tabelaSimbolo.keySet()) {
            if (tableSymbolVar.lexema.equals(target)) {
                aux = tableSymbolVar.sym;
                targetEscopo = this.tabelaSimbolo.get(tableSymbolVar);
            }
        }
        if (this.nivelEscopo < targetEscopo) aux = -1;
        if (aux != -1) aux = switchTipoVarParaVar(aux);
        return aux;
    }

    private int checkTabelaSimbolo(String target, boolean functionReturn) {
        //escopo diferente (maior) -> -1
        //não existe -> -1
        //existe -> retorna sym
        int aux = -1;
        int targetEscopo = -1;
        for (TableSymbolVar tableSymbolVar : tabelaSimbolo.keySet()) {
            if (tableSymbolVar.lexema.equals(target)) {
                aux = tableSymbolVar.sym;
                targetEscopo = this.tabelaSimbolo.get(tableSymbolVar) - 1;
            }
        }
        if (this.nivelEscopo < targetEscopo) aux = -1;
        if (aux != -1) aux = switchTipoVarParaVar(aux);
        return aux;
    }

    private int switchTipoVarParaVar(int aux) {
        if (aux != -1) {
            switch (aux) {
                case (sym.TIPO_INTEIRO):
                    aux = sym.INTEIRO;
                    break;
                case (sym.TIPO_FLUTUANTE):
                    aux = sym.FLUTUANTE;
                    break;
                case (sym.TIPO_BOOLEAN):
                    //ver
                    break;
                case (sym.TIPO_CHAR):
                    aux = sym.CARACTERE;
            }
        }
        return aux;
    }

    private int checkTabelaFuncoes(String target) {
        //escopo diferente (maior) -> -1
        //não existe -> -1
        //existe -> retorna sym
        int aux = -1;
        int targetEscopo = -1;
        for (TableProceduresVar tableProceduresVar : tableProceduresVarEscopo.keySet()) {
            if (tableProceduresVar.id.equals(target)) {
                aux = tableProceduresVar.tipoRetorno;
                targetEscopo = this.tableProceduresVarEscopo.get(tableProceduresVar);
            }
        }
        if (this.nivelEscopo < targetEscopo) aux = -1;
        if (aux != -1) aux = switchTipoVarParaVar(aux);
        return aux;
    }
}
