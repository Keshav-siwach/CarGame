import javax.swing.JFrame;

public class AppFrame extends JFrame {
    AppFrame(){
        initApp();
    }

    public void initApp(){
        setTitle("car game");
        setSize(500,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        AppPanel ap = new AppPanel();
        add(ap);
        setResizable(false);
        setVisible(true);
    }
}
