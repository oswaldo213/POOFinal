import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public abstract class ABC_Base extends JFrame implements ActionListener {

    public static Connection con;
    protected String tabla;
    protected JFrame anterior;

    protected JPanel panel_up = new JPanel();
    protected JPanel panel_down = new JPanel();
    protected Container c;

    protected JMenuBar menubar = new JMenuBar();
    protected JMenu subMenu = new JMenu("Menu");
    protected JMenuItem exit = new JMenuItem("Exit");

    protected JTextField txt1Field = new JTextField();
    protected JTextField txt2Field = new JTextField();
    protected JTextField txt3Field = new JTextField();

    protected JButton btnSave = new JButton("Guardar");
    protected JButton btnRemove = new JButton("Eliminar");
    protected JButton btnUpdate = new JButton("Actualizar");
    protected JButton btnQuery = new JButton("Consultar");

    protected JLabel lbl1Field = new JLabel("Nombre:");
    protected JLabel lbl2Field = new JLabel("Apellido:");
    protected JLabel lbl3Field = new JLabel("Número:");

    public ABC_Base(JFrame anterior) {
        this.anterior = anterior;
        Window();
    }

    public ABC_Base() {
        Window();
        this.anterior = null;
    }

    protected static void conectar() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/Practica2?user=oswaldo&password=1234"
            );
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver no encontrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "Error de conexión: " + e.getMessage()
            );
        }
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

        panel_up.add(lbl1Field);
        panel_up.add(txt1Field);
        panel_up.add(lbl2Field);
        panel_up.add(txt2Field);
        panel_up.add(lbl3Field);
        panel_up.add(txt3Field);

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

        setSize(700, 550);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Gestión de Profesores");
    }

    public abstract String MakeQuery(int valor);

    public void darDeAlta() {
        try {
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(MakeQuery(2));
            JOptionPane.showMessageDialog(null, rows + " rows affected");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                null,
                "Error al guardar: " + ex.getMessage()
            );
        }
    }

    public void darDeBaja() {
        try {
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(MakeQuery(4));
            JOptionPane.showMessageDialog(null, rows + " rows affected");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                null,
                "Error al eliminar: " + ex.getMessage()
            );
        }
    }

    public void Cambiar() {
        try {
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(MakeQuery(3));
            JOptionPane.showMessageDialog(null, rows + " rows affected");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                null,
                "Error al modificar: " + ex.getMessage()
            );
        }
    }

    public abstract void Consultar();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            this.dispose();
            if (anterior != null) anterior.setVisible(true);
            else System.exit(0);
        }
        if (e.getSource() == btnSave) darDeAlta();
        if (e.getSource() == btnRemove) darDeBaja();
        if (e.getSource() == btnUpdate) Cambiar();
        if (e.getSource() == btnQuery) Consultar();
    }
}
