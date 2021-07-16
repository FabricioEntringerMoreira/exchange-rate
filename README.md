<br />
<p align="center">
<h1 align="center">Exchange Rate</h1>



<!-- ABOUT THE PROJECT -->
### About The Project

This project implements an exchange rate system using updated real rates information for all currencies around the world.
The service uses the EURO currency as a basis for conversion, so the user, from the system's frontend, must inform an amount and choose the currency in which the amount will be converted.

Way: EUR to Currency chosen

To ensure up-to-date real rates, this service uses an external exchange rate expert API (https://www.exchangerate-api.com/). Furthermore, the service is prepared to be expanded to work with other sources of exchange rates.

The executed operations are updated in the database for simple checking.

## Índice

- [User Requirements](#user-requirements)
- [Assumptions](#assumptions)
- [Service - Exchange Rate](#service---exchange-rate)
- [Service - Find all currencies](#service---find-all-currencies)
- [Service - Get Exchanges](#service---get-exchanges)
- [Built With](#built-with)
- [Source Code](#source-code)
- [Usage](#usage)
    
### User Requirements

1) A service that applies an exchange rate to that value (the candidate can also be flexible,
hardcoded exchange rate, external public API).

2) A User Interface that allows the user to insert a value and currency and calculate the exchange
rate.
   
### Assumptions

- Item 1 must be implemented in Java.
- Item 2 must be implemented in Angular.
- Not focus on data storage. 
- Quality Assurance: Must implement unit test

## Service - Exchange Rate

### Objective:

Calculate the entered amount converted to an entered currency. Based on the EURO

Example:

    Scenario 1: The chosen currency is USD. At the time of the transaction, the rate was 1.1850 
        and the amount value was €100.00. The result will be $ 84.38.

#### *API:*  (In localhost, use: Port:8081)

        POST:  /currencies

        Content Type: application/json
        body:   
                {
                    "originCurrency": "EUR",
                    "targetCurrency": "BRL",
                    "amount": 100
                }

        response:
                {
                    "id": 1,
                    "originCurrency": "EUR",
                    "targetCurrency": "BRL",
                    "rate": 6.0712,
                    "amount": 100,
                    "value": 607.1200,
                    "dataTimeRate": "2021-07-15T17:15:43.013"
                }  

### *Sequence Diagram of Exchange Calculate*
![alt text](https://github.com/FabricioEntringerMoreira/exchange-rate/blob/main/docs/img/exchange-calculate-sequence-diagram.png)

## Service - Find all currencies

### Objective:
The objective of this service is to provide updated information about currencies and exchange rate to be used by the application frontend

This service won't process any transaction. It was implemented just to support the front.

#### *API:*

        GET:  /currencies

        Content Type: application/json
        
        response:
                [
                    {
                        "code": "AED",
                        "rate": 4.3514
                    },
                    {
                        "code": "AFN",
                        "rate": 94.7994
                    },
                    {
                        "code": "EUR",
                        "rate": 1
                    },
                    {
                        "code": "USD",
                        "rate": 1.1850
                    },
                    ...
                ]

## Service - Get Exchanges

### Objective:
The objective of this service is to provide all exchanges operations executed in the system.

This service won't process any transaction. It was implemented just to support the front.

#### *API:*

        GET:  /exchanges

        Content Type: application/json
        
        response:
                [
                    {
                        "id": 1,
                        "originCurrency": "EUR",
                        "targetCurrency": "BRL",
                        "rate": 6.07,
                        "amount": 100.00,
                        "value": 607.12,
                        "dataTimeRate": "2021-07-15T17:15:43.013"
                    }
                ]



## Built With

To implement the solution were used the below frameworks and tools were:

| Technology / Framework / Tool | Version | Motivation |
| --- | --- | --- |
| Java | 11 | A base requirement |
| Spring Boot | 2.5.2 | Spring boot was used to help us autoconfigure the application, we can use the built-in tomcat. |
| Spring WEB MVC | 2.5.2 | To user by easily way the MCV architecture built-in Spring Boot.  |
| Spring Cache and Caffeine Framework | - | To work with configurable cache and avoiding the excessive use of external resource.|
| Lombook Framework | 1.18 | It was used to help us with data classes implementing some boilerplate code such as getters and setters methods and Data Builder Pattern. |
| Spring Data JPA | 2.5.2 | Helping in ORM mapping and database  |
| H2 Database | --- | To memory database |
| JUnit + Mockito  | 4.12 | To work with unit tests and scenario simulation |


### Source Code

1. Backend
   ```sh
    git clone https://github.com/FabricioEntringerMoreira/exchange-rate.git
   ```
2. Frontend
   ```sh
    git clone https://github.com/FabricioEntringerMoreira/exchange-rate-front.git
   ```


## Usage

To test the consistency of the application's deployment and its external use, I made the application available in a free service for testing purposes.
