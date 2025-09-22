import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CarrerasApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== MENÚ CARRERAS =====");
            System.out.println("1. Insertar carrera");
            System.out.println("2. Mostrar carreras");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    insertarCarrera(sc);
                    break;
                case 2:
                    mostrarCarreras();
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        } while (opcion != 3);

        sc.close();
    }

    // Método para insertar carrera
    private static void insertarCarrera(Scanner sc) {
        System.out.print("Nombre de la carrera: ");
        String nombre = sc.nextLine();

        System.out.print("Facultad: ");
        String facultad = sc.nextLine();

        System.out.println("Dificultad (Alto, Medio, Bajo): ");
        String dificultad = sc.nextLine();

        String sql = "INSERT INTO carreras (nombre, facultad, dificultad) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, facultad);
            ps.setString(3, dificultad);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Carrera insertada correctamente!");
            }

        } catch (Exception e) {
            System.out.println("❌ Error al insertar carrera:");
            e.printStackTrace();
        }
    }

    // Método para mostrar carreras
    private static void mostrarCarreras() {
        String sql = "SELECT * FROM carreras";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n📚 Lista de Carreras:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("nombre") + " - " +
                                rs.getString("facultad") + " - " +
                                rs.getString("dificultad")
                );
            }

        } catch (Exception e) {
            System.out.println("❌ Error al mostrar carreras:");
            e.printStackTrace();
        }
    }
}

