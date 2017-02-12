import java.util.*;

public class Controller{
  
  
  
  public void run(){
    
    URLParser parser = new URLParser();
    parser.loadURL("http://stackoverflow.com/questions/9776231/regular-expression-to-validate-us-phone-numbers","(?<![Ff]ax[:\\-\\s])(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{4})");
    
  }
  
  public static void main(String[] args){
    
    Controller parser = new Controller(); //create an instantiation of the controller
    
    parser.run(); //run the controller
    
    
    
  }
  
}
