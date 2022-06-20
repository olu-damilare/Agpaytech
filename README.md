# Country app

This application is built to perform create and read functionalities. A user can save a country with a unique name on the application, fetch the paginated list of countries saved on the application and search for countries matching specific characters.

## Installation

Step 1: Clone repository

Step 2: Install dependencies: `cd` into the application root folder and run the following command: `mvn install`

Step 3: Run the project from your terminal using `mvn spring-boot:run`
    
## API Reference

The application runs on port 8080

#### Add a new country

```http
  POST http://localhost:8080/api/v1/country
```

| Request body | Type     | Description                | Required |
| :-------- | :------- | :------------------------- |:------------------------- |
| `name` | `string` | The name of the country | `true`|


##### Request body sample

```json
{
  "name": "Egypt"
}
```
#### Get countries

```http
  GET http://localhost:8080/api/v1/country
```

| Parameter | Type     | Description                       |Required|
| :-------- | :------- | :-------------------------------- | :-------------------------------- |
| `pageNumber`      | `Integer` |Country list response page number|`false`|
| `pageSize`      | `Integer` |Country list response page size|`false`|
| `searchBy`      | `String` |This filters the countries and returns a list of countries matching the searchBy value |`false`|




## Author

- [@olu-damilare](https://www.github.com/olu-damilare)

