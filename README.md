## Documentación del proyecto

Descargar proyecto, abrirlo con algún IDE para mayor comodidad y eejecutarlo.
El proyecto se ejecutará en el puerto 8080 y la base de datos se construirá automáticamente, se adjunta un archivo json para las pruebas a los endpoints.

### Documentación de Endpoints

A continuación se detalla la documentación de los endpoints disponibles en este servicio:

Nota: Para detalles más técnicos puede acceder a: http://localhost:8080/swagger-ui.html

---

#### 1. Registro de Persona

- **Método:** `POST`
- **Ruta:** `/apis/people-v1/sign-up`
- **Descripción:** Este endpoint permite registrar una nueva persona.
- **Código de Estado HTTP:** `201 (CREATED)`
  - **Cuerpo de la Solicitud (Request Body):** Debe incluir los datos de la persona a registrar.
    - Formato: JSON
    - Ejemplo:
      ```json
      {
        "name": "diego",
        "email": "diego@gmail.com",
        "password": "123Diego$",
        "phones" : [
          {
            "number": "912345678",
            "cityCode": "12",
            "countryCode": "12"
          },
          {
            "number": "912345888",
            "cityCode": "12",
            "countryCode": "12"
          }
        ]
      }
      ```
  - **Respuesta (Response):** Retorna los datos de la persona registrada.
    - Formato: JSON
    - Ejemplo:
      ```json
      {
        "id": "0352dfd9-f5b0-4376-b479-d5bf775d7f9e",
        "name": "diego",
        "createdAt": "2024-09-30 00:47:58",
        "updatedAt": "2024-09-30 00:47:58",
        "lastLogin": "2024-09-30 00:47:58",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMzUyZGZkOS1mNWIwLTQzNzYtYjQ3OS1kNWJmNzc1ZDdmOWUiLCJpYXQiOjE3Mjc2NzUyNzgsImV4cCI6MTcyNzY3NTU3OH0.QR-pZ7MmXNGHWfoYPX8T9y_K4U5-9vCorE_-uvVyN3o",
        "active": true
      }
      ```

---

#### 2. Lista de Personas

- **Método:** `GET`
- **Ruta:** `/apis/people-v1/list-people`
- **Descripción:** Este endpoint proporciona una lista de todas las personas registradas.
- **Código de Estado HTTP:** `200 (OK)`
- **Respuesta (Response):** Retorna una lista de personas con detalles resumidos.
  - Formato: JSON
  - Ejemplo:
    ```json
    [
      {
        "name": "diego",
        "email": "diego@gmail.com",
        "password": "1BF6F2CD1961FE98C0A06DA62A3E6AB76D94E4C7D759CC76E0D52DF9CDF4044D",
        "updatedAt": "2024-09-30 00:55:48",
        "lastLogin": "2024-09-30 00:55:48",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjOGE4ODUxZS00MzFkLTQ2OGItOWU5Yy1hMmFlNjMxMzcxODkiLCJpYXQiOjE3Mjc2NzU3NDgsImV4cCI6MTcyNzY3NjA0OH0.j_WqUcB2yZcDkEl5hpevYtgdWjfC-6HNXQYrHnzl_F0",
        "phones": [
          {
            "id": "aaad2aee-e199-40d6-9a1c-7399635a3fc0",
            "number": "912345678",
            "cityCode": "12",
            "countryCode": "12"
          },
          {
            "id": "c2d20c77-228e-4b4c-9afc-aff72b3e5811",
            "number": "912345888",
            "cityCode": "12",
            "countryCode": "12"
          }
        ],
        "active": true
      }
    ]
    ```

---

#### 3. Login

- **Método:** `POST`
- **Ruta:** `/apis/person-v1/login`
- **Descripción:** Este endpoint permite loguearse.
- **Código de Estado HTTP:** `200 (OK)`
  - **Cuerpo de la Solicitud (Request Body):** Debe incluir las credenciales de la cuenta a iniciar sesión.
    - Formato: JSON
    - Ejemplo:
      ```json
      {
        "email": "diego@gmail.com",
        "password": "123Diego$"
      }
      ```
  - **Respuesta (Response):** Retorna infomación del inicio de sesión.
    - Formato: JSON
    - Ejemplo:
      ```json
      {
        "message": "Login successful",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMzUyZGZkOS1mNWIwLTQzNzYtYjQ3OS1kNWJmNzc1ZDdmOWUiLCJpYXQiOjE3Mjc2NzUyNzgsImV4cCI6MTcyNzY3NTU3OH0.QR-pZ7MmXNGHWfoYPX8T9y_K4U5-9vCorE_-uvVyN3o"
      }
      ```

---

#### 4. Eliminación Lógica de Persona

- **Método:** `DELETE`
- **Ruta:** `/apis/people-v1/delete/{email}`
- **Descripción:** Realiza una eliminación lógica de la persona con el email dado.
- **Código de Estado HTTP:** `202 (ACCEPTED)`
  - **Parámetros de Ruta:**
    - `{email}`: El email de la persona que se desea eliminar.
  - **Respuesta (Response):** Retorna un valor booleano indicando si la eliminación fue exitosa.
    - Formato: JSON
    - Ejemplo:
      ```json
      true
      ```

---
