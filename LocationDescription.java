/*
 * File: LocationDescription.java
 */

public class LocationDescription{
  
  //instance variables
  private String roomName;
  private String suspect;
  private String clue;
  private int token;
  private boolean mark = false;
  
  /* Constructor with just a room
   */
  public LocationDescription(String roomName){
    this.roomName = roomName;
  }
  
  /* Constructor with a room, a suspect and token 
   */
  public LocationDescription(String roomName, String suspect, int token){
    this.roomName = roomName;
    this.suspect = suspect;
    this.token = token;
  }
  
   /* Constructor with all descriptions
   */
  public LocationDescription(String roomName, String suspect, String clue, int token){
    this.roomName = roomName;
    this.suspect = suspect;
    this.clue = clue;
    this.token = token;
  }
  
  /* Gets name 
   * @return String name
   */ 
  public String getName(){
    return this.roomName;
  }
  
  /* Gets suspects 
   * @return String suspect name
   */ 
  public String getSuspect(){
    return this.suspect;
  }
  
  /* Gets clue
   * @return String a clue in the location
   */
  public String getClue(){
    return this.clue;
  }
  
  /* Gets token 
   * @return String a token in the location
   */
  public int getToken(){
    return this.token;
  }
  
  
  public String toString(){        
    String s;
    if(this.roomName.equals("Start Room")){
      s = "You are in the Start Room. Move to another room to see its properties";
    }
    else if(this.clue == null){
      s = "You are in the " + this.getName() + ", and " + this.getSuspect() + " is also in the room.";
    }else if(suspect != "null" && clue != "null"){
      s = "You are in the " + this.getName() + ", and " + this.getSuspect() + " is also in the room." +
        " You also see a " + this.getClue() + " in the room.";
    }
    else if(this.suspect.equals("null") && clue.equals("null")){
      s = "You are in the " + this.getName() + ", and already talked to the suspect"  +
        ". You also inspected the clue in the room.";
    }else if(clue.equals("null")){
      s = "You are in the " + this.getName() + ", and " + this.getSuspect() + " is also in the room." +
        " You also already inspected the clue in the room.";
    }
    else{
      s = "You are in the " + this.getName() + ", and  you already questioned the suspect." +
        " There is also a " + this.getClue() + "in the room.";
    }
    return s;
  }
  
  /* Sets token
   * @param int a token in a  location
   */
  public void setToken(int newToken){
    this.token = newToken;
  }
  
  /* Sets suspect
   * @param STRING  a suspect in a  location
   */
  public void setSuspect(String name){
    this.suspect = name;
  }
  
  /* Sets clue
   * @param STRING  a clue in a  location
   */
  public void setClue(String name){
    this.clue = name;
  }
  
  /* For search method, marks a location 
   */
  public void mark(){
    this.mark = true;
  }
  
  /* For search method, unmarks a location 
   */
  public void unMark(){
    this.mark = false;
  }
  
  /* For search method, gets the value of mark
   */
  public boolean getMark(){
    return this.mark;
  }
}

