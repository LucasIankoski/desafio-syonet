# Newsletter API
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

Esse projeto é uma API construída usando **Java, Java Spring, Postgres como banco de dados**.

O propósito usual da API é o cadastro de uma base de clientes para disponibilizar notícias diariamente ao respectivo endereço de e-mail de cada cliente. Notícias são cadastradas em um dia e processadas/encaminhadas às 08:00 da manhã do outro dia.

A API foi desenvolvida com o intuito de completar o desafio disponibilizado pela Syonet.

## Menus

- [Instalação](#instalacao)
- [Usabilidade](#usabilidade)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Configuração](#config)

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/LucasIankoski/newsletter-api.git
```

2. Instale as dependências utilizando Maven

## Usabilidade

1. Inicie a aplicação usando Maven
2. A API será acessível via http://localhost:8080


## API Endpoints
A API disponibiliza os seguintes endpoints:

**GET CLIENTES**
```markdown
GET /clientes - Monta uma lista de clientes cadastrados.
```
```json
[
  {
    "id": 1,
    "nome": "Lucas",
    "email": "lucas@digihub.com.br",
    "dtNascimento": "1996-12-22"
  },
  {
    "id": 2,
    "nome": "Pamela",
    "email": "vidalettipam@gmail.com",
    "dtNascimento": "1996-12-22"
  },
  {
    "id": 3,
    "nome": "Matheus",
    "email": "matheus@digihub.com.br",
    "dtNascimento": "1994-06-13"
  }
]
```


**GET NOTICIAS**
```markdown
GET /noticias - Monta uma lista de notícias cadastradas que ainda não foram processadas 
(não foram encaminhadas por e-mail para os usuários).
```
```json
[
  {
    "id": 7,
    "titulo": "Apple quer te deixar em paz enquanto você está no Safari",
    "descricao": "Não, não nesse safari. A partir da próxima atualização de software, a Apple vai adicionar a ferramenta de “Controle de Distração” no seu navegador padrão em iPhones, iPads e Mac, o Safari.",
    "link": "https://techcrunch.com/2024/08/05/apples-new-safari-feature-removes-distracting-items-from-websites/?guccounter=1&utm_source=the_news&utm_medium=newsletter&utm_campaign=08-08-2024&_bhlid=1d14b86217278fa285a6c574a06a8b938b53a7a6",
    "processada": false
  },
  {
    "id": 8,
    "titulo": "à la vontê",
    "descricao": "bom dia. você já tentou mudar o seu jeito só para impressionar alguém? da próxima vez, pense em escolher conviver com quem te deixa confortável para ser você.",
    "link": "",
    "processada": false
  },
  {
    "id": 9,
    "titulo": "Se não fossem os hispânicos, população dos EUA cresceria bem menos",
    "descricao": "Why it matters: Com uma população mais jovem e em crescimento, os hispânicos devem ter um impacto econômico e político maior ao movimentarem o mercado de trabalho americano nos próximos anos.",
    "link": "",
    "processada": false
  }
]
```

**POST CLIENTES**
```markdown
POST /clientes - Cadastra um cliente na base de dados.
```
```json
{
  "nome":"Lucas",
  "email":"lucas@email.com",
  "dtNascimento":"1997-06-2"
}
```

**POST NOTICIAS**
```markdown
POST /noticias - Registra uma notícia na base de dados.
```

```json

{
  "titulo": "Apple quer te deixar em paz enquanto você está no Safari",
  "descricao": "Não, não nesse safari. A partir da próxima atualização de software, a Apple vai adicionar a ferramenta de “Controle de Distração” no seu navegador padrão em iPhones, iPads e Mac, o Safari.",
  "link": "https://techcrunch.com/2024/08/05/apples-new-safari-feature-removes-distracting-items-from-websites/?guccounter=1&utm_source=the_news&utm_medium=newsletter&utm_campaign=08-08-2024&_bhlid=1d14b86217278fa285a6c574a06a8b938b53a7a6"
}
```

## Database
O projeto utiliza [PostgresSQL](https://www.postgresql.org/docs/) e foi utilizado [Docker Compose](https://hub.docker.com/_/postgres) para montar a imagem da base de dados.

## Configuração
### STMP
É necessário atribuir às variáveis de ambiente em um arquivo .env ou diretamente no arquivo .properties os valores corretos para o serviço de SMTP escolhido por você. Durante a construção desta API utilizamos os serviços SMPT do Google.

```
spring.mail.host=${SMTP_HOST}
spring.mail.port=${SMTP_PORT}
spring.mail.username=${SMTP_USERNAME}
spring.mail.password=${SMTP_PASSWORD}
spring.mail.properties.mail.smtp.auth=${SMTP_AUTH}
spring.mail.properties.mail.smtp.starttls.enable=${SMTP_STARTTLS}
```

### Database

É necessário atribuir às variáveis de ambiente em um arquivo .env ou diretamente no arquivo .properties os valores corretos para a conexão com o banco de dados. Durante a construção desta API utilizamos o Docker Compose para criar uma imagem do PostgresSQL 16.

```
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```