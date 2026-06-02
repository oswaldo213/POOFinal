import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login extends profesores {

    JPasswordField txtPass;
    JButton btnLogin;

    public Login() {
        super(null);
    }

    @Override
    public void Window() {
        txtPass = new JPasswordField();
        btnLogin = new JButton("Ingresar");

        c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        subMenu.add(exit);
        exit.addActionListener(this);
        menubar.add(subMenu);
        setJMenuBar(menubar);

        panel_up.setLayout(new GridLayout(2, 2, 10, 10));
        panel_up.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));

        JLabel lblUser = new JLabel("Usuario:");
        JLabel lblPass = new JLabel("Contraseña:");

        panel_up.add(lblUser);
        panel_up.add(txtNombre);
        panel_up.add(lblPass);
        panel_up.add(txtPass);

        panel_down.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel_down.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        btnLogin.addActionListener(this);
        panel_down.add(btnLogin);

        c.add(panel_up, BorderLayout.CENTER);
        c.add(panel_down, BorderLayout.SOUTH);

        setSize(400, 220);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesión");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        }

        if (e.getSource() == btnLogin) {
            String usuario = txtNombre.getText();
            String password = new String(txtPass.getPassword());

            try {
                String query =
                    "SELECT * FROM Admin WHERE Username = '" +
                    usuario +
                    "' AND Passphrase = '" +
                    password +
                    "'";

                Statement stmt = con.createStatement();
                ResultSet res = stmt.executeQuery(query);

                if (res.next()) {
                    JOptionPane.showMessageDialog(null, "Acceso exitoso");
                    ABC_Menu menu = new ABC_Menu();
                    menu.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Datos incorrectos");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    null,
                    "Error: " + ex.getMessage()
                );
            }
        }
    }
}
