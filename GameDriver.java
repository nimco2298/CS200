/**
 * File: GameDriver.java
 */

import javax.swing.JOptionPane;
import java.util.*;

public class GameDriver{
  
 
   
  public static void main(String[] args){
    
    final String START = "Start Room";
    String currentLocation = START;
    int energy = 150;
    GameLayout myLayout = new GameLayout("connections", "locations");
    AllImages ai = new AllImages();
    ArrayList<String> suspectOption = myLayout.getSuspectList();

    
    String[] actions = {"Move to a Connected Location", "View Notebook", "List Room Properties", 
                        "Search for the Nearest Clue", "Interact With the Room", "Exit the Game"};
    String[] roomInteractions = {"Talk to a suspect (-20 energy)", "Look for clues(-25 energy)", "Search for a token(-5 energy)"};
    String[] suspectList = suspectOption.toArray(new String[suspectOption.size()]);
    
    JOptionPane.showMessageDialog(null, "Welcome to the Mystery Game! Someone has stolen the mummy's crown and" +
                                      " you have been hired to figure out who.");
    while(energy > 0){
      
      ai.displayPicture(currentLocation);
      int i = 0;
      if(currentLocation.equals(START)){
        JOptionPane.showMessageDialog(null,"You have 150 energy points and a notebook to view your list of suspects and locations" +
                                      " at any given time.\n Every investigation will cost you energy points so use them wisely.\n " +
                                      "You also have the opportunity to search for tokens. You can search for a token in any room but " +
                                      " not all rooms have a token. \n Tokens will give you more energy to keep searching. " +
                                      "After all your energy points run out " + 
                                      "or you move to the Mummy's tomb,\n you will make a guess as to who stole " +
                                      "the crown. If you guess correctly you win!");
        i = JOptionPane.showOptionDialog(null,
                                     "You are currently in the " + currentLocation + " and have " +
                                            energy + " energy. Move to the next location or view your notebook:",
                                              "Actions", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                               null, actions, actions[1]);
      }else{
        LocationDescription currentDescription = myLayout.getDescription(currentLocation);
        i = JOptionPane.showOptionDialog(null, 
                                             currentDescription.toString() + " \n You have " +
                                            energy + " energy. Would you like to:",
                                              "Actions", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                               null, actions, actions[1]);
      }
      if(i == 0){
        currentLocation = move(myLayout, currentLocation);
      }
      else if(i == 1)
        viewNotebook(myLayout, currentLocation);
      else if(i == 2)
        listRoomProperties(myLayout, currentLocation);
      else if (i == 3)
        search(myLayout, currentLocation);
      else if (i == 4){
        int l = JOptionPane.showOptionDialog(null, "What would you like to do in the room? ", "Searching for clues", JOptionPane.DEFAULT_OPTION,
                                 JOptionPane.INFORMATION_MESSAGE, null, roomInteractions, roomInteractions[0]);
        int energyUsed = interact(myLayout, currentLocation, l, energy);
        if(energyUsed == 50)
          energy = energy + energyUsed;
        else
          energy = energy - energyUsed;
      }else{
        break;
    }
      
      
      if(currentLocation.equals("Tomb"))
           break;
    }
    if(currentLocation.equals("Tomb") || energy <= 0)
      endGame(myLayout, suspectList);
      ai.close();
    
  }
  
