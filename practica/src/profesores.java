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
    JButton btnRemove = new JButton("Eliminar");
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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Principal Menu");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String numero = txtNumero.getText();
        String tabla = "Professor";

        if (e.getSource() == exit) {
            this.dispose();
            anterior.setVisible(true);
        }

        if (e.getSource() == btnSave) {
            try {
                String query =
                    "INSERT INTO " +
                    tabla +
                    " (FirstName, LastName, WorkerNumber) VALUES ('" +
                    nombre +
                    "','" +
                    apellido +
                    "','" +
                    numero +
                    "')";

                Statement stmt = con.createStatement();
                int rowsEffected = stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(
                    null,
                    rowsEffected + "rows effected"
                );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar datos: " + ex.getMessage()
                );
            }
        }

        if (e.getSource() == btnRemove) {
            try {
                String Query =
                    "DELETE from " +
                    tabla +
                    " where WorkerNumber = '" +
                    numero +
                    "'";
                Statement stmt = con.createStatement();
                int rowsEffected = stmt.executeUpdate(Query);
                JOptionPane.showMessageDialog(
                    null,
                    rowsEffected + "rows effected"
                );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    null,
                    "error al eliminar los datos" + ex.getMessage()
                );
            }
        }

        if (e.getSource() == btnUpdate) {
            try {
                String Query =
                    "UPDATE " +
                    tabla +
                    " SET FirstName ='" +
                    nombre +
                    "',LastName ='" +
                    apellido +
                    "' where WorkerNumber = '" +
                    numero +
                    "'";
                Statement stmt = con.createStatement();
                int rowsEffected = stmt.executeUpdate(Query);
                JOptionPane.showMessageDialog(
                    null,
                    rowsEffected + "Exito en la modificacion"
                );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    null,
                    "error al modificar los datos" + ex.getMessage()
                );
            }
        }

        if (e.getSource() == btnQuery) {
            try {
                String Query =
                    "SELECT * from " +
                    tabla +
                    " where WorkerNumber = '" +
                    numero +
                    "'";
                Statement stmt = con.createStatement();
                ResultSet res = stmt.executeQuery(Query);
                res.next();

                txtNombre.setText(res.getString("FirstName"));
                txtApellido.setText(res.getString("LastName"));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    null,
                    "error al consultar los datos" + ex.getMessage()
                );
            }
        }
    }
}
