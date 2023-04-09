//Seção de código do usuário (user code)

%%
//Seção de opções e declarações (options and declarations)
%standalone
%class Lexer

//Set de caracteres
%unicode
//Compatibilidade com cup
//%cup
//Conta linhas (acessivel através de yyline)
%line
//Conta colunas (acessivel através de yycolumn)
%column
%caseless

//Macros
Letra = [a-zA-Z]
Digito = [0-9]
Palavra = {Letra}*
Numero = {Digito}+
Identificador = {Letra}({Letra} | {Digito} | "_")*

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]


%%
//Seção de regras (lexical rules)

{Numero}           {System.out.print("<numero:" + yytext() + ">(l:"+yyline+ " c:"+yycolumn+")");}
{Palavra}          {System.out.print("<palavra:" + yytext() + ">(l:"+yyline+ " c:"+yycolumn+")");}
{Identificador}    {System.out.print("<identificador:" + yytext() + ">(l:"+yyline+ " c:"+yycolumn+")");}
{LineTerminator}   {System.out.println("<fim_linha>");}
