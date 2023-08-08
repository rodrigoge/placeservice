## Place Service
An api web developed for the challenge made by ClickBus.

### 🧰 Tools
- Java 20
- Spring Boot 3
- Spring Framewrok 6
- PostgreSQL
- Map Struct
- Flyway Migration
- Lombok

### 📝 API Documentation
#### Create a new place

```http
POST /v1/place/create
```

| Request Body   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **Required**. The name of the place to be registered. |
| `slug` | `string` | **Required**. The slug of the place to be registered. |
| `city` | `string` | **Required**. The city of the place to be registered. |
| `state` | `string` | **Required**. The state of the place to be registered. |

#### Get a only place by id

```http
GET /v1/place/get-by-id/{placeId}
```

| Path Param   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `placeId` | `string` | **Required**. The ID of the place to be found. |

#### Get all places

```http
GET /v1/place/get-all
```

| Query Param   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `offset` | `number` | **Required**. The page number to be found. |
| `limit` | `number` | **Required**. The page limit to be found. |
| `order` | `string` | **Required**. The page order to be found. (asc, desc) |
| `sort` | `string` | **Required**. The page sort to be found. (name, slug, city, state) |
| `name` | `string` | The name of the place to be found. |
| `slug` | `string` | The slug of the place to be found. |
| `city` | `string` | The city of the place to be found. |
| `state` | `string` | The state of the place to be found. |

#### Edit a only place by id

```http
PATCH /v1/place/edit-by-id/{placeId}
```
| Path Param   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `placeId` | `string` | **Required**. The ID of the place to be found. |

| Request Body   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | The name of the place to be registered. |
| `slug` | `string` | The slug of the place to be registered. |
| `city` | `string` | The city of the place to be registered. |
| `state` | `string` | The state of the place to be registered. |

### 🙋🏽‍♂️ Author
- [Rodrigo Gouveia](https://www.github.com/rodrigoge)

### 🤝 References
 - [Spring.io](https://spring.io/)
 - [Youtube - Giuliana Bezerra](https://www.youtube.com/watch?v=SsWZ4O9iWuo&t=13s&ab_channel=GiulianaBezerra)
 - [Backend Developer - Quero ser ClickBus](https://github.com/RocketBus/quero-ser-clickbus/tree/master/testes/backend-developer)
