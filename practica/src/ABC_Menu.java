import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class ABC_Menu extends JFrame implements ActionListener {

    JMenuBar menubar = new JMenuBar();
    JMenu subMenu = new JMenu("Menu");
    JMenu About = new JMenu("About");

    JMenuItem professor = new JMenuItem("Professors");
    JMenuItem students = new JMenuItem("Students");
    JMenuItem Areas = new JMenuItem("Areas");
    JMenuItem Group = new JMenuItem("Grups");
    JMenuItem classroom = new JMenuItem("Classroom");
    JMenuItem exit = new JMenuItem("Exit");

    Connection con;

    public ABC_Menu() {
        subMenu.add(professor);
        subMenu.add(students);
        subMenu.add(Areas);
        subMenu.add(Group);
        subMenu.add(classroom);
        subMenu.add(exit);

        professor.addActionListener(this);
        students.addActionListener(this);
        Areas.addActionListener(this);
        Group.addActionListener(this);
        classroom.addActionListener(this);
        exit.addActionListener(this);

        menubar.add(subMenu);
        menubar.add(About);

        setSize(600, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Principal Menu");

        setJMenuBar(menubar);

        setLayout(null);
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
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == professor) {
            this.setVisible(false);
        }
    }
}
//javac -cp "lib/*" -d bin src/*.java
//java -cp "lib/*:bin" Main
