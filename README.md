# Playwright Test Project

Welcome to the Playwright Test Project!

## Required setup

- bash
- nodejs (v20 or v22)
- maven
- IntelliJ (lombok plugin is important)

## Getting Started

Before running the tests, ensure that your backend is set up and running. To start the backend, navigate to the backend project directory and run:

```bash
git clone https://github.com/mfaisalkhatri/restful-ecommerce.git
cd restful-ecommerce
npm run start
```

## Environment Variables
The project uses dotenv to manage environment variables. This allows you to define variables in a .env file in the root of your project. These variables can then be accessed in your code, ensuring that sensitive information like API keys or database URLs are not hardcoded.

To utilize dotenv, create a .env file and define your variables in the format:

` VARIABLE_NAME=value `

Some basic variables are already added to the .env file.

## Running API Tests

To execute the API tests, follow these steps:

- Open the project in IntelliJ
- Go to bash terminal
- Run the following command:

```bash
mvn clean test
```
