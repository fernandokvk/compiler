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

//Macros
Letra = [a-zA-Z]
Digito = [0-9]
Palavra = {Letra}*
Numero = {Digito}+

%%
//Seção de regras (lexical rules)

{Numero} {System.out.print("Numero");}
{Palavra} {System.out.print("Palavra");}