//Seção de código do usuário (user code)
import java_cup.runtime.Symbol;import jflex.core.sym;
%%
//Seção de opções e declarações (options and declarations)

%class Lexer

//Set de caracteres
%unicode
//Compatibilidade com cup
%cup
//Conta linhas (acessivel através de yyline)
%line
//Conta colunas (acessivel através de yycolumn)
%column
%caseless
%debug

//Macros
Letra = [a-zA-Z]
Digito = [0-9]
Palavra = {Letra}*
Inteiro = {Digito}+
Identificador = {Letra}({Letra} | {Digito} | "_")*

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]


%%
//Seção de regras (lexical rules)

{Inteiro}           {return new Symbol(sym.DIGITCLASS, yyline, yycolumn, yytext());}
{Palavra}          {System.out.print("<palavra:" + yytext() + ">(l:"+yyline+ " c:"+yycolumn+")");}
{Identificador}    {System.out.print("<identificador:" + yytext() + ">(l:"+yyline+ " c:"+yycolumn+")");}
{LineTerminator}   {System.out.println("<fim_linha>");}

fim {return new Symbol(sym.EOF, yyline, yycolumn, yytext());}