import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class aulas extends profesores {

    private JTextField txtLocation;

    public aulas(JFrame anterior) {
        super(anterior);
    }

    @Override
    public void Window() {
        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));
        panel_up.add(Box.createVerticalStrut(10));

        JLabel lblLocation = new JLabel("Ubicación:");
        txtLocation = new JTextField();
        lblNombre = new JLabel("Nombre de aula:");
        lblApellido = new JLabel("Type:");
        lblNumero = new JLabel("Group Name:");

        super.Window();

        panel_up.add(lblLocation);
        panel_up.add(txtLocation);

        setTitle("Gestión de aulas");
        setSize(500, 300);
        panel_up.revalidate();
        panel_up.repaint();
    }

    @Override
    public String MakeQuery(int valor) {
        switch (valor) {
            case 1:
                return (
                    "SELECT * FROM " +
                    tabla +
                    " WHERE Name = '" +
                    txtNombre.getText() +
                    "'"
                );
            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (Type, Location, GroupName, Name) VALUES ('" +
                    txtApellido.getText() +
                    "','" +
                    txtLocation.getText() +
                    "','" +
                    txtNumero.getText() +
                    "','" +
                    txtNombre.getText() +
                    "')"
                );
            case 3:
                return (
                    "UPDATE " +
                    tabla +
                    " SET Type = '" +
                    txtApellido.getText() +
                    "', Location = '" +
                    txtLocation.getText() +
                    "', GroupName = '" +
                    txtNumero.getText() +
                    "' WHERE Name = '" +
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
        this.tabla = "Classroom";

        if (e.getSource() == btnQuery) {
            String query = MakeQuery(1);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                txtNombre.setText(rs.getString("Name"));
                txtApellido.setText(rs.getString("Type"));
                txtLocation.setText(rs.getString("Location"));
                txtNumero.setText(rs.getString("GroupName"));
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
