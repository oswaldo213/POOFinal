import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Grupos extends profesores {

    public Grupos(JFrame anterior) {
        super(anterior);
    }

    @Override
    public void Window() {
        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));
        panel_up.add(Box.createVerticalStrut(10));

        lblNombre = new JLabel("Nombre de grupo:");
        txtNombre = new JTextField();
        lblApellido = new JLabel("Semestre:");
        txtApellido = new JTextField();
        lblNumero = new JLabel("Numero de aula");
        txtNumero = new JTextField();

        super.Window();
    }

    @Override
    public String MakeQuery(int valor) {
        switch (valor) {
            case 1:
                return (
                    "SELECT GroupName.Name, GroupName.Semester, c.Name AS ClassroomName " +
                    "FROM " +
                    tabla +
                    " JOIN Classroom c ON GroupName.ClassroomID = c.ClassroomID" +
                    " WHERE GroupName.Name = '" +
                    txtNombre.getText() +
                    "'"
                );
            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (Name, Semester, ClassroomID) VALUES ('" +
                    txtNombre.getText() +
                    "','" +
                    txtApellido.getText() +
                    "'," +
                    "(SELECT ClassroomID FROM Classroom WHERE Name = '" +
                    txtNumero.getText() +
                    "')" +
                    ")"
                );
            case 3:
                return (
                    "UPDATE " +
                    tabla +
                    " SET Name = '" +
                    txtNombre.getText() +
                    "', Semester = '" +
                    txtApellido.getText() +
                    "', ClassroomID = " +
                    "(SELECT ClassroomID FROM Classroom WHERE Name = '" +
                    txtNumero.getText() +
                    "')" +
                    " WHERE Name = '" +
                    txtNombre.getText() +
                    "'"
                );
            case 4:
                return (
                    "DELETE FROM " +
                    tabla +
                    " WHERE Name = '" +
                    txtNombre.getText() +
                    "'"
                );
            default:
                return "";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.tabla = "GroupName";

        if (e.getSource() == btnQuery) {
            String query = MakeQuery(1);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                txtApellido.setText(rs.getString("Semester"));
                txtNumero.setText(rs.getString("ClassroomName"));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    null,
                    "Error al consultar datos: " + ex.getMessage()
                );
            }
            return;
        }
        super.actionPerformed(e);
    }
}
