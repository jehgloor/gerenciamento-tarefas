Gerenciamento de Tarefas
============================



Este é um projeto de Gerenciamento de Tarefas desenvolvido com Spring Boot. 
A aplicação permite a gestão de departamentos, pessoas e tarefas, 
e está configurada para usar PostgreSQL como banco de dados.

Requisitos
----------

* JDK 17 ou superior
* Maven 3.6 ou superior
* Docker e Docker Compose

Configuração do Ambiente
----------
A aplicação está configurada para usar um banco de dados PostgreSQL. Você pode configurar o PostgreSQL usando Docker Compose.
1. Inicie o serviço PostgreSQL com Docker Compose:

`docker-compose up`

2. Verifique se o contêiner está em execução:

`docker-compose ps`