import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JTextField txtNombre;
    JPasswordField txtPass;
    JButton btnLogin;

    JPanel panel_up = new JPanel();
    JPanel panel_down = new JPanel();
    Container c;

    JMenuBar menubar = new JMenuBar();
    JMenu subMenu = new JMenu("Menu");
    JMenuItem exit = new JMenuItem("Exit");

    public Login() {
        Window();
    }

    public void Window() {
        txtNombre = new JTextField();
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

        setSize(500, 320);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesión");
    }

    public void popo() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url =
                "jdbc:mariadb://localhost:3306/Practica2?user=oswaldo&password=1234";
            profesores.con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "hey como que no hay driver");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "jsjjs no te jalo wey");
        }
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

                Statement stmt = profesores.con.createStatement();
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
