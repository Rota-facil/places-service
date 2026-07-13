# places-service

Serviço de lugares do Rota Fácil. É a fonte de verdade para instituições e pontos de embarque.

## Porta e base path

- Porta: `8083`
- Context path: `/places`
- Via gateway: `http://localhost:8080/places`

## Endpoints

Instituições:

- `POST /places/institutions`
- `GET /places/institutions?page=0&size=20`
- `GET /places/institutions/{id}`
- `PUT /places/institutions/{id}`
- `DELETE /places/institutions/{id}`

Pontos de embarque:

- `POST /places/board-points`
- `GET /places/board-points?page=0&size=20`
- `GET /places/board-points/{id}`
- `PUT /places/board-points/{id}`
- `DELETE /places/board-points/{id}`

Infra: `GET /places/health-check`, `/places/v3/api-docs` e `/places/swagger-ui.html`.

Não há controller HTTP separado para endereços no código atual. Endereço, nome, latitude e longitude fazem parte dos contratos de instituição/ponto.

## Segurança

GETs exigem autenticação. POST, PUT e DELETE exigem `ADMIN` ou `SUPERUSER`. O serviço usa os headers do gateway e isola dados por prefeitura.

## Eventos publicados

Exchange `places.events`: `institution.created`, `institution.updated`, `institution.deleted`, `boarding.created`, `boarding.updated` e `boarding.deleted`.

O `transport-service` mantém cópias operacionais; o `file-service` limpa arquivos após exclusões; o `audit-service` registra as ações. Não há consumidores RabbitMQ neste serviço.

## Persistência

- Banco: `jdbc:postgresql://localhost:5433/places_database`
- Usuário padrão: `rota-facil`
- Migrations: `src/main/resources/db/migration`
- Hibernate: `ddl-auto=validate`

## Como rodar

```bash
cd places-service
./mvnw spring-boot:run
```

Requer Java 21, PostgreSQL, Eureka e RabbitMQ.
