package br.edu.unirg.projeto.conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConexaoDB {
    private Connection con;
    public Connection connect() {
        if (con == null) {
            try {
                String strJDBC = "jdbc:mysql://localhost:3306/AGENDA?"
                        + "zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                con = DriverManager.getConnection(strJDBC, "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
}
