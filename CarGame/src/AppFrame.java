import javax.swing.JFrame;

public class AppFrame extends JFrame {
    AppFrame(){
        initApp();
    }

    public void initApp(){
        setTitle("car game");
        pack();
        setSize(500,840);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        AppPanel ap = new AppPanel();
        add(ap);
        setResizable(false);
        setVisible(true);
    }
}
