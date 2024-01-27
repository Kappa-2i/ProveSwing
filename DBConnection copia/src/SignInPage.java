import javax.swing.*;
import java.awt.*;

public class SignInPage extends JFrame {
    private MainApp myGestore;

    public SignInPage(MainApp ma){
        myGestore = ma;
        setTitle("Login Page");
        setSize(1920, 800);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    }
}
