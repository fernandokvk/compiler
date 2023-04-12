import flex.*;
import cup.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Entry {
    public static void main(String[] args) throws IOException {
        String[] filenames = {
//                "testes/expressoes.big",
//                "testes/funcao.big",
                "testes/mini.big",
//                "testes/declaracoes.big",
//                "testes/controle.big",
        };
        FileReader[] files = getFiles(filenames);
        runLexer(filenames, files);
        /*
         Definicao
            Inicializacao -> int i = 0;
            Declaracao -> int i;
        Atribuicao -> i = 0;
        Uso -> i;
         */
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
                parser.parse();
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
                e.printStackTrace();
            }
        }
        return files;
    }
}
