# Sprint_7
Проект по автотестированию API 

## **Описание**

Версия Java 11.

Проект использует следующие библиотеки:

* JUnit 4

* RestAssured

* Allure

## Документация

[Ссылка](https://qa-scooter.praktikum-services.ru/docs/) на документацию учебного сервиса Яндекс.Самокат. [Ссылка](http://qa-scooter.praktikum-services.ru/) на учебное приложение Яндекс.Самокат.


## Запуск автотестов

Для запуска автотестов необходимо:

1. Скачать код

#### git clone https://github.com/Misteriya89/Sprint_7.git

2. Ввести команду в проекте

#### mvn clean test

3. Ввести команду для построения отчета  в Allure

#### mvn allure:report

## Структура проекта

.gitignore

pom.xml

README.md

```
src
-- main
|   |-- java
|   |   |-- org.example
|   |   |   |-- courier
|   |   |   |   |-- Courier.java
|   |   |   |   |-- CourierClient.java
|   |   |   |-- order
|   |   |   |   |-- AvailableStation.java
|   |   |   |   |-- ListOfOrders.java
|   |   |   |   |-- Order.java
|   |   |   |   |-- PageInfo.java
|   |   |   |-- util
|   |   |   |   |-- Colors.java
|   |-- resources
|-- test
|   |-- java
|   |   |-- courier
|   |   |   |-- CourierLoginTest.java
|   |   |   |-- CreateCourierTest.java
|   |   |-- order
|   |   |   |-- CreateOrderTest.java
|   |   |   |-- GetListOfOrdersTest.java
```
