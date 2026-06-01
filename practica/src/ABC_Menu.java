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

    public ABC_Menu() {
        Window();
    }

    public void Window() {
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == professor) {
            profesores p = new profesores(this);
            p.setVisible(true);
            this.setVisible(false);
        }
    }
}
//javac -cp "lib/*" -d bin src/*.java
//java -cp "lib/*:bin" Main
