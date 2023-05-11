import java.sql.*;

public class AcidDemo {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try {
            // Connect to the PostgreSQL database
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CS623",
                    "postgres", "password");

                    
            // Set auto-commit to false to start a transaction
            conn.setAutoCommit(false);

            // Create a statement for executing SQL queries
            stmt = conn.createStatement();

            try{
                // Transaction 2: delete depot d1 from Depot and Stock
                stmt.executeUpdate("DELETE FROM Stock WHERE depid = 'd1';");
                System.out.println("Transaction 2 completed successfully.");
                conn.commit();
            }catch (SQLException e) {
                // Rollback in case of errors
                System.out.println("Error occurred during Transaction 2. Rolling back changes.");
                System.out.println(e.getMessage());
                conn.rollback();
            }
            
            try{
                // Transaction 4: change depot d1 name to dd1 in Depot and Stock
                stmt.executeUpdate("UPDATE Depot SET depid = 'dd1' WHERE depid = 'd1';");
                System.out.println("Transaction 4 completed successfully.");
                conn.commit();
                
            }catch (SQLException e) {
                // Rollback in case of errors
                System.out.println("Error occurred during Transaction 4. Rolling back changes.");
                System.out.println(e.getMessage());
                conn.rollback();
            }

            try{
                // Transaction 6: add depot (d100, Chicago, 100) to Depot and (p1, d100, 100) to Stock
                stmt.executeUpdate("INSERT INTO Depot (depid, addr, volume) VALUES ('d100', 'Chicago', 100);");
                System.out.println("(d100, Chicago, Volume  ) Added to Depot");
                stmt.executeUpdate("INSERT INTO Stock (prodid, depid, quantity) VALUES ('p1', 'd100', 100);");
                System.out.println("('p1', 'd100', 100) Added to Stock");
                System.out.println("Transaction 6 completed successfully.");
                conn.commit();
            }catch (SQLException e) {
                // Rollback in case of errors
                System.out.println("Error occurred during Transaction 6. Rolling back changes.");
                System.out.println(e.getMessage());
                conn.rollback();
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
