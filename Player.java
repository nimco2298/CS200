import java.awt.event.ActionEvent; // ActionListener 
import java.awt.event.KeyEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyListener; 

import java.io.*; //File
import javax.swing.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component.*;
import java.awt.BorderLayout;


public class Player extends JPanel implements ActionListener,KeyListener { //AL is for timer
  
  
  Timer timer = new Timer(5, this );// every 2 second call Actionlistener
  int x = 0;
  int y = 0;
  int xVel = 0; //speed of the object horizontally 
  int yVel = 0;
    
      
  public Player()
  {
    timer.start(); // once user uses keyboard 
    addKeyListener(this); // for KeyListener
    setFocusable(true); //set focus to use keyListener
    setFocusTraversalKeysEnabled(false); //not using tab,shift keys
    
  }
  
  public void paintComponent(Graphics g) //draw graphics on screen
  {
    super.paintComponent(g);
    g.setColor(Color.GREEN);
    g.fillRect(x,y, 100, 40);
    
  }
  
  public void keyPressed(KeyEvent e)
  {
    int key = e.getKeyCode(); // get the input from key that was pressed 
    
    if(key == KeyEvent.VK_LEFT)
    {
      xVel = -1;
      yVel = 0;
    }
    
     if(key == KeyEvent.VK_RIGHT)
    {
      xVel = 1;
      yVel = 0;
    }
     
      if(key == KeyEvent.VK_UP)
    {
      xVel = 0;
      yVel = -1;
    }
      
       if(key == KeyEvent.VK_DOWN)
    {
      xVel = 0;
      yVel = 1;
    }
  }
    
  public void keyTyped(KeyEvent ke)
  {
  }
  
  public void keyReleased(KeyEvent ke)
  {
    //Stop graphic from moving after key release
    xVel = 0;
    yVel = 0;
    checkbounds();
  }
    
  
  private void checkbounds(){
    if(x < 0){
      x = 0;
    }
    if(x > 900){
      x = 900;
    }
    if(y < 0) {
      y = 0;
    }
    if(y > 900) {
      y = 900;
    }
  }
  
  public void actionPerformed(ActionEvent e)
  {
    x = x + xVel; //after keyboard press, move object and change x
    y = y + yVel;
    repaint(); // refresh the graphics on screen
  }
  
  public static void main(String [] args)
  {
    JFrame frame = new JFrame();
    
   
   //ImageIcon digsite = new ImageIcon("/home/nhussein001/CS200/Labs/Project/DIGSITE.jpg");
   //setIconImage(digsite);
   //JLabel imageLabel = new JLabel("Digsite", digsite, JLabel.CENTER);
   //label.setOpaque(true);
   // label.setBackground("WHITE");
   // frame.add(imageLabel);
//    try{
//    BufferedImage myPicture = ImageIO.read(new File("/home/nhussein001/CS200/Labs/Project/DIGSITE.jpg"));
//    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//    frame.add(picLabel);
//    }
//    catch(IOException ioe){}
   
   
    Player p = new Player();
   // MummyImages mi = new MummyImages();
    frame.setSize(900,900);
    frame.setTitle("Mystery Game");
    frame.setBackground(Color.YELLOW);
    //frame.add(p); //add grphc
    JLabel welcomeL = new JLabel("Please move to correct");
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
     panel.add(welcomeL,BorderLayout.NORTH);
     panel.add(p, BorderLayout.CENTER);
    //panel.add(digsite,BorderLayout.EAST);
    frame.add(panel);
    //frame.add(p);
   // frame.add(p);
    frame.setVisible(true);
   
  }
  }
  
  