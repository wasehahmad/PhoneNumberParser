import java.util.*;

public class Controller{
  
  
  
  public void run(){
    
    URLParser parser = new URLParser();
    parser.loadURL("http://google.com");
    
  }
  
  public static void main(String[] args){
    
    Controller parser = new Controller(); //create an instantiation of the controller
    
    parser.run(); //run the controller
    
    
    
  }
  
}
