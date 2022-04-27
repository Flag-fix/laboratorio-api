# laboratorio-api


Sistema Gerado para Consulta de Pessoas Por Tipo Sanguíneo


Java *v 11*

Spring *v 2.6.1*

BD *Mysql*

Importar Projeto 

Rodar comandos para maven instalar dependencias

Json para popular Banco de dados

    Arquivos/data.json

Em *DataConfiguration* alterar linhas:

    dataSource.setUrl("jdbc:mysql://URLBANCO:3306/NOME_BANCO?useTimezone=true&serverTimezone=America/Sao_Paulo");
    dataSource.setUsername("USER_BANCO");
Obs: Criar banco de dados antes de alterar as configurações