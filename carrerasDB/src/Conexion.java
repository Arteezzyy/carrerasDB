import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:postgresql://localhost:5432/carreras_db";
    private static final String USER = "usuario_carreras"; // Cambia por tu usuario real
    private static final String PASSWORD = "12345";        // Cambia por tu contraseña real

    // Método para obtener conexión
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a PostgreSQL!");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión a la base de datos:");
            e.printStackTrace();
            return null;
        }
    }

    // Método principal solo para probar la conexión
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("La conexión está lista para usarse ✅");
            } else {
                System.out.println("No se pudo establecer la conexión ❌");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

