# 🐳 Microservices Docker Consul Lab

[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Consul](https://img.shields.io/badge/Consul-8B5CF6?style=for-the-badge&logo=consul&logoColor=white)](https://www.consul.io/)
[![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)

This project demonstrates how to containerize a Spring Boot microservices architecture using Docker Compose, with Consul for service discovery. The architecture includes Client Service, Voiture Service, Gateway Service, MySQL database, and phpMyAdmin for database management.

## 🎯 Learning Objectives

By the end of this lab, you will be able to:

- Understand the role of Docker in microservices architectures
- Write multi-stage Dockerfiles for Spring Boot microservices
- Orchestrate multiple containers using docker-compose.yml
- Implement service discovery with Consul
- Understand the difference between localhost (host machine) and Docker DNS (mysql, consul, etc.)
- Diagnose common issues: ports, networks, dependencies

## 🏗️ Architecture Overview

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Client Service│    │ Gateway Service │    │ Voiture Service │
│     (8088)      │◄──►│     (8888)      │◄──►│     (8089)      │
│                 │    │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │     Consul      │
                    │ Service Registry│
                    │     (8500)      │
                    └─────────────────┘
                                 │
                    ┌─────────────────┐
                    │     MySQL       │
                    │   Database      │
                    │     (3306)      │
                    └─────────────────┘
                                 │
                    ┌─────────────────┐
                    │  phpMyAdmin     │
                    │     (8081)      │
                    └─────────────────┘
```

## 📋 Prerequisites

- **Docker Desktop** (latest version)
- **Docker Compose** V2
- **Java 17** (for local development)
- **Maven 3.6+** (for local development)

Verify installations:
```bash
docker --version
docker compose version
java -version
mvn -version
```

## 🚀 Quick Start

### 1. Clone and Navigate

```bash
cd microservices-docker-consul-lab
```

### 2. Project Structure

```
microservices-docker-consul-lab/
├── docker-compose.yml
├── README.md
├── client-service/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
├── gateway-service/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
├── voiture-service/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
└── docs/
    └── QUICK-START.md
```

### 3. Launch the Architecture

```bash
# Build and start all services
docker compose up -d --build

# Check running services
docker compose ps
```

### 4. Access Services

| Service | URL | Description |
|---------|-----|-------------|
| **Consul UI** | http://localhost:8500 | Service discovery dashboard |
| **Gateway** | http://localhost:8888 | API Gateway (entry point) |
| **Client Service** | http://localhost:8088 | Client microservice |
| **Voiture Service** | http://localhost:8089 | Voiture microservice |
| **phpMyAdmin** | http://localhost:8081 | MySQL database management |

## 🔧 Essential Commands

```bash
# View all logs
docker compose logs -f

# View specific service logs
docker compose logs -f client-service

# Restart a service
docker compose restart client-service

# Stop all services
docker compose stop

# Remove all containers
docker compose down

# Remove containers and volumes
docker compose down -v
```

## ✅ Verification Checklist

### Consul Service Discovery
- [ ] Consul UI accessible at http://localhost:8500
- [ ] "gateway-service" visible in services
- [ ] "client-service" visible in services
- [ ] "voiture-service" visible in services
- [ ] All services show "passing" status (green)

### phpMyAdmin Database
- [ ] phpMyAdmin accessible at http://localhost:8081
- [ ] Login successful (server: mysql, username: root, password: root)
- [ ] Database `Micro_ClientDB` exists
- [ ] Database `Micro_VoitureDB` exists

### Microservices Health
- [ ] Gateway responds at http://localhost:8888
- [ ] Client service responds at http://localhost:8088
- [ ] Voiture service responds at http://localhost:8089

## 🧪 API Testing

### Client Service
```bash
# Get all clients
curl http://localhost:8088/clients

# Home endpoint
curl http://localhost:8088/
```

### Voiture Service
```bash
# Get all voitures
curl http://localhost:8089/voitures

# Home endpoint
curl http://localhost:8089/
```

### Gateway Routing
```bash
# Route to client service
curl http://localhost:8888/client-service/

# Route to voiture service
curl http://localhost:8888/voiture-service/
```

## 🐛 Troubleshooting

### Port Already in Use

**Windows:**
```bash
netstat -ano | findstr :8500
taskkill /PID <PID> /F
```

**Linux/Mac:**
```bash
lsof -i :8500
kill -9 <PID>
```

### Service Won't Start

```bash
# Check logs
docker compose logs client-service

# Rebuild specific service
docker compose build client-service
docker compose up -d client-service
```

### MySQL Connection Issues

**Incorrect configuration (❌):**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/...
```

**Correct configuration (✅):**
```properties
spring.datasource.url=jdbc:mysql://mysql:3306/...
```

### Service Not Registered in Consul

**Verify environment variables:**
```yaml
environment:
  SPRING_CLOUD_CONSUL_HOST: consul
  SPRING_CLOUD_CONSUL_PORT: 8500
  SPRING_CLOUD_CONSUL_DISCOVERY_ENABLED: 'true'
```

## 📊 Startup Order

Docker Compose automatically manages dependencies with `depends_on`:

1. **MySQL** - Starts first (database)
2. **Consul** - Starts first (service registry)
3. **Gateway Service** - Waits for MySQL + Consul
4. **Client Service** - Waits for MySQL + Consul + Gateway
5. **Voiture Service** - Waits for MySQL + Consul + Gateway
6. **phpMyAdmin** - Waits for MySQL

## 🔍 Advanced Debugging

### Enter a Container

```bash
docker exec -it client-container sh
```

### Test Connectivity

```bash
# Inside container
ping mysql
ping consul
```

### View Environment Variables

```bash
docker exec client-container env
```

### Check Java Process

```bash
docker exec client-container ps aux
```

## 🛠️ Local Development

To run services locally (without Docker):

1. **Start MySQL locally** or use Docker for MySQL only
2. **Update application.yml** with local database URL
3. **Run services:**
   ```bash
   cd client-service
   mvn spring-boot:run

   cd ../gateway-service
   mvn spring-boot:run

   cd ../voiture-service
   mvn spring-boot:run
   ```

## 📚 Additional Resources

- [Docker Documentation](https://docs.docker.com/)
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Consul Documentation](https://www.consul.io/docs)
- [Spring Cloud Consul](https://spring.io/projects/spring-cloud-consul)

---

**Happy coding!** 🚀
