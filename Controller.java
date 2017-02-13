import java.util.*;

public class Controller{
  
  
  
  public void run(){
    
    URLParser parser = new URLParser();
    String regex = "(?<![Ff]ax[:\\-\\s])[\\s|\\(](\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{4})";
    parser.loadURL("https://www.allmenus.com/pa/easton/66281-dominos-pizza/menu/",regex);
    
  }
  
  public static void main(String[] args){
    
    Controller parser = new Controller(); //create an instantiation of the controller
    
    parser.run(); //run the controller
    
    
    
  }
  
}
