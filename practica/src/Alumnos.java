import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Alumnos extends profesores {

    private JTextField txtSemester;
    private JTextField txtGroupName;

    public Alumnos(JFrame anterior) {
        super(anterior);
    }

    @Override
    public void Window() {
        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));

        panel_up.add(Box.createVerticalStrut(10));
        JLabel lblSemester = new JLabel("Semestre:");
        JLabel lblGroupName = new JLabel("Grupo:");

        txtSemester = new JTextField();
        txtGroupName = new JTextField();

        super.Window();

        panel_up.add(lblSemester);
        panel_up.add(txtSemester);
        panel_up.add(lblGroupName);
        panel_up.add(txtGroupName);

        setTitle("Gestión de Alumnos");
        setSize(700, 550);
        panel_up.revalidate();
        panel_up.repaint();
    }

    @Override
    public String MakeQuery(int valor) {
        switch (valor) {
            case 1:
                return "SELECT * FROM " + tabla;
            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (FirstName, LastName, CountNumber, SemesterNumber, GroupName) VALUES ('" +
                    txtNombre.getText() +
                    "','" +
                    txtApellido.getText() +
                    "','" +
                    txtNumero.getText() +
                    "','" +
                    txtSemester.getText() +
                    "','" +
                    txtGroupName.getText() +
                    "')"
                );
            case 3:
                return (
                    "UPDATE " +
                    tabla +
                    " SET FirstName = '" +
                    txtNombre.getText() +
                    "', LastName = '" +
                    txtApellido.getText() +
                    "', SemesterNumber = '" +
                    txtSemester.getText() +
                    "', GroupName = '" +
                    txtGroupName.getText() +
                    "' WHERE CountNumber = '" +
                    txtNumero.getText() +
                    "'"
                );
            case 4:
                return (
                    "DELETE FROM " +
                    tabla +
                    " WHERE CountNumber = '" +
                    txtNumero.getText() +
                    "'"
                );
            default:
                return "";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tabla = "Students";

        if (e.getSource() == btnQuery) {
            try {
                String query = MakeQuery(1);
                Statement stmt = con.createStatement();
                ResultSet res = stmt.executeQuery(query);
                res.next();

                txtNombre.setText(res.getString("FirstName"));
                txtApellido.setText(res.getString("LastName"));
                txtSemester.setText(res.getString("SemesterNumber"));
                txtGroupName.setText(res.getString("GroupName"));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    null,
                    "error al consultar los datos" + ex.getMessage()
                );
            }
            return;
        }

        super.actionPerformed(e);
    }
}
