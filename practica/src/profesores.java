import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class profesores extends JFrame implements ActionListener {

    protected Connection con;
    protected String tabla = "Professor";

    protected JFrame anterior;

    protected JPanel panel_up = new JPanel();
    protected JPanel panel_down = new JPanel();

    protected Container c = new Container();

    protected JMenuBar menubar = new JMenuBar();
    protected JMenu subMenu = new JMenu("Menu");
    protected JMenuItem exit = new JMenuItem("Exit");

    protected JTextField txtNombre = new JTextField();
    protected JTextField txtApellido = new JTextField();
    protected JTextField txtNumero = new JTextField();

    protected JButton btnSave = new JButton("Guardar");
    protected JButton btnRemove = new JButton("Eliminar");
    protected JButton btnUpdate = new JButton("Actualizar");
    protected JButton btnQuery = new JButton("Consultar");

    public profesores(JFrame anterior) {
        this.anterior = anterior;
        Window();
        popo();
    }

    public void Window() {
        c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        subMenu.add(exit);
        exit.addActionListener(this);
        menubar.add(subMenu);
        setJMenuBar(menubar);

        panel_up.setLayout(new BoxLayout(panel_up, BoxLayout.Y_AXIS));
        panel_up.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblApellido = new JLabel("Apellido:");
        JLabel lblNumero = new JLabel("Número de trabajador:");

        panel_up.add(lblNombre);
        panel_up.add(txtNombre);
        panel_up.add(lblApellido);
        panel_up.add(txtApellido);
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
        setTitle("Gestión de Profesores");
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

    public String MakeQuery(int valor) {
        switch (valor) {
            case 1:
                return "SELECT * FROM " + tabla;
            case 2:
                return (
                    "INSERT INTO " +
                    tabla +
                    " (FirstName, LastName, WorkerNumber) VALUES ('" +
                    txtNombre.getText() +
                    "','" +
                    txtApellido.getText() +
                    "','" +
                    txtNumero.getText() +
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
                    "', WorkerNumber = '" +
                    txtNumero.getText() +
                    "' WHERE WorkerNumber = '" +
                    txtNumero.getText() +
                    "'"
                );
            case 4:
                return (
                    "DELETE FROM " +
                    tabla +
                    " WHERE WorkerNumber = '" +
                    txtNumero.getText() +
                    "'"
                );
            default:
                return "";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            this.dispose();
            anterior.setVisible(true);
        }

        if (e.getSource() == btnSave) {
            try {
                String query = MakeQuery(2);
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
                String query = MakeQuery(4);
                Statement stmt = con.createStatement();
                int rowsEffected = stmt.executeUpdate(query);
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
                String query = MakeQuery(3);
                Statement stmt = con.createStatement();
                int rowsEffected = stmt.executeUpdate(query);
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
                String query = MakeQuery(1);
                Statement stmt = con.createStatement();
                ResultSet res = stmt.executeQuery(query);
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
