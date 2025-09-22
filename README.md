# 🎓 CarrerasApp - Java + PostgreSQL
Este proyecto es una aplicación en **Java** que se conecta a una base de datos **PostgreSQL** para gestionar carreras universitarias. Fue desarrollado en **IntelliJ IDEA** y utiliza **JDBC** para la conexión.

## 📦 Requisitos
Antes de ejecutar el proyecto, asegúrate de tener instalado:
- [Java JDK 17+](https://adoptium.net/)
- [PostgreSQL 14+](https://www.postgresql.org/download/)
- [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/)
- [Driver JDBC de PostgreSQL](https://jdbc.postgresql.org/download/)

## 🗄️ Configuración de la base de datos en PostgreSQL
Abrir **pgAdmin4** o la consola `psql` y ejecutar:
```sql
CREATE DATABASE carreras_db;
CREATE USER usuario_carreras WITH PASSWORD '12345';
GRANT ALL PRIVILEGES ON DATABASE carreras_db TO usuario_carreras;

CREATE TABLE carreras (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    facultad VARCHAR(100),
    duracion INT
);

INSERT INTO carreras (nombre, facultad, duracion) VALUES
('Ingeniería de Sistemas', 'Tecnología', 5),
('Medicina', 'Medicina', 6),
('Derecho', 'Ciencias Jurídicas', 5);

SELECT * FROM carreras;


## ⚙️ Configuración del proyecto en IntelliJ IDEA
Clonar el repositorio:
```sql
Abrir el proyecto en IntelliJ IDEA con File → Open....
Descargar postgresql-42.x.x.jar desde la página oficial
.
Crear una carpeta lib/ en el proyecto y copiar el .jar.
En IntelliJ: clic derecho sobre el .jar → Add as Library.

La estructura del proyecto quedará así:
CarrerasApp/
├── lib/
│   └── postgresql-42.x.x.jar
├── src/
│   ├── Conexion.java
│   └── CarrerasApp.java
└── README.md

## 📜 Código principal
```sql
Clase de conexión Conexion.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/carreras_db";
    private static final String USER = "usuario_carreras";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a PostgreSQL!");
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión:");
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        getConnection();
    }
}

## Clase principal CarrerasApp.java
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CarrerasApp {
    public static void main(String[] args) {
        try (Connection conn = Conexion.getConnection()) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM carreras");

                System.out.println("📚 Lista de Carreras:");
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("id") + " - " +
                        rs.getString("nombre") + " - " +
                        rs.getString("facultad") + " - " +
                        rs.getInt("duracion") + " años"
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

## ▶️ Ejecución
```sql

Ejecutar la clase Conexion.java para probar la conexión.
Ejecutar la clase CarrerasApp.java para listar las carreras.

##Salida esperada en consola:
```sql
✅ Conexión exitosa a PostgreSQL!
📚 Lista de Carreras:
1 - Ingeniería de Sistemas - Tecnología - 5 años
2 - Medicina - Medicina - 6 años
3 - Derecho - Ciencias Jurídicas - 5 años
