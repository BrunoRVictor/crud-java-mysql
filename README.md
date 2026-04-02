# 🛒 Sistema de Gerenciamento — Java + MySQL

Sistema de gerenciamento de clientes, produtos e pedidos desenvolvido em Java com conexão a banco de dados MySQL, utilizando o padrão DAO e Maven como gerenciador de dependências.

---

## 📋 Funcionalidades

- Cadastrar, listar, atualizar e deletar **clientes**
- Cadastrar, listar, atualizar e deletar **produtos**
- Realizar e listar **pedidos** vinculando clientes e produtos
- Validação de entradas no terminal
- Persistência de dados com MySQL

---

## 🏗️ Estrutura do Projeto

```
src/
└── main/
    ├── java/
    │   └── sistema/
    │       ├── dao/          # Acesso ao banco de dados (DAO)
    │       ├── model/        # Entidades (Cliente, Produto, Pedido)
    │       ├── service/      # Regras de negócio (GerenciaService)
    │       └── Main.java     # Ponto de entrada
    └── resources/
        └── banco.properties  # Configurações do banco (não versionado)
```

---

## 🚀 Como rodar

### Pré-requisitos

- Java 17+
- Maven
- MySQL

### Configuração do banco

Crie o banco de dados e as tabelas:

```sql
CREATE DATABASE armazem;

USE armazem;

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    quantidade INT NOT NULL
);

CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    idProduto INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES cliente(id),
    FOREIGN KEY (idProduto) REFERENCES produto(id)
);
```

### Configuração do arquivo de propriedades

Crie o arquivo `src/main/resources/banco.properties`:

```properties
banco.url=jdbc:mysql://localhost/armazem
banco.user=seu_usuario
banco.password=sua_senha
```

> ⚠️ Este arquivo não foi versionado por conter credenciais.

### Dependência do MySQL no Maven

No `pom.xml`, adicione dentro de `<dependencies>`:

```xml
<dependencies>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.3.0</version>
    </dependency>
</dependencies>
```

Depois recarregue o Maven na sua IDE para baixar o driver automaticamente.

### Executando

Rode diretamente pela sua IDE ou, se quiser:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="sistema.Main"
```

---

## 🛠️ Tecnologias

- Java
- MySQL
- JDBC
- Maven
- Padrão DAO

---
