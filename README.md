# Media Microservice

This media microservice is responsible for managing and storing media files with support for multiple storage providers.

It provides **media upload and management**, **multi-provider storage support**, **metadata handling**, and **multi-tenant support**.

> This service focuses on providing a unified interface for media storage across different cloud providers while maintaining flexibility and scalability.


---

## How to generate a new micro service from this boilerplate ?

Follow the instructions from the following guide :

./doc/etapes_pour_vibecoder_un_micro_service.txt

The content is written in French, don't hesitate to translate if it is more convenient for you.

---

## ✅ Features

- 📁 **Complete media file management**
  - Upload, read, download, and delete operations
  - File metadata tracking (name, extension, MIME type, size)
- 🌐 **Multi-provider storage support**
  - Native file system storage
  - SharePoint integration
  - OneDrive support
  - Google Drive compatibility
  - Azure Media Services
- 🔐 **Provider authentication management**
  - Multiple authentication strategies (OAuth2, API keys, service accounts)
  - Token lifecycle management (access tokens, refresh tokens)
  - Provider state tracking (not_configured, ready, expired)
- 📊 **Metadata management system**
  - Custom metadata per media and provider
  - Flexible metadata indexing
  - Metadata-based search capabilities
- 🔍 **Advanced search and filtering**
  - Flexible search operators (EQUAL, LIKE, OR)
  - Date range filtering
  - Pagination and sorting
- 🏢 **Multi-tenant support** via entityUuid
- 📊 **Complete REST API** with pagination and advanced filtering

All business rules follow **Clean Architecture principles** to keep the core logic independent of frameworks and infrastructure concerns.

<img src="./doc/project-architecture.drawio.png" />

---

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3.4.1** (latest stable version)
- **Maven** as the build tool
- **PostgreSQL** for database
- **REST API** using Spring Web
- **Lombok** for reducing boilerplate code
- **Liquibase** for database migrations
- **OpenAPI Generator** for generating Presenters
- **Clean Architecture** software architecture

---

## 🏗️ Architecture

The project follows Clean Architecture principles with 4 Maven modules:

- **domain**: Business entities and repository interfaces (no external dependencies)
- **usecase**: Application logic (depends only on domain)
- **infrastructure**: Technical implementations (JPA, Spring Data, gateways)
- **ui**: REST API and contracts (OpenAPI, controllers)

---

## 🎯 How It Works

### Media Management

1. **File Upload**: Upload media files via multipart/form-data with automatic metadata extraction
   - Automatic file extension detection
   - MIME type validation
   - File size tracking

2. **File Access**: Multiple access patterns supported
   - Read operation: Stream file content for viewing
   - Download operation: Download file with proper headers

3. **Metadata Management**: Flexible metadata system
   - Add custom key-value metadata to media
   - Update or delete metadata independently
   - Search media by metadata values

### Provider System

The service supports multiple storage providers with different authentication mechanisms:

1. **Native Provider**: Local file system storage
   - No authentication required
   - Direct file access

2. **SharePoint/OneDrive**: Microsoft cloud storage
   - OAuth2 authentication flow
   - Support for application and delegated permissions
   - Automatic token refresh

3. **Google Drive**: Google cloud storage
   - API key authentication
   - Service account support
   - User impersonation capabilities

4. **Azure Media**: Azure Media Services integration
   - Enterprise-grade media processing

### Provider States

- `not_configured`: Provider created but not fully configured
- `ready`: Provider configured and ready to use
- `expired`: Provider credentials expired and need renewal

---

## ▶️ How to Run

To launch the application:

```bash
# Complete build
./mvnw clean install

# Run the application
./mvnw -pl infrastructure spring-boot:run -Dspring-boot.run.profiles=h2
```

## How to debug

```bash
# Complete build
./mvnw clean install

# Run the application in debug mode
./mvnw -pl infrastructure spring-boot:run -Dspring-boot.run.profiles=h2 -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005"

# Add the breaking point in the app

# Click on left bar on "Run and Debug"

# Run the action "Attach Spring Boot (5005)"

```

---

## ▶️ How to Generate SQL Migration After Updating/Adding Entity

To generate migration files:

```bash
./mvnw -pl infrastructure liquibase:generateChangeLog \
  -Dliquibase.url=jdbc:h2:mem:testdb \
  -Dliquibase.outputFormat=yaml \
  -Dliquibase.outputChangeLogFile=src/main/resources/db/changelog/01-generated-schema.yaml
```

---

## ⚙️ Configuration

Required environment variables are defined in the `.env` file.

---

## 📡 API Endpoints

The service exposes two main resource endpoints:

- `/medias`: Manage media files
- `/providers`: Manage storage providers

Each endpoint supports:
- Full CRUD operations
- Advanced filtering with operators (EQUAL, LIKE, OR)
- Pagination and sorting
- Count operations
