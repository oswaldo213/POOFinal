import java.sql.*;
import javax.swing.*;

public class materias extends profesores {

    public materias(JFrame anterior) {
        super(anterior);
        tabla = "Signature";
    }

    @Override
    public void Window() {
        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));
        panel_up.add(Box.createVerticalStrut(10));

        lbl1Field.setText("Nombre de materia:");
        lbl3Field.setText("Profesor de la materia:");

        super.Window();

        panel_up.remove(lbl2Field);
        panel_up.remove(txt2Field);

        setTitle("Gestión de Materias");
        panel_up.revalidate();
        panel_up.repaint();
    }

    @Override
    public String MakeQuery(int valor) {
        switch (valor) {
            case 1:
                return (
                    "SELECT s.Name, CONCAT(p.FirstName, ' ', p.LastName) AS NombreProfesor " +
                    "FROM Signature s " +
                    "LEFT JOIN Professor p ON s.WorkerNumber = p.WorkerNumber " +
                    "WHERE s.Name = '" +
                    txt1Field.getText() +
                    "'"
                );
            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (Name, WorkerNumber) VALUES ('" +
                    txt1Field.getText() +
                    "', " +
                    "(SELECT WorkerNumber FROM Professor WHERE CONCAT(FirstName, ' ', LastName) = '" +
                    txt3Field.getText() +
                    "'))"
                );
            case 3:
                return (
                    "UPDATE " +
                    tabla +
                    " SET WorkerNumber = " +
                    "(SELECT WorkerNumber FROM Professor WHERE CONCAT(FirstName, ' ', LastName) = '" +
                    txt3Field.getText() +
                    "') " +
                    "WHERE Name = '" +
                    txt1Field.getText() +
                    "'"
                );
            case 4:
                return (
                    "DELETE FROM " +
                    tabla +
                    " WHERE Name = '" +
                    txt1Field.getText() +
                    "'"
                );
            default:
                return "";
        }
    }

    @Override
    public void Consultar() {
        String query = MakeQuery(1);
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                txt1Field.setText(rs.getString("Name"));
                String proFe = rs.getString("NombreProfesor");
                if (proFe != null) {
                    txt3Field.setText(proFe);
                } else {
                    txt3Field.setText("Sin profesor asignado");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Materia no encontrada.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                null,
                "Error al consultar: " + ex.getMessage()
            );
        }
    }
}
