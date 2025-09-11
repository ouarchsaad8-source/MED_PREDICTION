# MedPrediction Backend API

Spring Boot backend for the MedPrediction medical diagnosis application.

## Prerequisites

- **Java 17+** - Download from [Adoptium](https://adoptium.net/)
- **Maven 3.6+** - Download from [Apache Maven](https://maven.apache.org/download.cgi)

## Quick Start

### Option 1: Using the Batch Script (Windows)
```bash
cd backend
start-backend.bat
```

### Option 2: Manual Commands
```bash
cd backend
mvn clean compile
mvn spring-boot:run
```

## API Endpoints

The backend runs on `http://localhost:8080` with base path `/api`

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `POST /api/auth/logout` - User logout

### User Management
- `GET /api/users/profile` - Get current user profile
- `PUT /api/users/profile` - Update user profile

### Predictions
- `POST /api/predictions` - Create new prediction
- `GET /api/predictions` - Get user predictions
- `GET /api/predictions/recent?limit=5` - Get recent predictions
- `GET /api/predictions/{id}` - Get prediction by ID

### Symptoms
- `GET /api/symptoms/public/all` - Get all active symptoms
- `GET /api/symptoms/public/categories` - Get symptom categories
- `GET /api/symptoms/public/category/{category}` - Get symptoms by category
- `GET /api/symptoms/public/search?keyword=` - Search symptoms

### Dashboard
- `GET /api/dashboard/stats` - Get dashboard statistics

## Documentation

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs

## Database

- **H2 Console**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:medprediction`
- **Username**: `sa`
- **Password**: (empty)

## Sample Users

The application initializes with sample users:

1. **Admin User**
   - Email: `admin@medprediction.com`
   - Password: `admin123`

2. **Demo User**
   - Email: `john.doe@example.com`
   - Password: `password123`

3. **Demo User 2**
   - Email: `jane.smith@example.com`
   - Password: `password123`

## Features

- JWT-based authentication
- Role-based access control
- AI-simulated medical predictions
- Comprehensive symptom database
- RESTful API design
- Input validation and error handling
- CORS configuration for frontend integration
- Swagger API documentation
- H2 in-memory database with sample data

## Security

- Passwords are encrypted using BCrypt
- JWT tokens for stateless authentication
- CORS configured for localhost:4200 (Angular frontend)
- Input validation on all endpoints

## Architecture

```
src/main/java/com/medprediction/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── dto/            # Data Transfer Objects
├── entity/         # JPA entities
├── exception/      # Exception handling
├── repository/     # Data access layer
├── security/       # Security configuration
└── service/        # Business logic
```

## Development

The backend is configured for development with:
- Hot reload enabled
- Detailed logging
- H2 console for database inspection
- Swagger for API testing

## Production Notes

For production deployment:
1. Replace H2 with PostgreSQL
2. Update `application.yml` with production database settings
3. Configure proper JWT secret
4. Set up proper CORS origins
5. Enable HTTPS
6. Configure proper logging levels