  /* User can view info of suspects 
   * @param GameLayout  map of the game 
   * @param String  my current location
   * 
   */
  public static void viewNotebook(GameLayout myLayout, String currentLocation){
    Iterator<String> rooms = myLayout.iterateNames();
    String s = "The rooms on the map are: \n";
    while(rooms.hasNext()){
      s = s + rooms.next() + " \n";
    }
    JOptionPane.showMessageDialog(null, s);
    ArrayList<String> suspects = myLayout.getSuspectList();
    String t = "The current suspects are: \n";
    for(String suspect: suspects){
      t = t + suspect + "\n";
    }
    JOptionPane.showMessageDialog(null, t);
    
  }
  
  
    /* User can move from one location in the graph to another 
   * @param GameLayout  map of the game 
   * @param String  my current location
   * 
   */
  public static String move(GameLayout myLayout, String currentLocation){
    Iterator<String> connections = myLayout.iterateConnections(currentLocation);
    ArrayList<String> rooms = new ArrayList<String>();
    while (connections.hasNext()){
      rooms.add(connections.next());
    }
    String[] list = new String[rooms.size()];
    list = rooms.toArray(list);
    int k = JOptionPane.showOptionDialog(null, "You can move to:", "Moving", JOptionPane.DEFAULT_OPTION,
                                         JOptionPane.INFORMATION_MESSAGE, null, list, list[0]);
    return list[k];
  }
  
  
    /* User can list properties the currentLocation 
   * @param GameLayout  map of the game 
   * @param String  my current location
   * 
   */
  public static void listRoomProperties(GameLayout myLayout, String currentLocation){
    LocationDescription description = myLayout.getDescription(currentLocation);
    String toString = description.toString();
    JOptionPane.showMessageDialog(null, toString);
  }
  
   /* User can search for the nearest clue based on their location 
   * @param GameLayout  map of the game 
   * @param String  my current location
   * 
   * @return int  whether or not theres a clue 
   * 
   */
  public static int search(GameLayout myLayout, String currentLocation){
    Queue<LocationDescription> locations = new LinkedList<LocationDescription>();
    LocationDescription currentDescription = myLayout.getDescription(currentLocation);
    locations.add(currentDescription);
    currentDescription.mark();
    while(locations.size() != 0){
      LocationDescription loc = locations.remove();
      if(!(loc.getClue().equals("null"))){
        Iterator<String> names = myLayout.iterateNames();
        while(names.hasNext()){
          LocationDescription l = myLayout.getDescription(names.next());
          l.unMark();
        }
        JOptionPane.showMessageDialog(null, "The nearest clue is in the " + loc.getName());
        return 1;
        
      }
      Iterator<String> connections = myLayout.iterateConnections(currentDescription.getName());
      while(connections.hasNext()){
        LocationDescription l = myLayout.getDescription(connections.next());
        l.mark();
        locations.add(l);
      }
    }
    Iterator<String> names = myLayout.iterateNames();
    while(names.hasNext()){
      LocationDescription l = myLayout.getDescription(names.next());
      l.unMark();
    }
    JOptionPane.showMessageDialog(null, "There is no nearest clue");  
    return 1;
  }
  
