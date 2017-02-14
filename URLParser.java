import java.util.*;
import java.net.*;
import java.io.*;
import java.util.regex.*;

public class URLParser{
  private String currentURL;
  private LinkedList<String> matches=new LinkedList<String>();;
  
  public boolean loadURL(String website,String regex){
    
    
    
    currentURL = website; //store the current website url
    
    Scanner scan = null;
    try{
      //set up the url connection and pass the input stream of the URL to a scanner
      URL url = new URL(currentURL);
      URLConnection connection = url.openConnection();
      scan = new Scanner(connection.getInputStream());
      scan.useDelimiter("\\Z"); //end of line in HTML
    }
    catch(IOException e){
      
      //e.printStackTrace(); 
      return false;
    }
    System.out.println("Point");
    String html = "";
    
    //scan the document
    while(scan.hasNext()){
      html = html+scan.next();   
    }
    
    //remove scripts
    html = removeScripts(html);
    
    findMatches(html,regex);
    
    scan.close();
    return true;
  }
  
  public Matcher findMatches(String htmlCode, String regex){
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(htmlCode);
    while(matcher.find()){
      if(matcher.group("Valid")!=null){
        matches.add(matcher.group("Valid"));
      }
    }
    return matcher.reset();
    
    
  }
  
  //method to remove scripts from the html string
  public String removeScripts(String string){
    string = string.replaceAll("(?m)^[ \\t]*\\r?\\n", "");

    String regex = "(<script[\\s\\S]*?<\\/script>)|(<(.|\\n)*?>)"; 
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(string);
    System.out.println("_________________________________________"); 
    
    String result="";
    try{
      result = matcher.replaceAll("");
    }catch(StackOverflowError e){
      return string;
    }
    System.out.println(result);
    return result; 
  }
  
  public LinkedList<String> getMatches(){
    return matches;
  }
  
  
  
  
}