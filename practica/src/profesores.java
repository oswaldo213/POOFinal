import java.sql.*;
import javax.swing.*;

public class profesores extends ABC_Base {

    public profesores(JFrame anterior) {
        super(anterior);
        tabla = "Professor";
    }

    public profesores() {
        super();
        tabla = "Professor";
    }


    public String MakeQuery(int valor) {
        switch (valor) {
            case 1:
                return "SELECT * FROM " + tabla + " WHERE WorkerNumber = '" + txt3Field.getText()  + "'";

            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (FirstName, LastName, WorkerNumber) VALUES ('" +
                    txt1Field.getText() +
                    "','" +
                    txt2Field.getText() +
                    "','" +
                    txt3Field.getText() +
                    "')"
                );
            case 3:
                return (
                    "UPDATE " +
                    tabla +
                    " SET FirstName = '" +
                    txt1Field.getText() +
                    "', LastName = '" +
                    txt2Field.getText() +
                    "', WorkerNumber = '" +
                    txt3Field.getText() +
                    "' WHERE WorkerNumber = '" +
                    txt3Field.getText() +
                    "'"
                );
            case 4:
                return (
                    "DELETE FROM " +
                    tabla +
                    " WHERE WorkerNumber = '" +
                    txt3Field.getText() +
                    "'"
                );
            default:
                return "";
        }
    }

    @Override
    public void Consultar() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(MakeQuery(1));
            if (rs.next()) {
                txt1Field.setText(rs.getString("FirstName"));
                txt2Field.setText(rs.getString("LastName"));
            } else {
                JOptionPane.showMessageDialog(null, "Profesor no encontrado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                null,
                "Error al consultar: " + ex.getMessage()
            );
        }
    }
}
