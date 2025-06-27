# API Rest para serviço de empréstimo de livros

Sistema backend para gerenciamento de livros, leitores e empréstimos, desenvolvido com **Spring Boot**. Permite cadastrar, editar, buscar, excluir e realizar operações de empréstimo e devolução de livros.

---

## Integrantes
- Patricia Morais
- Yasmin Bez Fontana

---

## Descrição do Problema
Gerenciar livros e empréstimos manualmente pode ser ineficiente para uma biblioteca, sujeito a erros e difícil de escalar. Este sistema automatiza esse processo, facilitando a gestão de empréstimos e a consulta de livros e leitores.

---

##  Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.3
- Spring Web
- Spring Data JPA
- Hibernate Validator
- Banco de Dados H2 (em memória)
- Swagger (OpenAPI) para documentação

---

##  Limitações

- Banco de dados H2 em memória: os dados são perdidos ao reiniciar.
- Não possui autenticação/autorização.
- Validações básicas.

---

##  Entidades

### `LeitorEntities`
- `UUID id`
- `String nome`
- `String contato`

### `LivrosEntities`
- `UUID id`
- `String nome`
- `String autor`
- `String descricao`
- `String codigoISBN`
- `Boolean disponibilidade`

### `EmprestimoEntities`
- `UUID id`
- `UUID idLivro`
- `UUID idLeitor`
- `LocalDate dataEmprestimo`
- `LocalDate dataDevolucao`

---

##  Rotas

### 📚 Livros `/api/livros`
- `GET /livros` → Lista todos os livros.
- `GET /{id}` → Retorna livro por ID.
- `POST /InserirNovoLivro` → Cadastra novo livro.
- `PATCH /{id}` → Atualiza livro.
- `DELETE /{id}` → Remove livro.
- `GET /buscarLivros?nome=&autor=&codigoISBN=` → Busca por filtros (parâmetros opcionais).

### 👤 Leitores `/api/leitores`
- `GET /leitores` → Lista todos os leitores.
- `GET /{id}` → Retorna leitor por ID.
- `POST /inserirNovo` → Cadastra novo leitor.
- `PATCH /{id}` → Atualiza leitor.
- `DELETE /{id}` → Remove leitor.

### 🔄 Empréstimos `/api/emprestimos`
- `GET /emprestimos` → Lista todos os empréstimos.
- `GET /{id}` → Empréstimo por ID.
- `POST /` → Cria novo empréstimo.
- `DELETE /{id}` → Remove empréstimo.
- `PATCH /{id}/devolver` → Devolve um livro.

---

##  Exemplos de Erros HTTP

- `404 Not Found`: recurso não encontrado.
- `400 Bad Request`: dados inválidos ou campos ausentes.
- `201 Created`: recurso criado com sucesso.

---

##  Como executar localmente

1. Clone o repositório:
```bash
git clone https://github.com/patriciamrs/BackendFINAL.git
```

2. Navegue até a pasta:
```bash
cd BackendFINAL
```

3. Execute com Maven:
```bash
./mvnw spring-boot:run
```

4. Acesse a documentação Swagger:
```
http://localhost:8080/swagger-ui.html
```

---

## 📋 Observações
- Recomendado usar o Insomnia ou Swagger UI para testar.
- Projeto estruturado em camadas: Controller, Service, Repository e DTOs.

---

