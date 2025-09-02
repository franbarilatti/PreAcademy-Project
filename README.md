# EventHub 🎉

EventHub es una plataforma para la **gestión de eventos** desarrollada bajo una arquitectura de **microservicios**.  
El sistema permite organizar, gestionar y asistir a eventos de manera sencilla, con diferentes roles de usuario.

---

## 🚀 Características principales
- Creación y gestión de eventos.
- Registro de usuarios como organizadores o participantes.
- Administración de inscripciones y asistencia.
- Gestión de salas y horarios.
- Estadísticas básicas de asistencia y eventos creados.

---

## 🏗️ Arquitectura propuesta
EventHub está dividido en **microservicios** para facilitar el desarrollo colaborativo:

- **Auth Service** → Registro y login de usuarios.
- **User Service** → Gestión de perfiles y roles.
- **Event Service** → CRUD de eventos y salas.
- **Booking Service** → Inscripciones y reservas de lugares.
- **Notification Service** → Envío de mails/notificaciones.
- **Admin Service** → Estadísticas y reportes.

Cada microservicio tendrá su propio **backend (Spring Boot)** y se comunicará vía **REST API**.  
El frontend se desarrollará en **React**.

---
# PreAcademy-Project
Plataforma para la gestión de eventos con roles de organizador, participante y administrador.
