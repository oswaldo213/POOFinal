import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class aulas extends profesores {

    private JTextField txtType = new JTextField();
    private JTextField txtLocation = new JTextField();
    private JTextField txtGroupName = new JTextField();

    public aulas(JFrame anterior) {
        super(anterior);
    }

    @Override
    public void Window() {
        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));
        super.Window();

        panel_up.add(Box.createVerticalStrut(10));

        JLabel lblType = new JLabel("Tipo de aula:");
        panel_up.add(lblType);
        panel_up.add(txtType);

        JLabel lblLocation = new JLabel("Ubicación:");
        panel_up.add(lblLocation);
        panel_up.add(txtLocation);

        JLabel lblGroupName = new JLabel("Grupo:");
        panel_up.add(lblGroupName);
        panel_up.add(txtGroupName);

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
                    "SELECT * FROM Classroom WHERE Number = '" +
                    txtNumero.getText() +
                    "'"
                );
            case 2:
                return (
                    "INSERT INTO Classroom (Type, Location, GroupName) VALUES ('" +
                    txtType.getText() +
                    "','" +
                    txtLocation.getText() +
                    "','" +
                    txtGroupName.getText() +
                    "')"
                );
            case 3:
                return (
                    "UPDATE Classroom SET Type = '" +
                    txtType.getText() +
                    "', Location = '" +
                    txtLocation.getText() +
                    "', GroupName = '" +
                    txtGroupName.getText() +
                    "' WHERE Number = '" +
                    txtNumero.getText() +
                    "'"
                );
            case 4:
                return (
                    "DELETE FROM Classroom WHERE Number = '" +
                    txtNumero.getText() +
                    "'"
                );
            default:
                return "";
        }
    }
}
