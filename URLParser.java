import java.util.*;
import java.net.*;
import java.io.*;
import java.util.regex.*;

public class URLParser{
  private String currentURL;
  private LinkedList<String> matches;
  
  public void loadURL(String website,String regex){
    
    matches = new LinkedList<String>();  //instantiate the array list where the matched numbers will be kept
    
    currentURL = website; //store the current website url
    
    Scanner scan = null;
    try{
      //set up the url connection and pass the input stream of the URL to a scanner
      URL url = new URL(currentURL);
      URLConnection connection = url.openConnection();
      scan = new Scanner(connection.getInputStream());
      scan.useDelimiter("<.*?>"); //end of line in HTML
    }
    catch(IOException e){
      e.printStackTrace(); 
    }
    
    //scan the document
    String html = "";
    while(scan.hasNext()){
      html = html+scan.next();   
    }
    findMatches(html,regex);
    
    scan.close();
  }
  
  public void findMatches(String html, String regex){
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(html);
    while(matcher.find()){
     System.out.println(matcher.group()); 
    }
    
    
    
  }
  
  
  
  
}