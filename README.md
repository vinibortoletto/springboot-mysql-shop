<div align="center">
  <h1>SpringBoot/MySQL Shop</h1>
  This user-friendly interface facilitates seamless product browsing, efficient cart management, and secure user authentication.
</div>

<hr />

<p align="center">
  <a href="#writing_hand-sobre">About</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#rocket-tecnologias">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#information_source-como-usar">Usage</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#video_camera-demo">Endpoints</a>
</p>

## :writing_hand: Sobre

A simple ecommerce API where its possible to manage users and addresses, products, cart and orders.

## :rocket: Tecnologias

This project was created with the following technologies:

- Java
- SpringBoot
- Spring Security
- JPA
- MySQL
- JUnit
- Mockito
- H2
- Java Faker
- Swagger

## :information_source: Usage

To test this application, you will need Docker and Docker Compose, then follow the steps below:

Clone repo:

```bash
git clone https://github.com/vinibortoletto/springboot-mysql-shop.git
```

Navigate to the project's directory:

```bash
cd springboot-mysql-shop
```

Run Docker Compose:

```bash
docker-compose up -d
```
      
 **Endpoints**
 
|  Path |  Method | Description |
|----------|--------------|--------------|
|`http://localhost:8081/empresa/salvar/{cnpj}`                                 | POST | Salva a empresa no banco de dados |
|`http://localhost:8081/empresa/listarEmpresa`                                 | GET | Lista todas as empresas salvas no banco de dados |
|`http://localhost:8081/empresa/buscarUserId/{id}`                             | GET | Buscar empresa baseado no Id salvo no banco de dados |
|`http://localhost:8081/empresa/buscar/{filter}`                               | GET | Buscas dados da empresa tanto por nome Fantasia quanto CNPJ |
