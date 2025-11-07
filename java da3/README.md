# Student Admission Portal (JSF + Hibernate)

This project demonstrates a simple Student Admission Portal built with JSF for the front-end and Hibernate (JPA) for persistence. It includes:

- Welcome → Registration → Confirmation → Dashboard JSF pages.
- Managed beans to pass data between pages.
- JSF validators (email, phone, age) implemented as custom validators.
- Hibernate/JPA persistence using an embedded H2 database.
- CRUD operations (Create, Read, Update, Delete) through StudentDAO.

Prerequisites
- Java 8 or newer
- Maven
- (Optional) Servlet container such as Tomcat, or just use the included Jetty plugin

Run locally using Jetty (fast):

```powershell
mvn clean package jetty:run
```

Open http://localhost:8080/ in your browser.

To deploy to Tomcat, build the WAR and drop it into Tomcat's webapps:

```powershell
mvn clean package
```

Then deploy `target/student-admission-portal.war` to your servlet container.

Notes
- H2 database file will be created in your user home as `~/studentdb`.
- This sample is intentionally minimal to demonstrate navigation, validators, event-driven actions, and CRUD with Hibernate.
