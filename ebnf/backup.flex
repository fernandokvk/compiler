//Seção de código do usuário (user code)
package flex;
import java_cup.runtime.Symbol;
import java.io.IOException;
import cup.sym;
import misc.TokenInfo;import org.apache.tools.ant.filters.ReplaceTokens;

%%
//Seção de opções e declarações (options and declarations)

%class Lexer
%public
//Set de caracteres
%unicode
//Compatibilidade com cup
%cup
//Conta linhas (acessivel através de yyline)
%line
//Conta colunas (acessivel através de yycolumn)
%column
%caseless
//%debug

%{
//RuntimeException já que não é em tempo de compilação (JAVA)"
    public class ErroLexico extends RuntimeException{
      ErroLexico(String mensagem){
          super(mensagem);
      }
    }
%}

//Macros
Letra = [a-zA-Z]
Digito = [0-9]
Char = [\'][{Letra}][\']
Palavra = ({Letra}|{Digito})*
Inteiro = {Digito}+ | "-"{Digito}+
Identificador = {Letra}({Letra} | {Digito} | "_")*
Flutuante = {Inteiro}"."{Inteiro}

//Do manual JFlex
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]
Comentario = "#"[^\n]*

%%
//Seção de regras (lexical rules)
"program"  { return new Symbol(sym.PRGRM_DEF, new TokenInfo(yyline, yycolumn, yytext(), sym.PRGRM_DEF)); }
"begin"    { return new Symbol(sym.PRGRM_INI, new TokenInfo(yyline, yycolumn, yytext(), sym.PRGRM_INI)); }
"end"      { return new Symbol(sym.PRGRM_FIM, new TokenInfo(yyline, yycolumn, yytext(), sym.PRGRM_FIM)); }
"function" { return new Symbol(sym.FUNCTION, new TokenInfo(yyline, yycolumn, yytext(), sym.FUNCTION)); }
"procedure" { return new Symbol(sym.PROCEDURE, new TokenInfo(yyline, yycolumn, yytext(), sym.PROCEDURE)); }
"return"   { return new Symbol(sym.FUN_RETURN, new TokenInfo(yyline, yycolumn, yytext(), sym.FUN_RETURN)); }

","        { return new Symbol(sym.VIRGULA, new TokenInfo(yyline, yycolumn, yytext(), sym.VIRGULA)); }
":"        { return new Symbol(sym.DOIS_PONTOS, new TokenInfo(yyline, yycolumn, yytext(), sym.DOIS_PONTOS)); }
";"        { return new Symbol(sym.PT_VIRGULA, new TokenInfo(yyline, yycolumn, yytext(), sym.PT_VIRGULA)); }
"("        { return new Symbol(sym.L_PARENTESES, new TokenInfo(yyline, yycolumn, yytext(), sym.L_PARENTESES)); }
")"        { return new Symbol(sym.R_PARENTESES, new TokenInfo(yyline, yycolumn, yytext(), sym.R_PARENTESES)); }
"{"        { return new Symbol(sym.L_CHAVES, new TokenInfo(yyline, yycolumn, yytext(), sym.L_CHAVES)); }
"}"        { return new Symbol(sym.R_CHAVES, new TokenInfo(yyline, yycolumn, yytext(), sym.R_CHAVES)); }

"="        { return new Symbol(sym.IGUAL, new TokenInfo(yyline, yycolumn, yytext(), sym.IGUAL)); }
"+="       { return new Symbol(sym.ADD_IGUAL, new TokenInfo(yyline, yycolumn, yytext(), sym.ADD_IGUAL)); }
"-="       { return new Symbol(sym.SUB_IGUAL, new TokenInfo(yyline, yycolumn, yytext(), sym.SUB_IGUAL)); }
"<"        { return new Symbol(sym.MENOR, new TokenInfo(yyline, yycolumn, yytext(), sym.MENOR)); }
">"        { return new Symbol(sym.MAIOR, new TokenInfo(yyline, yycolumn, yytext(), sym.MAIOR)); }
"<="       { return new Symbol(sym.MENOR_IGUAL, new TokenInfo(yyline, yycolumn, yytext(), sym.MENOR_IGUAL)); }
">="       { return new Symbol(sym.MAIOR_IGUAL, new TokenInfo(yyline, yycolumn, yytext(), sym.MAIOR_IGUAL)); }
"=="       { return new Symbol(sym.IGUAL_IGUAL, new TokenInfo(yyline, yycolumn, yytext(), sym.IGUAL_IGUAL)); }
"!="       { return new Symbol(sym.DIFERENTE, new TokenInfo(yyline, yycolumn, yytext(), sym.DIFERENTE)); }

