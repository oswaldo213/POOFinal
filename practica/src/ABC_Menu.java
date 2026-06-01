import javax.swing.*;
public class ABC_Menu extends JFrame {
    JFrame Menu = new JFrame();
    JMenuBar menubar = new JMenuBar();
    JMenu professor = new JMenu("Professor");

    public ABC_Menu(){
        Menu = new JFrame();
        menubar = new JMenuBar();
        menubar.add(professor);

        Menu.setSize(400,200);
        Menu.setVisible(true);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Menu.setLayout(none);

        Menu.add(menubar);

    }
}
