# CryptoCurrenciesApp
This application is a cryptocurrencies manager, where you can find 
different currencies and compare their prices.

### 💡 Features:
* GET /cryptocurrencies/minprice?name=[currency_name]
* GET /cryptocurrencies/maxprice?name=[currency_name]
* GET /cryptocurrencies?name=[currency_name]&page=[page_number]&size=[page_size]
* GET /cryptocurrencies/csv


### 🚀  Project structure:
**Application has an N-Tier Architecture**
* Controller - This level allows the user to work with this application.
* Service - This level of architecture is responsible for processing the data received from the DAO level.
* Repository - This level of architecture is responsible for communicating with the database.

### ⚙ Technologies:
- Java 17
- Maven
- MongoDb
- Lombok
- Spring Boot

INSTRUCTIONS FOR LAUNCHING PROJECT:
1) Clone this repository 
2) Open project from Version Control
3) Edit application.properties - set the necessary parameters'

* spring.data.mongodb.host=
* spring.data.mongodb.port=
* spring.data.mongodb.database=

4) Run project