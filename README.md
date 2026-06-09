# places-service

Servico de lugares do Rota Facil. Mantem o cadastro de enderecos, instituicoes e pontos de embarque usados pelo dominio de transporte.

## Para que serve

- Cadastrar enderecos reutilizaveis.
- Cadastrar instituicoes com latitude/longitude.
- Cadastrar pontos de embarque com latitude/longitude.
- Publicar eventos para sincronizar `transport-service`, `file-service` e `audit-service`.

## Porta e base path

- Aplicacao: `places-service`
- Porta: `8083`
- Context path: `/places`
- Via gateway: `http://localhost:8080/places`

## Endpoints principais

Enderecos:

- `POST /places/places-address`: cria endereco.
- `GET /places/places-address`: lista enderecos.
- `GET /places/places-address/{id}`: busca endereco.
- `PUT /places/places-address/{id}`: atualiza endereco.
- `DELETE /places/places-address/{id}`: remove endereco.

Instituicoes:

- `POST /places/institutions`: cria instituicao.
- `GET /places/institutions`: lista instituicoes.
- `GET /places/institutions/{id}`: busca instituicao.
- `PUT /places/institutions/{id}`: atualiza instituicao.
- `DELETE /places/institutions/{id}`: remove instituicao.

Pontos de embarque:

- `POST /places/board-points`: cria ponto de embarque.
- `GET /places/board-points`: lista pontos.
- `GET /places/board-points/{id}`: busca ponto.
- `PUT /places/board-points/{id}`: atualiza ponto.
- `DELETE /places/board-points/{id}`: remove ponto.

Infra:

- `GET /places/health-check`
- `/places/v3/api-docs`
- `/places/swagger-ui.html`

## Dados principais

Endereco:

- `neighborhood`
- `city`
- `road`

Instituicao e ponto de embarque:

- `placesAddressId`
- `name`
- `latitude`
- `longitude`

## Seguranca

No gateway, `/places/**` exige `ADMIN` ou `SUPERUSER`. Internamente o servico monta `CurrentUser` a partir dos headers enviados pelo gateway.

## Eventos publicados

Exchange: `places.events`

- `institution.created`
- `institution.updated`
- `institution.deleted`
- `boarding.created`
- `boarding.updated`
- `boarding.deleted`

## Banco de dados

- Default: `jdbc:postgresql://localhost:5433/places_database`
- Usuario default: `rota-facil`
- Senha default: `admin`
- Migrations: `src/main/resources/db/migration`

## Como rodar

Pre-requisitos:

- Java 21.
- PostgreSQL com banco `places_database`.
- Eureka.
- RabbitMQ.

Comando:

```bash
cd places-service
./mvnw spring-boot:run
```

## Especializacao

Este servico e a fonte de verdade para instituicoes, pontos de embarque e enderecos. O `transport-service` mantem uma copia operacional desses dados por eventos para calcular rotas e viagens.