  //Returns the energy lost
  public static int interact(GameLayout myLayout, String currentLocation, int l, int energy){
    if(currentLocation.equals("Start Room")){
      JOptionPane.showMessageDialog(null, "You are in the start room, there are no suspects or clues or tokens");
      return 0;
    }
    LocationDescription descForCurrentRoom = myLayout.getDescription(currentLocation);
    if(l == 0 && energy >= 20){
      //talk to a suspect and return  20
      String suspectName = descForCurrentRoom.getSuspect();
      if(suspectName.equals("null")){
        JOptionPane.showMessageDialog(null, "You already investigated this. pick something else");
        return 0;
      }
      else if(suspectName.equals(myLayout.getGuilty())){
        JOptionPane.showMessageDialog(null, suspectName + " says: I bet you it was that Tour Guide Sara. She's always " +
                                      " giving tours around here. She knows this place like the back of her hand.");
      }
      else if(suspectName.equals("Julie the Archeologist")){
        JOptionPane.showMessageDialog(null, suspectName + " says: Well hello there! This crown being missing shurley is a big deal! " 
                                        + "\n I was out here digging when all the alarms went off in the pyramid and I saw a swift " +
                                      "figure run into the night. \n The thief had to be fast and know where they were going. ");
      }else if(suspectName.equals("Pat the Tourist")){
        JOptionPane.showMessageDialog(null, suspectName + " says: Wow I can not believe I am caught up in all of this. " + 
                                      "\n I was just here on a visit and now I'm stuck here until they figure out who did this. " +
                                      "\n I was supposed to fly out bright and early this morning, but the robery last night is delaying me.");
      }
      else if(suspectName.equals("Michelle the Reporter")){
        JOptionPane.showMessageDialog(null, suspectName + " says: I have been studying these ruins my whole career and now, " +
                                      "\n someone steals the Mummy's Crown. At least this story will be interesting to the general public.");
      }else if(suspectName.equals("Tim the Archeologist")){
        JOptionPane.showMessageDialog(null, suspectName + " says: Me? You think I did this? I'm just here because of the steady job." +
                                      "\n  I don't actually like the artifacts enough to take them.");
      }else if(suspectName.equals("Sara the Tour Guide")){
        JOptionPane.showMessageDialog(null, suspectName + " says: This is the most interesting thing that has happened here since I " +
                                      "started working here! \n I love all the adventure and excitement around here! It's good for business.");
      }
      myLayout.setPropertyToInspected(currentLocation, suspectName);
      return 20;
    }else if(l == 1 && energy >= 25){
      String clueName = descForCurrentRoom.getClue();
      if(clueName.equals("null")){
        JOptionPane.showMessageDialog(null, "You already investigated this. pick something else");
        return 0;
      }
      else if(clueName.equals("Archeologist Brush")){
        JOptionPane.showMessageDialog(null, "You pick up the archeologist brush on the ground. \n It looks as if it was used recently, so " +
                                            "if someone claimed to be working, they probaly weren't lying.");
      }else if(clueName.equals("Fanny Pack")){
        JOptionPane.showMessageDialog(null, "You ask to see what Pat has in his fanny pack. He lets you look in it. Seems trusting. \n"+
                                            "You find his passport and some extra change as well as a plane "  
                                            + "ticket to leave today, the day after the break in..");
      }
      else if(clueName.equals("Pencil")){
        JOptionPane.showMessageDialog(null, "You see Michelle the Reporter with her notebook and notice that she has a pencil. \n" +
                                       "The pencil is chewed up as if she was nervous about something.");
      }else if(clueName.equals("Ring")){
        JOptionPane.showMessageDialog(null, "Looking around you see a ring on the floor. The only person in the room is Tim the Archeologist." +
                                      " \n Maybe Tim would know where the ring came from.");
      }else if(clueName.equals("Map")){
        JOptionPane.showMessageDialog(null, "You see a piece of paper shredded up near the crime scene. Upon further investigation, " +
                                      " It looks like a map Sara uses for her tours. \n The tears are not natural, almost as if they were on purpose.");
      }
      myLayout.setPropertyToInspected(currentLocation, clueName);
      
      return 25;
    }
    else if(energy >= 5){
      int tokenNumber = descForCurrentRoom.getToken();
      double randomChance = (Math.random()*10) + 1;
      int random = (int)randomChance;
      if(tokenNumber == random){
        JOptionPane.showMessageDialog(null, "Congratulations you found a token! You earned 50 energy points");
        return 50;
      }
      else{
        JOptionPane.showMessageDialog(null, "You did not find a token this time.");
        return 5;
      }
    }
    else{
      JOptionPane.showMessageDialog(null, "You do not have enough energy.");
      return 0;
    }
  }
  
  
   /* User must answer to the MUMMMY so the game can end 
   * @param GameLayout  map of the game 
   * @param String      list of all the suspects 
   * 
   */
  public static void endGame(GameLayout myLayout, String[] suspectList){
    JOptionPane.showMessageDialog(null, "You have entered the Tomb of the Mummy. There is no exit from this room. " +
                                  "\n The Mummy wants to know who stole his crown. Now is your time to shine.");
    int i = JOptionPane.showOptionDialog(null, "Who do you think stole the crown:", "Final Guess", JOptionPane.DEFAULT_OPTION,
                                         JOptionPane.INFORMATION_MESSAGE, null, suspectList, suspectList[0]);
    if(suspectList[i].equals(myLayout.getGuilty())){
      JOptionPane.showMessageDialog(null, "Congratulations! You have won the game! The thief was " + myLayout.getGuilty() +
                                    ". \n They framed Sara by placing the Map at the crime scene and campaigning against her.");
    }else{
      JOptionPane.showMessageDialog(null, "You let the thief get away! The correct thief was " + myLayout.getGuilty() +
                                    ". \n They framed Sara by placing the Map at the crime scene and campaigning agianst her.");
    }
  
  }
  
}