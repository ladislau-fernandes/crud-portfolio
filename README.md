🧩 CRUD Portfólio - Spring Boot

Um projeto CRUD simples desenvolvido em Spring Boot, com Lombok e banco em H2, destinado a servir como base para aprendizado e evolução contínua.
O objetivo é refatorar e aprimorar este projeto com funcionalidades futuras como autenticação JWT, testes automatizados, documentação Swagger, entre outros.

🚀 Tecnologias Utilizadas

Java 17+

Spring Boot 3

Spring Web

Spring Data JPA

H2 Database

Lombok

Postman (para testes HTTP)

🎯 Objetivo do Projeto

Criar uma API REST que realiza operações CRUD (Create, Read, Update e Delete) em uma entidade Project.
Cada projeto possui:

id (Long)

title (String)

description (String)

link (String)

O sistema utiliza banco H2 em memória, facilitando testes locais.

📁 Estrutura de Pastas
crud-portfolio/
├── src/
│   ├── main/
│   │   ├── java/com/ladislau/crud_portfolio/
│   │   │   ├── controller/
│   │   │   │   └── ProjectController.java
│   │   │   ├── model/
│   │   │   │   └── Project.java
│   │   │   ├── repository/
│   │   │   │   └── ProjectRepository.java
│   │   │   └── CrudPortfolioApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql (opcional)
├── pom.xml
└── README.md

⚙️ Configuração do Banco H2

No arquivo application.properties:

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console


Acesse o console do H2:

http://localhost:8080/h2-console


JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (vazio)

🧠 Endpoints da API
Método	Endpoint	Descrição
GET	/projects	Lista todos os projetos
GET	/projects/{id}	Retorna um projeto específico
POST	/projects	Cria um novo projeto
PUT	/projects/{id}	Atualiza um projeto existente
DELETE	/projects/{id}	Remove um projeto pelo ID
📬 Exemplo de Requisição (via Postman)
Criar um Projeto (POST /projects)
{
  "title": "Meu Portfólio",
  "description": "Projeto CRUD com Spring Boot",
  "link": "https://github.com/ladislau-fernandes"
}

Retorno Esperado
{
  "id": 1,
  "title": "Meu Portfólio",
  "description": "Projeto CRUD com Spring Boot",
  "link": "https://github.com/ladislau-fernandes"
}

▶️ Como Rodar o Projeto

Clone o repositório:

git clone https://github.com/ladislau-fernandes/crud-portfolio.git


Acesse a pasta:

cd crud-portfolio


Compile e execute:

mvn spring-boot:run


Teste os endpoints usando Postman ou navegador:

http://localhost:8080/projects

🧩 Próximas Implementações

 Documentação interativa com Swagger

 Autenticação JWT

 Camada de Service e DTOs

 Testes com JUnit e Mockito

 Deploy em nuvem (Render / Railway)

👨‍💻 Autor

Ladislau Fernandes Marques Nagy
📍 Madre de Deus/BA
