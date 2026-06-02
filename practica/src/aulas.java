import java.sql.*;
import javax.swing.*;

public class aulas extends ABC_Base {

    private JTextField txtLocation;

    public aulas(JFrame anterior) {
        super(anterior);
        tabla = "Classroom";
    }

    public aulas() {
        super();
        tabla = "Classroom";
    }

    @Override
    public void Window() {
        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));
        panel_up.add(Box.createVerticalStrut(10));

        lbl1Field = new JLabel("Nombre de aula:");
        panel_up.add(lbl1Field);
        panel_up.add(txt1Field);

        lbl2Field = new JLabel("Tipo de aula:");
        panel_up.add(lbl2Field);
        panel_up.add(txt2Field);

        JLabel lblLocation = new JLabel("Ubicación / Edificio:");
        txtLocation = new JTextField();
        panel_up.add(lblLocation);
        panel_up.add(txtLocation);

        super.Window();

        panel_up.remove(lbl3Field);
        panel_up.remove(txt3Field);

        setTitle("Gestión de Aulas");
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
                    txt1Field.getText() +
                    "'"
                );
            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (Type, Location, Name) VALUES ('" +
                    txt2Field.getText() +
                    "','" +
                    txtLocation.getText() +
                    "','" +
                    txt1Field.getText() +
                    "')"
                );
            case 3:
                return (
                    "UPDATE " +
                    tabla +
                    " SET Type = '" +
                    txt2Field.getText() +
                    "', Location = '" +
                    txtLocation.getText() +
                    "' WHERE Name = '" +
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
                txt2Field.setText(rs.getString("Type"));
                txtLocation.setText(rs.getString("Location"));
            } else {
                JOptionPane.showMessageDialog(null, "Aula no encontrada.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                null,
                "Error al consultar datos: " + ex.getMessage()
            );
        }
    }
}