"+"        { return new Symbol(sym.ADD, new TokenInfo(yyline, yycolumn, yytext(), sym.ADD)); }
"-"        { return new Symbol(sym.SUB, new TokenInfo(yyline, yycolumn, yytext(), sym.SUB)); }
"*"        { return new Symbol(sym.MUL, new TokenInfo(yyline, yycolumn, yytext(), sym.MUL)); }
"/"        { return new Symbol(sym.DIV, new TokenInfo(yyline, yycolumn, yytext(), sym.DIV)); }

"char"     { return new Symbol(sym.TIPO_CHAR, new TokenInfo(yyline, yycolumn, yytext(), sym.TIPO_CHAR)); }
"int"      { return new Symbol(sym.TIPO_INTEIRO, new TokenInfo(yyline, yycolumn, yytext(), sym.TIPO_INTEIRO)); }
"float"    { return new Symbol(sym.TIPO_FLUTUANTE, new TokenInfo(yyline, yycolumn, yytext(), sym.TIPO_FLUTUANTE)); }

"and"    { return new Symbol(sym.AND, new TokenInfo(yyline, yycolumn, yytext(), sym.AND)); }
"or"     { return new Symbol(sym.OR, new TokenInfo(yyline, yycolumn, yytext(), sym.OR)); }

"true"   { return new Symbol(sym.BOOL_TRUE, new TokenInfo(yyline, yycolumn, yytext(), sym.BOOL_TRUE)); }
"false"  { return new Symbol(sym.BOOL_FALSE, new TokenInfo(yyline, yycolumn, yytext(), sym.BOOL_FALSE)); }

"if"     { return new Symbol(sym.IF, new TokenInfo(yyline, yycolumn, yytext(), sym.IF)); }
"else"   { return new Symbol(sym.ELSE, new TokenInfo(yyline, yycolumn, yytext(), sym.ELSE)); }
"for"    { return new Symbol(sym.FOR, new TokenInfo(yyline, yycolumn, yytext(), sym.FOR)); }
"while"  { return new Symbol(sym.WHILE, new TokenInfo(yyline, yycolumn, yytext(), sym.WHILE)); }
"do"     { return new Symbol(sym.DO, new TokenInfo(yyline, yycolumn, yytext(), sym.DO)); }

{Char}            { return new Symbol(sym.CARACTERE, new TokenInfo(yyline, yycolumn, yytext(), sym.CARACTERE)); }
{Inteiro}         { return new Symbol(sym.INTEIRO, new TokenInfo(yyline, yycolumn, yytext(), sym.INTEIRO)); }
{Flutuante}       { return new Symbol(sym.FLUTUANTE, new TokenInfo(yyline, yycolumn, yytext(), sym.FLUTUANTE)); }
{Identificador}   { return new Symbol(sym.ID, new TokenInfo(yyline, yycolumn, yytext(), sym.ID)); }
//{Identificador}  {System.out.print("<identificador:" + yytext(), sym. + ">(l:"+yyline+ " c:"+yycolumn+")");}

/* whitespace */
{WhiteSpace}                   { /* whitespace */ }
{Comentario}                   { /* comentario */ }

/* error fallback (manual jflex):
The last lexical rule in the example specification is used as an error fallback.
It matches any character in any state that has not been matched by another rule.
It doesn’t conflict with any other rule because it has the least priority
(because it’s the last rule) and because it matches only one character
(so it can’t have longest match precedence over any other rule).
*/

[^] {
    throw new ErroLexico("Erro lexico na linha "
     + (yyline + 1)
     + ", coluna "
     + yycolumn + ": caractere inesperado '"
     + yytext() + "'");
}
