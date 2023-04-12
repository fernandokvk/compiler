package misc;


public  class TokenInfo {
    public int linha;
    public int coluna;
    public String lexema;
    public int sym;

    public TokenInfo(int linha, int coluna, String lexema) {
        this.linha = linha;
        this.coluna = coluna;
        this.lexema = lexema;
    }

    public TokenInfo(int linha, int coluna, String lexema, int sym) {
        this.linha = linha;
        this.coluna = coluna;
        this.lexema = lexema;
        this.sym = sym;
    }

    @Override
    public String toString() {
        return lexema;
    }
}
