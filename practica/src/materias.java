import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class materias extends profesores {

    public materias(JFrame anterior) {
        super(anterior);
    }

    @Override
    public void Window() {
        c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        subMenu.add(exit);
        exit.addActionListener(this);
        menubar.add(subMenu);
        setJMenuBar(menubar);

        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));
        panel_up.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        lblNombre = new JLabel("Nombre de materia:");
        lblNumero = new JLabel("Profesor de la materia:");

        panel_up.add(lblNombre);
        panel_up.add(txtNombre);
        panel_up.add(lblNumero);
        panel_up.add(txtNumero);

        panel_down.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel_down.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        btnSave.addActionListener(this);
        btnRemove.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnQuery.addActionListener(this);

        panel_down.add(btnSave);
        panel_down.add(btnRemove);
        panel_down.add(btnUpdate);
        panel_down.add(btnQuery);

        c.add(panel_up, BorderLayout.CENTER);
        c.add(panel_down, BorderLayout.SOUTH);

        setSize(500, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Gestión de Grupos");
    }

    @Override
    public String MakeQuery(int valor) {
        switch (valor) {
            case 1:
                return ("SELECT * FROM " + tabla);
            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (Name, ProfessorWN) VALUES ('" +
                    txtNombre.getText() +
                    "', " +
                    txtNumero.getText() +
                    ")"
                );
            case 4:
                return (
                    "DELETE FROM " +
                    tabla +
                    " WHERE Name = '" +
                    txtNombre.getText() +
                    "'"
                );
            case 3:
                return (
                    "UPDATE " +
                    tabla +
                    " SET ProfessorWN = " +
                    txtNumero.getText() +
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
        this.tabla = "Signature";

        if (e.getSource() == btnQuery) {
            String query = MakeQuery(1);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                txtNombre.setText(rs.getString("Name"));
                txtNumero.setText(rs.getString("ProfessorWN"));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    null,
                    "Error al consultar: " + ex.getMessage()
                );
            }
            return;
        }
        super.actionPerformed(e);
    }
}
