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
        c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        subMenu.add(exit);
        exit.addActionListener(this);
        menubar.add(subMenu);
        setJMenuBar(menubar);

        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));
        panel_up.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        panel_up.add(lblNombre);
        panel_up.add(txtNombre);

        panel_down.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel_down.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        btnSave.addActionListener(this);
        btnRemove.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnQuery.addActionListener(this);

        panel_down.add(btnSave);
        panel_down.add(btnRemove);

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
            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (Name) VALUES ('" +
                    txtNombre.getText() +
                    "')"
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
            return;
        }
        super.actionPerformed(e);
    }
}
