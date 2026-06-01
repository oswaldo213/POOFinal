import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class ABC_Menu extends JFrame implements ActionListener {

    JFrame Menu = new JFrame();
    JMenuBar menubar = new JMenuBar();
    JMenu subMenu = new JMenu("Menu");
    JMenuItem professor = new JMenuItem("Professors");

    Connection con;

    public ABC_Menu() {
        subMenu.add(professor);
        menubar.add(subMenu);
        Menu.setSize(600, 300);
        Menu.setVisible(true);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Menu.setJMenuBar(menubar);

        Menu.setLayout(null);
        popo();
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
        if (e.getSource() == professor) {
        }
    }
}
//javac -cp "lib/*" -d bin src/*.java
//java -cp "lib/*:bin" Main
