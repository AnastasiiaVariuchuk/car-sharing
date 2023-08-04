# <h1 align="center"> <img src="https://em-content.zobj.net/thumbs/120/apple/354/racing-car_1f3ce-fe0f.png" width="35"/> Car Sharing Service <img src="https://em-content.zobj.net/thumbs/120/apple/354/racing-car_1f3ce-fe0f.png" width="35"/> </h1>
#### <h4 align="center"> `Faster than you've ever seen` </h4>

### <img src="https://em-content.zobj.net/thumbs/160/apple/354/raising-hands_1f64c.png" width="25"/> Introduction
`The Car Sharing Service aims to provide an efficient and user-friendly platform for renting cars. It caters to both customers and administrators, allowing customers to browse available cars, make reservations, and process payments securely, while administrators can manage car inventory, user roles, and track payments`

### <img src="https://em-content.zobj.net/thumbs/160/apple/354/laptop_1f4bb.png" width="25"/> Technologies Used
`The following technologies are used to build the Car Sharing Service:`
- <img src="https://image.emojipng.com/677/13219677.jpg" width="30"/> **Java**: The primary programming language used for the application.
- <img src="https://media.trustradius.com/product-logos/9B/8G/IMJEF6VWC74S.PNG" width="30"/> **Spring Boot**: A powerful framework that provides essential features for building web applications.
- <img src="https://media.trustradius.com/product-logos/9B/8G/IMJEF6VWC74S.PNG" width="30"/> **Spring Data JPA**: Simplifies data access and persistence with JPA (Java Persistence API).
- <img src="https://media.trustradius.com/product-logos/9B/8G/IMJEF6VWC74S.PNG" width="30"/> **Spring Security**: Enables robust and secure authentication and authorization mechanisms.
- <img src="https://t1.gstatic.com/images?q=tbn:ANd9GcSPbQehl7jW6cT9MZXqOeA4FNWqVNkOThwpkukkugx0lD1EhpIH" width="30"/> **Swagger**: Provides API documentation.
- <img src="https://w7.pngwing.com/pngs/464/18/png-transparent-mysql-database-innodb-postgresql-column-marine-mammal-electric-blue-postgresql-thumbnail.png" width="30"/> **MySQL**: The database management system used for data storage.
- <img src="https://t2.gstatic.com/images?q=tbn:ANd9GcQY7YDz2atjZ4SUnF-rkVAAMh0Rq5gOA4v9I4D2WRZHccPlacGo" width="30"/> **Telegram API**: Used to send notifications to administrators via Telegram.
- <img src="https://cdn.freebiesupply.com/logos/large/2x/docker-logo-png-transparent.png" width="35"/>  **Docker**: Used for containerization of the application and database.
- <img src="https://cdn.dribbble.com/users/920/screenshots/1675816/stripe.png?resize=400x300&vertical=center" width="35"/> **Stripe API**: Integrated with Stripe for secure payment processing.
- <img src="https://velog.velcdn.com/images/gloom/post/17bae182-7380-43e0-a45e-fff76b8ba9c7/image.png" width="35"/> **Lombok**: Reduces boilerplate code with annotations.
- <img src="https://trguduru.github.io/img/mapstruct.png" width="35"/> **MapStruct**: Simplifies object mapping between DTOs and entities.

### <img src="https://em-content.zobj.net/thumbs/160/apple/354/rocket_1f680.png" width="25"/> Getting Started
`Before running the Car Sharing Service, ensure you have the following installed:`
- <img src="https://image.emojipng.com/677/13219677.jpg" width="30"/> Java Development Kit (JDK)
- <img src="https://cdn.freebiesupply.com/logos/large/2x/docker-logo-png-transparent.png" width="35"/> Docker and Docker Compose

`Follow the steps below to install:`
1. Clone the repository from GitHub and navigate to the project directory.
2. Create a `.env` file with the necessary environment variables. (See `.env.sample` for a sample.)
3. Run the following command to build and start the Docker containers:
   `docker-compose up --build`.
4. The application should now be running at `http://localhost:8080`.

### <img src="https://em-content.zobj.net/thumbs/160/apple/354/building-construction_1f3d7-fe0f.png" width="25"/> Project architecture:
![architecture](assets/architecture.png)

### <img src="https://em-content.zobj.net/source/apple/354/floppy-disk_1f4be.png" width="25"/> Database structure:
#### <h4 align="center"> ![car-sharing](assets/car-sharing.png) </h4>

### <img src="https://em-content.zobj.net/thumbs/160/apple/354/link_1f517.png" width="25"/> API Endpoints
`The Car Sharing Service provides the following API endpoints:`

<!-- Fill in the details for each endpoint as per your project's requirements -->

| **HTTP method** | **Endpoint**                   | **Role**   | **Function**                                     |
|:----------------|:-------------------------------|------------|:-------------------------------------------------|
| POST            | /register                      | ALL        | Register a new user.                            |
| POST            | /login                         | ALL        | Get JWT tokens for authentication.              |
| PUT             | /users/{id}/role               | ADMIN      | Update user role.                               |
| GET             | /users/me                      | ALL        | Get current user's profile info.                |
| PUT             | /users/me                      | ALL        | Update current user's profile info.             |
| POST            | /cars                          | ADMIN      | Add a new car.                                  |
| GET             | /cars                          | ALL        | Get a list of all cars.                         |
| GET             | /cars/{id}                     | ALL        | Get detailed information about a car.           |
| PATCH           | /cars/{id}                     | ADMIN      | Update a car's information.                     |
| DELETE          | /cars/{id}                     | ADMIN      | Delete a car.                                   |
| POST            | /rentals                       | ADMIN/USER        | Add a new rental.                        |
| GET             | /rentals                       | ADMIN/USER | Get rentals by user ID and rental status.       |
| GET             | /rentals/{id}                  | ADMIN/USER | Get specific rental by ID.                      |
| POST            | /rentals/{id}/return           | ADMIN/USER | Set actual return date for a rental.            |
| POST            | /payments                      | ADMIN/USER | Create a payment session for a rental.          |
| GET             | /payments                      | ADMIN/USER | Get payments by user ID.                        |
| GET             | /payments/my                   | USER       | Get payments for current user.                        |
| GET             | /payments/success              | ALL        | Endpoint for handling successful Stripe payments. |
| GET             | /payments/cancel               | ALL        | Endpoint for handling canceled Stripe payments.  |

### <img src="https://em-content.zobj.net/thumbs/160/apple/354/mechanical-arm_1f9be.png" width="25"/> Telegram Notifications
`The Car Sharing Service sends Telegram notifications for the following events:`
- New rental created: Notifications are sent to all service administrators.
- Overdue rental: If a rental is overdue (return date is in the past, and the car is still not returned), notifications are sent to all service administrators.

### <img src="https://em-content.zobj.net/thumbs/120/apple/354/card-file-box_1f5c3-fe0f.png" width="25"/> Contribution Guidelines
`We welcome contributions to this project:`

Please create a new branch for each feature or bug fix and submit a pull request to the `main` branch. All PRs must be reviewed and approved by at least two team members before merging.
