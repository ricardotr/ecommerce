# ecommerce
Este proyecto es un demo de ecommerce

como usarlo:

Hay que logarse para obtener el token de acceso(Hay quie utilizar el usuario demouser):

curl --location 'http://localhost:8080/api/auth/v1/login' --header 'Content-Type: application/json' --data '{"username":"demouser","password":"demouser1"}'

Luego utilizar el token para acceder al endpoint de Prices.

curl --location 'http://localhost:8080/api/ecommerce/v1/prices?&&applicationDate=2020-06-15T17%3A00%3A00' --header 'Authorization: tokenJWS'

Se puede confirmar la documentación de la API con el cliente swagger:
