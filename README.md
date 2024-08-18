# ProyectoKanban - Tablero Kanban para la gestión de proyectos
## Descripcion del Proyecto API REST Backend
Este proyecto es una API REST desarrollada en Java con el framework Spring Boot. Utiliza Maven para la gestión de dependencias, Hibernate como ORM, JDK 17 como versión del lenguaje y una base de datos remota MySQL.
## Características de funcionalidad
- Registro e inicio de Sesión de los Usuarios
- Listado de proyectos en los que participa el usuario
- Creación de nuevos proyectos con la posibilidad de agregar constribuyentes
- Creación de Tareas, a las cuales se les asignará el usuario responsable a ejecutarla
- Cambio de estado de la tarea según el proceso en el que se encuentre (Por hacer, en proceso, terminado, en revisión, aprobado)
## Pasos para levantar la API localmente:
- Tener instalado Java Development Kit (JDK) 17 y Maven
- Clonar el repositorio: git clone https://github.com/juanmanuelpico/proyecto-kanban-back.git
- Cambiar a la rama main: git switch main / git checkout main
- Compilar el proyecto: mvn clean install
- **La API estará disponible en: http://localhost:8080
## Contribución
Si quiere contribuir en el desarrollo, enviar mail a la dirección: picojuan322@gmail.com con el asunto: "Contribución Proyecto Kanban"
