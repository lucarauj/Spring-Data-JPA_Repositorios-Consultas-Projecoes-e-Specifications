# Spring Data JPA Repositórios, Consultas, Projeções e Specifications

<br>

## Projeto base:

- https://start.spring.io/
- Gerenciador de dependências: Maven
- Linguagem: Java
- Dependência: Spring Data JPA / MariaDB Java Client / Spring Web

<br>

## Configurando a conexão com o banco de dados (`application.properties`):

```
1. `spring.datasource.testWhileIdle=true`: indica que, durante a inatividade, o pool de conexões do Spring Boot testará as conexões ociosas antes de reutilizá-las, garantindo que estejam válidas antes de serem entregues para utilização.

2. `spring.datasource.validationQuery=SELECT 1`: define a consulta SQL que será usada para validar a conexão com o banco de dados. Nesse caso, a consulta simples `SELECT 1` é usada para verificar se a conexão está ativa.

3. `spring.datasource.driver-class-name=org.mariadb.jdbc.Driver`: especifica a classe do driver JDBC que o Spring Boot deve usar para se conectar ao banco de dados MariaDB.

4. `spring.jpa.show-sql=false`: define se as consultas SQL executadas pelo JPA serão exibidas no console. Quando definido como `false`, as consultas não serão exibidas.

5. `spring.jpa.hibernate.ddl-auto=update`: determina a estratégia de geração do esquema do banco de dados. Neste caso, significa que o Hibernate atualizará automaticamente o esquema do banco de dados com base nas alterações nas entidades do JPA.

6. `spring.jpa.hibernate.naming-strategy=org.hibernate.cfg.ImprovedNamingStrategy`: define a estratégia de nomeação de tabelas e colunas usada pelo Hibernate. Neste exemplo, está usando a estratégia de nomeação aprimorada do Hibernate.

7. `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect`: define o dialeto do banco de dados usado pelo Hibernate. Isso permite que o Hibernate gere consultas SQL compatíveis com o MariaDB.

```

<br>

## Anotações:

- @Entity
- @Table
- @Id
- @GeneratedValue(strategy = GenerationType.IDENTITY)
- @Repository

<br>

