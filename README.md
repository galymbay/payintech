# Test Assignment Java
Test task from PayInTech

##Launch Application
    git clone https://github.com/galymbay/payintech.git
    docker pull postgres
    docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=postgres postgres
    docker build -t payintech .
    docker run -d -p 8080:8080 payintech


#API
***There is also a swagger.yaml inside the project***
###Registration

####URL: [localhost:8080/registration, POST]

Request example:

    {
        "firstName": "string",
        "lastName": "string",
        "patronymic": "string",
        "iin": "string",
        "phoneNumber": "string",
        "password": "string",
        "address": "string"
    }
Response:

    Token

###Login

####URL: [localhost:8080/login, POST]

Request example:

    {
        "phoneNumber":"string",
        "password":"string"
    }
Response:

    Token

###Loan

####URL: [localhost:8080/loan, POST]

Header:

    Authorization: Bearer token

Request example:

    {
        "loanSum": int,
        "period": int,
        "interestRate": int
    }
Response:

    {
        "loanSum": int,
        "period": int,
        "interestRate": int
    }

####URL: [localhost:8080/loan/{client_id}, GET]

Header:

    Authorization: Bearer token (Only ADMIN)

Request example:

    {
        "loanSum": int,
        "period": int,
        "interestRate": int
    }
Response:

    [
        {
            "remains": 249984.0,
            "remainingPaymentsCount": 24,
            "nextPaymentAmount": 10416.0,
            "nextPaymentDate": "2022-10-13",
            "earlyPayment": 120000
        }
    ]

##Database initial queries

    INSERT INTO role (role_name) VALUES ('ROLE_CLIENT');
    INSERT INTO role (role_name) VALUES ('ROLE_ADMIN');
    
    INSERT INTO client (client_firstname, client_lastname, client_patronymic, client_iin, client_phone, client_password, client_address)
    VALUES ('payintech', 'payintech', 'payintech', '0', '0', '$2a$12$Ee36wFxREpLnQnMIqL2NS.ujlUcsJJwBWm0Z3JFF2uHw4jQXbJCKW', '0');
    
    INSERT INTO role_user(client_id, role_id) VALUES (1,2);