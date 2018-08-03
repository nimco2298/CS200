
import javax.swing.*;
import java.awt.Color;

public class MysteryGame extends JFrame
{
  JFrame window1;
  
  public MysteryGame() 
  {
    window1 = new JFrame();
    window1.setSize(1100,800);
    window1.setLayout(null);
    window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window1.setVisible(true);
    window1.setTitle("Who Stole The Crown?");
    window1.getContentPane().setBackground(Color.yellow);
  }
  
  
   public static void main(String [] args)
  {
     new MysteryGame();
   }
}
                                     