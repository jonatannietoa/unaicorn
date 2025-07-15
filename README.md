# Shipfyze - Cargo Ship Tracking System

Shipfyze is a comprehensive application designed for tracking cargo ships, ship containers, and products inside containers. This system provides real-time visibility into maritime logistics operations, enabling efficient management of cargo throughout the shipping process.

## 🚢 Overview

Shipfyze helps logistics companies, shipping operators, and cargo owners track their assets across the maritime supply chain. The application provides a centralized platform to monitor:

- **Cargo Ships**: Track vessel locations, routes, and operational status
- **Ship Containers**: Monitor container assignments, locations, and capacity
- **Products**: Track individual products within containers, including inventory and condition

## ✨ Features

### Ship Management
- Register and manage cargo ships in the fleet
- Track ship locations and routes
- Monitor ship capacity and operational status
- Maintain ship documentation and certifications

### Container Tracking
- Assign containers to ships
- Track container locations throughout the journey
- Monitor container capacity and utilization
- Manage container types and specifications

### Product Management
- Track individual products within containers
- Monitor product quantities and conditions
- Manage product categories and specifications
- Generate inventory reports

### Reporting & Analytics
- Real-time dashboard with key metrics
- Generate shipping reports and analytics
- Track delivery performance and delays
- Export data for external systems

## 🛠 Technology Stack

- **Backend Framework**: Spring Boot 3.5.3
- **Language**: Java 21
- **Database**: H2 (In-Memory) for development
- **ORM**: Spring Data JPA
- **Web Framework**: Spring Web (REST API)
- **Build Tool**: Gradle
- **Testing**: JUnit 5

## 📋 Prerequisites

Before running the application, ensure you have:

- Java 21 or higher installed
- Gradle (or use the included Gradle wrapper)
- Git for version control

## 🚀 Getting Started

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd shipfyze
   ```

2. **Build the application**
   ```bash
   ./gradlew build
   ```

3. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

The application will start on `http://localhost:8080`

### Database Access

The application uses H2 in-memory database for development. You can access the H2 console at:
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave empty)

## 📊 Database Schema

The application manages three main entities:

### Ships
- Ship ID, Name, Type
- Current Location (Latitude, Longitude)
- Capacity, Status
- Registration details

### Containers
- Container ID, Type, Size
- Assigned Ship
- Current Location
- Capacity and Current Load

### Products
- Product ID, Name, Category
- Assigned Container
- Quantity, Weight
- Condition Status

## 🔌 API Endpoints

### Ships
- `GET /api/ships` - List all ships
- `POST /api/ships` - Create a new ship
- `GET /api/ships/{id}` - Get ship details
- `PUT /api/ships/{id}` - Update ship information
- `DELETE /api/ships/{id}` - Remove ship

### Containers
- `GET /api/containers` - List all containers
- `POST /api/containers` - Create a new container
- `GET /api/containers/{id}` - Get container details
- `PUT /api/containers/{id}` - Update container information
- `DELETE /api/containers/{id}` - Remove container

### Products
- `GET /api/products` - List all products
- `POST /api/products` - Create a new product
- `GET /api/products/{id}` - Get product details
- `PUT /api/products/{id}` - Update product information
- `DELETE /api/products/{id}` - Remove product

## 🧪 Testing

Run the test suite:
```bash
./gradlew test
```

## 📁 Project Structure

```
TODO: Add project structure diagram
```

## 🚀 Deployment

### Production Database

For production deployment, replace H2 with a production database:

1. Add database dependency to `build.gradle.kts`
2. Update `application.properties` with production database settings
3. Configure connection pooling and security settings


## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the documentation in the `/docs` folder

## 🗺 Roadmap

### Upcoming Features
- Real-time GPS tracking integration
- Mobile application for field operations
- Advanced analytics and reporting
- Integration with external shipping APIs
- Multi-tenant support for different organizations

---

**Shipfyze** - Streamlining maritime logistics through intelligent tracking and management.