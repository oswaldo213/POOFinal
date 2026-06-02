import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.security.Signature;
import javax.swing.*;

public class ABC_Menu extends JFrame implements ActionListener {

    JMenuBar menubar = new JMenuBar();
    JMenu subMenu = new JMenu("Menu");
    JMenu About = new JMenu("About");

    JMenuItem professor = new JMenuItem("Professors");
    JMenuItem students = new JMenuItem("Students");
    JMenuItem Signature = new JMenuItem("Signatures");
    JMenuItem Group = new JMenuItem("Groups");
    JMenuItem classroom = new JMenuItem("Classroom");
    JMenuItem exit = new JMenuItem("Exit");

    public ABC_Menu() {
        Window();
    }

    public void Window() {
        subMenu.add(professor);
        subMenu.add(students);
        subMenu.add(Signature);
        subMenu.add(Group);
        subMenu.add(classroom);
        subMenu.add(exit);

        professor.addActionListener(this);
        students.addActionListener(this);
        Signature.addActionListener(this);
        Group.addActionListener(this);
        classroom.addActionListener(this);
        exit.addActionListener(this);

        menubar.add(subMenu);
        menubar.add(About);

        setSize(700, 400);
        setLocationRelativeTo(null);
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

        if (e.getSource() == students) {
            Alumnos a = new Alumnos(this);
            a.setVisible(true);
            this.setVisible(false);
        }

        if (e.getSource() == classroom) {
            aulas a = new aulas(this);
            a.setVisible(true);
            this.setVisible(false);
        }

        if (e.getSource() == Group) {
            Grupos g = new Grupos(this);
            g.setVisible(true);
            this.setVisible(false);
        }

        if (e.getSource() == Signature) {
            materias m = new materias(this);
            m.setVisible(true);
            this.setVisible(false);
        }
    }
}
