import java.sql.*;
import javax.swing.*;

public class Grupos extends ABC_Base {

    public Grupos(JFrame anterior) {
        super(anterior);
        tabla = "GroupName";
    }

    public Grupos() {
        super();
        tabla = "GroupName";
    }

    @Override
    public void Window() {
        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));
        panel_up.add(Box.createVerticalStrut(10));

        lbl1Field = new JLabel("Nombre de grupo:");
        txt1Field = new JTextField();
        lbl2Field = new JLabel("Semestre:");
        txt2Field = new JTextField();
        lbl3Field = new JLabel("Numero de aula");
        txt3Field = new JTextField();

        super.Window();
        setTitle("Gestión de Grupos");
    }

    @Override
    public String MakeQuery(int valor) {
        switch (valor) {
            case 1:
                return (
                    "SELECT GroupName.Name, GroupName.Semester, c.Name AS ClassroomName " +
                    "FROM " +
                    tabla +
                    " JOIN Classroom c ON GroupName.ClassroomID = c.ClassroomID " +
                    "WHERE GroupName.Name = '" +
                    txt1Field.getText() +
                    "'"
                );
            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (Name, Semester, ClassroomID) VALUES ('" +
                    txt1Field.getText() +
                    "','" +
                    txt2Field.getText() +
                    "'," +
                    "(SELECT ClassroomID FROM Classroom WHERE Name = '" +
                    txt3Field.getText() +
                    "'))"
                );
            case 3:
                return (
                    "UPDATE " +
                    tabla +
                    " SET Semester = '" +
                    txt2Field.getText() +
                    "', " +
                    "ClassroomID = (SELECT ClassroomID FROM Classroom WHERE Name = '" +
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
                txt2Field.setText(rs.getString("Semester"));
                txt3Field.setText(rs.getString("ClassroomName"));
            } else {
                JOptionPane.showMessageDialog(null, "Grupo no encontrado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                null,
                "Error al consultar datos: " + ex.getMessage()
            );
        }
    }
}
