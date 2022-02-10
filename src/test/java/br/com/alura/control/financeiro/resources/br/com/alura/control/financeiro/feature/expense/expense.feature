#language: pt

Funcionalidade: Buscar, inserir, alterar e deletar despesas

    Esquema do Cenario: Listar as despesas

    Dado que informo pagina "<offset>"
    E que informo o tamanho "<limit>"
    Quando solicito a consulta
    Entao o status deve retornar <status> 

    Exemplos: 
      | offset | limit | status |
      |      0 |    10 |    200 |
      |        |       |    200 |