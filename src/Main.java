import javax.swing.*;
import java.io.IOException;

/**
 * Run the program
 *
 * @author Narges Salehi
 */
public class Main {
    //choosing  look and fell
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        //creat a new GUI
	GUI gui=new GUI();
    }
//
//    public static void main(String[] args) throws IOException, InterruptedException, IOException {
//      HTTPClient h = new HTTPClient();
//
//    }
}
