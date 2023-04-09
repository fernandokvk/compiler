//Seção de código do usuário (user code)
package flex;
import java_cup.runtime.Symbol;
import cup.sym;
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
%debug

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
program  { return new Symbol(sym.PRGRM_DEF);}
begin    { return new Symbol(sym.PRGRM_INI);}
end      { return new Symbol(sym.PRGRM_FIM);}

","      { return new Symbol(sym.VIRGULA);}
//"."      { return new Symbol(sym.PONTO);}
":"      { return new Symbol(sym.DOIS_PONTOS);}
";"      { return new Symbol(sym.PT_VIRGULA);}

"("      { return new Symbol(sym.L_PARENTESES);}
")"      { return new Symbol(sym.R_PARENTESES);}
"{"      { return new Symbol(sym.L_CHAVES);}
"}"      { return new Symbol(sym.R_CHAVES);}

"="      { return new Symbol(sym.IGUAL);}
"+="      { return new Symbol(sym.ADD_IGUAL);}
"-="      { return new Symbol(sym.SUB_IGUAL);}

"<"      { return new Symbol(sym.MENOR);}
">"      { return new Symbol(sym.MAIOR);}
"<="      { return new Symbol(sym.MENOR_IGUAL);}
">="      { return new Symbol(sym.MAIOR_IGUAL);}
"=="     { return new Symbol(sym.IGUAL_IGUAL);}
"!="     { return new Symbol(sym.DIFERENTE);}

"+"      { return new Symbol(sym.ADD);}
"-"      { return new Symbol(sym.SUB);}
"*"      { return new Symbol(sym.MUL);}
"/"      { return new Symbol(sym.DIV);}

"char"   { return new Symbol(sym.TIPO_CHAR);}
"int"    { return new Symbol(sym.TIPO_INTEIRO);}
"float"  { return new Symbol(sym.TIPO_FLUTUANTE);}

"and"    { return new Symbol(sym.AND);}
"or"     { return new Symbol(sym.OR);}
//"not"    { return new Symbol(sym.NOT);}
"true"   { return new Symbol(sym.BOOL_TRUE);}
"false"  { return new Symbol(sym.BOOL_FALSE);}

"if"    { return new Symbol(sym.IF);}
"else"  { return new Symbol(sym.ELSE);}
"for"   { return new Symbol(sym.FOR);}
"while" { return new Symbol(sym.WHILE);}
"do"    { return new Symbol(sym.DO);}

{Char}            { return new Symbol(sym.CARACTERE);}
{Inteiro}         { return new Symbol(sym.INTEIRO);}
{Flutuante}       { return new Symbol(sym.FLUTUANTE);}
{Identificador}   { return new Symbol(sym.ID);}
//{Identificador}  {System.out.print("<identificador:" + yytext() + ">(l:"+yyline+ " c:"+yycolumn+")");}

/* whitespace */
{WhiteSpace}                   { /* whitespace */ }
{Comentario}                   { /* comentario */ }