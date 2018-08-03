
import java.io.*; //File
import javax.swing.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component.*;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Dimension;


public class MummyImages extends JFrame
{
  //create method 
  JFrame frame;
//  ImageIcon equipmentRoom;
//  ImageIcon digsite;
//  ImageIcon entranceHall;
//  ImageIcon grandHall;
  
  JLabel digsiteL;
  JLabel entranceHallL;
  JLabel grandHallL;
  JLabel equipmentL;
  JLabel treasureL;
  
  
  public MummyImages()
  {
    
    
    ImageIcon digsite = new ImageIcon("/home/nhussein001/CS200/Labs/Project/DIGSITE.jpg");
    ImageIcon digsiteScaled = new ImageIcon(digsite.getImage()
                .getScaledInstance(digsite.getIconWidth() / 3,
                        digsite.getIconHeight() / 3, Image.SCALE_SMOOTH));
    digsiteL = new JLabel(digsiteScaled);
    
    ImageIcon equipmentRoom = new ImageIcon("/home/nhussein001/CS200/Labs/Project/equipmentHall.png");
    ImageIcon equipmentScaled = new ImageIcon(equipmentRoom.getImage()
                .getScaledInstance(equipmentRoom.getIconWidth() / 1,
                        equipmentRoom.getIconHeight() / 1, Image.SCALE_SMOOTH));
    equipmentL = new JLabel(equipmentScaled);
    
    ImageIcon entranceHall = new ImageIcon("/home/nhussein001/CS200/Labs/Project/entraceHall.jpg");
    ImageIcon entranceHallScaled = new ImageIcon(entranceHall.getImage()
                .getScaledInstance(entranceHall.getIconWidth() / 3,
                        entranceHall.getIconHeight() / 3, Image.SCALE_SMOOTH));
    entranceHallL = new JLabel(entranceHallScaled);
    
    
    ImageIcon grandHall = new ImageIcon("/home/nhussein001/CS200/Labs/Project/grandHall.jpg");
    ImageIcon grandScaled = new ImageIcon(grandHall.getImage()
                .getScaledInstance(grandHall.getIconWidth() / 2,
                        grandHall.getIconHeight() / 2, Image.SCALE_SMOOTH));
    grandHallL = new JLabel(grandScaled);
    
    ImageIcon treasureRoom = new ImageIcon("/home/nhussein001/CS200/Labs/Project/treasureRoom.jpg");
    ImageIcon treasureScaled = new ImageIcon(treasureRoom.getImage()
                .getScaledInstance(treasureRoom.getIconWidth() / 2,
                        treasureRoom.getIconHeight() / 2, Image.SCALE_SMOOTH));
    treasureL = new JLabel(treasureScaled);
    
    frame = new JFrame();
    frame.setLayout(null);
    frame.setSize(1000,1000);
    frame.setTitle("Mystery Game");
    frame.setVisible(true);
    frame.getContentPane().setBackground(Color.cyan);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
   }
                      
  
    public void displayPicture(String currentLocation) 
  {
      
     System.out.println("FROM MI YOUR LCOAIO IS " + currentLocation);
    //JLabel equipmentRoom = new JLabel(new ImageIcon("/home/nhussein001/CS200/Labs/Project/equipmentHall.jpg"));
    
  
    //resizes images
    Dimension digSize = digsiteL.getPreferredSize();
    digsiteL.setBounds(50,50, digSize.width, digSize.height);
    
    //Add it to position
    Dimension entranceSize = entranceHallL.getPreferredSize();
    entranceHallL.setBounds(200,250, entranceSize.width, entranceSize.height);
    
    Dimension equipmentSize = equipmentL.getPreferredSize();
    equipmentL.setBounds(600,650, equipmentSize.width, equipmentSize.height);
    
    Dimension grandSize = grandHallL.getPreferredSize();
    grandHallL.setBounds(300,350, grandSize.width, grandSize.height);
    
    Dimension treasureSize = treasureL.getPreferredSize();
    treasureL.setBounds(850, 850, treasureSize.width, treasureSize.height);
   
  
    if(currentLocation.equals("Dig Site")){
      //display picture 
 
       frame.add(digsiteL); 
       digsiteL.setText("Dig Site");
       //frame.getContentPane().setBackground(Color.red);
    }
     else if(currentLocation.equals("Entrance Hall")){
      frame.add(entranceHallL);
      entranceHallL.setText("Entrance Hall");
      frame.getContentPane().setBackground(Color.yellow);
    }
      else if(currentLocation.equals("Grand Hall")){
       
      frame.add(grandHallL);
      grandHallL.setText("Grand Hall");
      System.out.println("YESS GAND HALL");
    }
      else if(currentLocation.equals("Equipment Storage")){
      frame.add(equipmentL);
      equipmentL.setText("Equipment Storage");
      frame.getContentPane().setBackground(Color.green);
    }
    
      else if(currentLocation.equals("Treasure Room")){
      frame.add(treasureL);
      setVisible(true);
      treasureL.setText("Treasure Room");
      //frame.getContentPane().setBackground(Color.y);
    }
      
     
    
   
  
    
  }
  
  
 
  
}
