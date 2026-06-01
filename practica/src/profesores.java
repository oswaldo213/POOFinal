import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class profesores extends JFrame implements ActionListener {

    Connection con;
    JFrame anterior;
    JMenuBar menubar = new JMenuBar();
    JMenu subMenu = new JMenu("Menu");
    JMenuItem exit = new JMenuItem("Exit");

    JTextField txtNombre = new JTextField();
    JTextField txtApellido = new JTextField();
    JTextField txtNumero = new JTextField();

    JButton btnSave = new JButton("Guardar");
    JButton btnRemove = new JButton("Cancelar");
    JButton btnUpdate = new JButton("Actualizar");
    JButton btnQuery = new JButton("Consultar");

    public profesores(JFrame anterior) {
        this.anterior = anterior;
        Window();
        popo();
    }

    public void Window() {
        Container c = getContentPane();
        JPanel panel_up = new JPanel();
        JPanel panel_down = new JPanel();

        subMenu.add(exit);
        exit.addActionListener(this);
        menubar.add(subMenu);
        setJMenuBar(menubar);

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblApellido = new JLabel("Apellido:");
        JLabel lblNumero = new JLabel("Número:");

        txtNombre.setBounds(100, 50, 200, 30);
        txtApellido.setBounds(100, 100, 200, 30);
        txtNumero.setBounds(100, 150, 200, 30);

        btnSave.setBounds(100, 200, 100, 30);
        btnRemove.setBounds(200, 200, 100, 30);
        btnUpdate.setBounds(300, 200, 100, 30);
        btnQuery.setBounds(400, 200, 100, 30);

        BorderLayout bl = new BorderLayout();
        c.setLayout(bl);

        GridLayout gl = new GridLayout(3, 3);
        panel_up.setLayout(gl);

        panel_up.add(lblNombre);
        panel_up.add(txtNombre);
        panel_up.add(lblApellido);
        panel_up.add(txtApellido);
        panel_up.add(lblNumero);
        panel_up.add(txtNumero);

        FlowLayout fl = new FlowLayout();
        panel_down.setLayout(fl);

        btnSave.addActionListener(this);
        btnRemove.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnQuery.addActionListener(this);

        panel_down.add(btnSave);
        panel_down.add(btnRemove);
        panel_down.add(btnUpdate);
        panel_down.add(btnQuery);

        c.add(panel_up, BorderLayout.NORTH);
        c.add(panel_down, BorderLayout.SOUTH);

        setSize(600, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Principal Menu");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            this.dispose();
            anterior.setVisible(true);
        }
    }

    public void popo() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url =
                "jdbc:mariadb://localhost:3306/Practica?user=oswaldo&password=1234";
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "hey como que no hay driver");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "jsjjs no te jalo wey");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String numero = txtNumero.getText();

            try {
                String query =
                    "INSERT INTO Professor (FirstName, LastName, WorkerNumber) VALUES (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, nombre);
                pst.setString(2, apellido);
                pst.setString(3, numero);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(
                    null,
                    "Datos guardados correctamente"
                );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar datos: " + ex.getMessage()
                );
            }
        }
    }
}
