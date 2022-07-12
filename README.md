    API REST que implementa recursos para gerenciamento de um cadastro de contatos


### Sobre o projeto:
A API REST de gerenciamento de cadastro permite realizar operações CRUD de contatos, onde basta digitar o cep pra que seja retornado automaticamente o endereço através do consumo da ApiCep.

### A arquitetura do projeto é formada por:
EndPoints que podem ser usados por uma aplicação FrontEnd
Testes unitários de funcionalidades básicas do projeto
Exemplo de um database com relacionamentos do tipo OneToOne

### Tecnologias utilizadas:
O projeto foi criado usando as seguintes tecnologias:
Java 17
Spring framework
Maven
Banco de dados MySQL
Intellij

### Instalando:
Clone o repositório
`git clone`
`cd api-rest`
`run`

### Acesso aos endpoints
Postman (importe a collection disponibilizada: Claro.postman_collection.json)
ou
Swagger
http://localhost:8080/swagger-ui/index.html#/

### Acesso aos dados
Configurar um client MySQL ou utilizar o H2: http://localhost:8080/h2/ senha: admin

##### Features
- [ ] Aplicar padrão DTO 
- [ ] Tratar todas as possíveis exceções
- [ ] Aumentar a cobertura de testes
