import jflex.exceptions.SilentExit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] filenames = {
                "testes/teste1.txt",
                "testes/hello.psc",
        };
        FileReader[] files = getFiles(filenames);
        runLexer(filenames, files);
    }

    private static void runLexer(String[] filenames, FileReader[] files) throws IOException {
        Lexer lexer;
        int i = 0;
        for (FileReader f : files) {
            lexer = new Lexer(f);
            System.out.println("\nArquivo: " + filenames[i]);
            System.out.println(lexer.yylex());
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
