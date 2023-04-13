import flex.*;
import cup.*;
import semantics.SemanticAnalyzer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Entry {
    public static void main(String[] args) throws IOException {
        String[] filenames;
        if (args.length > 0) {
            filenames = getNamesFromArgs(args);
        } else {
            filenames = getTestes();
        }

        FileReader[] files = getFiles(filenames);
        runLexer(filenames, files);
    }

    private static String[] getTestes() {
        return new String[]{
//                "testes/expressoes.big",
//                "testes/funcao.big",
//                "testes/mini.big",
//                "testes/declaracoes.big",
//                "testes/teste1.big",
//                "testes/teste2.big",
//                "testes/if_else.big",
//                "testes/controle.big",
                "testes/erro_lexico.big"
        };
    }

    private static void runLexer(String[] filenames, FileReader[] files) throws IOException {
        Lexer lexer;
        sym sym = new sym();
        int i = 0;
        for (FileReader f : files) {
            lexer = new Lexer(f);
            System.out.println("\nArquivo:\t" + filenames[i]);
            try {
                parser parser = new parser(lexer);
                Object raiz = parser.parse().value;
                SemanticAnalyzer saz = new SemanticAnalyzer(raiz);
                saz.run();
                System.out.println("Fim:\t\t" + filenames[i]);
            } catch (Lexer.ErroLexico e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    private static FileReader[] getFiles(String[] filenames) {
        FileReader[] files = new FileReader[filenames.length];

        for (int i = 0; i < filenames.length; i++) {
            String s = filenames[i];
            try {
                FileReader file = new FileReader(s);
                files[i] = file;
            } catch (FileNotFoundException e) {
                System.err.println("Arquivo não encontrado: " + s);
                System.exit(1);
            }
        }
        return files;
    }
    private static String[] getNamesFromArgs(String[] args){
        String[] filenames = new String[]{};
        boolean allValid = true;
        for (String arg : args) {
            if (arg == null || arg.trim().isEmpty()) {
                allValid = false;
                break;
            }
        }
        if (allValid) {
            filenames = args;
        } else {
            System.err.println("Invalid file names provided. Using default file names.");
            filenames = getTestes();
        }
        return filenames;
    }


}
