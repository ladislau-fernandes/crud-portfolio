# CRUD Portfólio - Spring Boot

Projeto **CRUD simples** com **Spring Boot**, **Lombok** e banco em **H2**, usado como base para aprendizado e evolução do projeto.

---

## Tecnologias

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database
* Lombok
* Postman (para testes)

---

## Estrutura do Projeto

```
crud-portfolio/
├── src/
│   ├── main/
│   │   ├── java/com/ladislau/crud_portfolio/
│   │   │   ├── controller/ProjectController.java
│   │   │   ├── model/Project.java
│   │   │   ├── repository/ProjectRepository.java
│   │   │   └── CrudPortfolioApplication.java
│   │   └── resources/application.properties
├── pom.xml
└── README.md
```

---

## Configuração do Banco H2

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Acesse o console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## Endpoints

| Método | Endpoint       | Descrição                     |
| ------ | -------------- | ----------------------------- |
| GET    | /projects      | Lista todos os projetos       |
| GET    | /projects/{id} | Retorna um projeto específico |
| POST   | /projects      | Cria um novo projeto          |
| PUT    | /projects/{id} | Atualiza um projeto existente |
| DELETE | /projects/{id} | Remove um projeto pelo ID     |

---

## Exemplo de Requisição (POST `/projects`)

```json
{
  "title": "Meu Portfólio",
  "description": "Projeto CRUD com Spring Boot",
  "link": "https://github.com/ladislau-fernandes"
}
```

---

## Como Rodar

1. Clone o repositório:

```bash
git clone https://github.com/ladislau-fernandes/crud-portfolio.git
```

2. Acesse a pasta:

```bash
cd crud-portfolio
```

3. Rode o projeto:

```bash
mvn spring-boot:run
```

4. Teste os endpoints no Postman ou navegador:

```
http://localhost:8080/projects
```

---

## Próximos Passos

* Adicionar **Swagger**
* Autenticação **JWT**
* Camada de **Service e DTOs**
* Testes com **JUnit e Mockito**
* Deploy em nuvem (**Render / Railway**)

---

## Autor

**Ladislau Fernandes Marques Nagy**
📍 Madre de Deus/BA
