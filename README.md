<h1 align="center">
  Company Restful API
</h1>

<p align="center">
  <a href="#-technologies">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-project">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-configuration">Configuração</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-developing">Construir e Executar</a>
</p>

<br>

## ✨ Technologies

- [Mysql](https://dev.mysql.com/downloads/mysql/)
- [Java](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito](https://site.mockito.org)
- [Swagger](https://swagger.io/)

## 💻 Projeto

company-api é um web-service desenvolvido em Java com SpringBoot e atinge os níveis de maturidade necessários para a API ser considerada Restful (HATEOAS, Http verbs, Resources). Além disso, a API segue boas práticas, como o tratamento de exceções, paginação, documentação com swagger e desenvolvimento em camadas.
Também foram realizados testes unitários da camada de serviços.

## 🛠️ Configuração

O projeto requer um banco de dados Mysql, então é necessário criar uma base de dados com os seguintes comandos:

```
$ sudo mysql

CREATE USER 'root'@'%' IDENTIFIED BY 'mysqlRoot2023';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;

exit

$ mysql -u user -p

CREATE DATABASE company;

exit
```

Durante a execução, as tabelas de banco já serão criadas automaticamente no banco de dados.

## 🚀 Construir e Executar

Para construir e testar, execute o comando:

```sh
$ ./mvnw clean verify
```

Depois de executar esse comando, você pode acessar os endpoints da API no endereço: http://localhost:8080/swagger-ui/index.html
