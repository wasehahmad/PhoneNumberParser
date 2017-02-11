import java.util.*;
import java.net.*;
import java.io.*;

public class URLParser{
  private String currentURL;
  private LinkedList<String> matches;
  
  public void loadURL(String website){
   
   matches = new LinkedList<String>();  //instantiate the array list where the matched numbers will be kept
    
   currentURL = website; //store the current website url
   
   Scanner scan = null;
   try{
   //set up the url connection and pass the input stream of the URL to a scanner
   URL url = new URL(currentURL);
   URLConnection connection = url.openConnection();
   scan = new Scanner(connection.getInputStream());
   }
   catch(IOException e){
    e.printStackTrace(); 
   }
   
   //scan the document
   
   findMatches(scan,"");
    
  }
  
  public void findMatches(Scanner scan, String regex){
    int i = matches.size();
    while(scan.hasNext()){
      
      
      matches.addLast(scan.next(regex));
      
      
    }
    
  }
  
  
  
  
}