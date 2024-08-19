# microserviciosCumpleanos

Microservicios para la gestión de Cumpleaños, diseñados con arquitectura modular y escalable.  
Este proyecto integra procesos de reposición, ventas y control de stock en tiempo real.

---

## 🚀 Requisitos
- Java 17+
- Maven 3.8+
- MongoDB en ejecución
- Configuración de variables en `.env`
- Docker y Docker Compose instalados

---

## ⚙️ Instalación
### Opción 1: Local con Maven
```bash
git clone https://github.com/LChumi/microserviciosCumpleanos.git
cd microserviciosCumpleanos
mvn spring-boot:run
```
### Opción 2: Docker Compose
```bash
git clone https://github.com/LChumi/microserviciosCumpleanos.git
cd microserviciosCumpleanos
docker-compose up -d
```
Las variables de entorno se configuran en el archivo .env.


## 📂 Estructura
- **Spring Administration** → administración general del sistema
- **Spring Config** → configuración centralizada
- **Spring Eureka** → servicio de descubrimiento
- **Spring Gateway** → API Gateway para enrutar peticiones
- **Microservicios (Spring Boot)** → lógica de negocio (ventas, reposiciones, confitería, etc.)


📜 Licencia
Este proyecto está bajo la licencia Apache 2.0.

