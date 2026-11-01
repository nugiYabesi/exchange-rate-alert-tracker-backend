# Exchange Rate Alert Tracker Backend

- **Student:** IYUKURI NUGI YABESI
- **Student ID:** 27327
- **Project:** Exchange Rate Alert Tracker
- **Course:** INSY 8322 Web Technology and Internet (AUCA), Mid-Term Examination 

A Spring Boot, Spring Data JPA and PostgreSQL backend. A subscriber creates a rate alert that is followed by an analyst at a trading desk. An alert moves from ACTIVE to TRIGGERED and ends NOTIFIED or DEACTIVATED, and the analyst then logs the alert notifications sent to the subscriber.

## Steps to run the API

1. **Requirements:** Java 17, Maven, PostgreSQL and Postman.

2. **Create the database** (in pgAdmin or psql):

   ```sql
   CREATE DATABASE exchange_db;
   ```

3. **Set your database password** in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/exchange_db
   spring.datasource.username=postgres
   spring.datasource.password=YOUR_POSTGRES_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. **Start the API** from the project root:

   ```bash
   mvn spring-boot:run
   ```

   Wait for `Started ExchangeRateAlertTrackerApplication`. The API runs on `http://localhost:8080` and Hibernate creates the tables automatically.

5. **Test in Postman:** import `exchange-rate-alert-tracker-midterm.postman_collection.json`. It has one request per requirement, named by its ID (A2, B1, C4, D2, ...). The ids used by the requests are collection variables, so edit them to match your own data (Collection, Variables).

## Entities

Currency, Desk, Analyst, Subscriber, RateAlert (with the enum AlertStatus) and AlertNotification.

## Endpoints

| Part | Endpoints |
|---|---|
| A: CRUD | `POST /api/{path}/save`, `GET /api/{path}/all`, `GET /api/{path}/{id}`, `PUT /api/{path}/update/{id}`, `DELETE /api/{path}/delete/{id}` for currencies, desks, analysts, subscribers, rate-alerts |
| B: derived queries | `GET /api/subscribers/by-country`, `GET /api/rate-alerts/by-status`, `GET /api/rate-alerts/between` |
| C: JPQL | `GET /api/analysts/by-currency`, `GET /api/desks/free`, `GET /api/currencies/unused`, `GET /api/subscribers/of-analyst/{analystId}` |
| D: business rules | `POST /api/rate-alerts/save`, `PATCH /api/rate-alerts/{id}/status`, `PUT /api/analysts/{analystId}/currencies/{currencyId}`, `PATCH /api/analysts/{analystId}/desk` |
| E: alert notifications | `POST /api/alert-notifications/save`, `GET /api/alert-notifications/subscriber/{subscriberCode}`, `DELETE /api/alert-notifications/delete/{id}` |
| F: reports | `GET /api/rate-alerts/stats/by-status`, `PATCH /api/rate-alerts/cancel-day` |
| Bonus | `GET /api/dashboard/summary`, `GET /api/rate-alerts/page` |
