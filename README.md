# 📌 **OS Management – API REST para Gestão de Ordens de Serviço**

API REST desenvolvida em **Java + Spring Boot**, seguindo **arquitetura multicamadas**, para gerenciar:

* 👤 **Clientes**
* 🧰 **Técnicos**
* 📝 **Ordens de Serviço**

O projeto utiliza **boas práticas**, como DTOs, exception handler global, documentação com Swagger, validação com Jakarta, persistência com JPA/Hibernate e integração com PostgreSQL.

---

## 🚀 **Tecnologias Utilizadas**

### 🖥️ Back-end

* **Java 17**
* **Spring Boot 3.5.7**
* Spring Web
* Spring Data JPA
* Hibernate
* Lombok
* Jakarta Validation
* SpringDoc OpenAPI (Swagger UI)

### 🗄️ Banco de Dados

* **PostgreSQL 18**

---

## 📁 **Arquitetura do Projeto**

```
src/main/java/com/mejuloli/os_management
│
├── controller        # Endpoints REST
├── service           # Regras de negócio
├── repository        # Acesso ao banco (JPA)
├── model             # Entidades JPA
├── dto               # Transferência de dados
├── exception         # Tratamento global de erros
└── OsManagementApplication.java
```

### **Descrição da Arquitetura**

* ✔ **Arquitetura multicamadas**
* ✔ **Controllers → Services → Repositories**
* ✔ **DTOs para isolamento das entidades**
* ✔ **Exception Handler global estruturado**
* ✔ **JPA + Hibernate integrados ao PostgreSQL**

---

## 🗺️ **Modelagem das Entidades**

### 👤 **Client**

* `id`
* `name`
* `email`
* `phone`

### 🧰 **Technician**

* `id`
* `name`
* `specialty`

### 📝 **ServiceOrder**

* `id`
* `client`
* `technician`
* `description`
* `priority` (`LOW`, `MEDIUM`, `HIGH`)
* `status` (`OPEN`, `IN_PROGRESS`, `CLOSED`)
* `openedAt`
* `closedAt`

---

## 🔧 **Como Rodar o Projeto**

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/mejuloli/os_management.git
cd os_management
```

### 2️⃣ Criar o banco no PostgreSQL

```sql
CREATE DATABASE os_management;
```

---

### 3️⃣ **Configurar seu arquivo `application.properties`**

O projeto contém um arquivo modelo:

```
src/main/resources/template_application.properties
```

Para ativar suas configurações locais, faça o seguinte:

1. **Renomeie** o arquivo:

```
application.properties
```

2. **Atualize** os campos `SEU_USUARIO_AQUI` e `SUA_SENHA_AQUI`:

```properties
# ===== configuração de conexão com o banco postgresql =====
spring.datasource.url=jdbc:postgresql://localhost:5432/os_management
spring.datasource.username=SEU_USUARIO_AQUI
spring.datasource.password=SUA_SENHA_AQUI

# ===== configurações do hibernate e geração das tabelas =====
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# ===== porta http em que a api vai subir =====
server.port=8080
```

🔒 **Este arquivo NÃO está no controle de versão**, por segurança.

---

### 4️⃣ Rodar o projeto

```bash
mvn spring-boot:run
```

API disponível em:

👉 [http://localhost:8080](http://localhost:8080)

---

## 📘 **Documentação da API (Swagger)**

Após iniciar o projeto:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

Permite testar:

* CRUD de clientes
* CRUD de técnicos
* CRUD de ordens de serviço
* Atualização de status
* Validações automáticas

---

## 🧪 **Exemplos de Requisições**

### ➤ Criar Cliente

`POST /api/clients`

```json
{
  "name": "Cliente Teste",
  "email": "cliente@teste.com",
  "phone": "41999999999"
}
```

---

### ➤ Criar Técnico

`POST /api/technicians`

```json
{
  "name": "Técnico 1",
  "specialty": "redes"
}
```

---

### ➤ Criar Ordem de Serviço

`POST /api/orders`

```json
{
  "clientId": 1,
  "technicianId": 1,
  "description": "computador não liga",
  "priority": "HIGH"
}
```

---

### ➤ Atualizar Status da OS

`PUT /api/orders/1`

```json
{
  "clientId": 1,
  "technicianId": 1,
  "description": "computador não liga",
  "priority": "HIGH",
  "status": "CLOSED"
}
```

---

## ⚠️ **Tratamento de Erros**

A API possui um handler global que padroniza respostas de erro:

### Exemplo — 404 Not Found

```json
{
  "timestamp": "2025-11-17T20:47:00",
  "status": 404,
  "error": "not found",
  "message": "client not found: 99",
  "path": "/api/clients/99"
}
```

Outros erros tratados:

* `400` Validation Error
* `500` Internal Server Error

---

## 📄 **Licença**

Este projeto é livre para uso, estudo e modificação.
