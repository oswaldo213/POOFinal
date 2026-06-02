import java.sql.*;
import javax.swing.*;

public class Alumnos extends ABC_Base {

    private JTextField txtGroupName;

    public Alumnos(JFrame anterior) {
        super(anterior);
        tabla = "Students";
    }

    @Override
    public void Window() {
        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));

        panel_up.add(Box.createVerticalStrut(10));
        JLabel lblGroupName = new JLabel("Grupo:");

        txtGroupName = new JTextField();
        lbl3Field = new JLabel("Cuenta de alumno:");

        super.Window();

        panel_up.add(lblGroupName);
        panel_up.add(txtGroupName);

        setTitle("Gestión de Alumnos");
        panel_up.revalidate();
        panel_up.repaint();
    }

    @Override
    public String MakeQuery(int valor) {
        switch (valor) {
            case 1:
                return (
                    "SELECT " +
                    tabla +
                    ".FirstName, " +
                    tabla +
                    ".LastName, " +
                    tabla +
                    ".CountNumber, " +
                    "GroupName.Name as NombreGrupo FROM " +
                    tabla +
                    " INNER JOIN GroupName ON " +
                    tabla +
                    ".GroupNameID = GroupName.GroupNameID WHERE " +
                    tabla +
                    ".CountNumber = '" +
                    txt3Field.getText() +
                    "'"
                );
            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (FirstName, LastName, CountNumber, GroupNameID) VALUES ('" +
                    txt1Field.getText() +
                    "','" +
                    txt2Field.getText() +
                    "','" +
                    txt3Field.getText() +
                    "'," +
                    "(SELECT GroupNameID FROM GroupName WHERE Name = '" +
                    txtGroupName.getText() +
                    "'))"
                );
            case 3:
                return (
                    "UPDATE " +
                    tabla +
                    " SET FirstName = '" +
                    txt1Field.getText() +
                    "', LastName = '" +
                    txt2Field.getText() +
                    "', GroupNameID = (SELECT GroupNameID FROM GroupName WHERE Name = '" +
                    txtGroupName.getText() +
                    "') " +
                    "WHERE CountNumber = '" +
                    txt3Field.getText() +
                    "'"
                );
            case 4:
                return (
                    "DELETE FROM " +
                    tabla +
                    " WHERE CountNumber = '" +
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
            String query = MakeQuery(1);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                txt1Field.setText(res.getString("FirstName"));
                txt2Field.setText(res.getString("LastName"));
                txtGroupName.setText(res.getString("NombreGrupo"));
            } else {
                JOptionPane.showMessageDialog(null, "Alumno no encontrado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                null,
                "error al consultar los datos" + ex.getMessage()
            );
        }
    }
}
