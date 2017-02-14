import java.util.*;
import java.net.*;
import java.io.*;
import java.util.regex.*;

public class URLParser{
  //declare variables for storing the url and matches
  private String currentURL;
  private LinkedList<String> matches=new LinkedList<String>();;
  
  /**
   * Method to parse a website's html using the url
   * @param website the url of the webpage
   * @param the regex command used to parse the website for numbers
   * @return True if url is valid, False if invalid
   */
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
    catch(Exception e){
      
      //return false and exit the method
      return false;
    }
    //instantiate the string to store the html
    String html = "";
    
    //scan the input stream from the url and add it to the html string
    while(scan.hasNext()){
      html = html+scan.next();   
    }
    
    //remove scripts and tags from the htmls
    html = removeScripts(html);
    
    //find the matches and add them to the linked list
    findMatches(html,regex);
    
    scan.close();
    return true;
  }
  
  /**
   * Method to find matches in a string using regex with groups Valid and Invalid
   * @param htmlCode string to be parsed
   * @param regex regex script used to find matches
   * @return the matcher object containing the matches.
   */
  public Matcher findMatches(String htmlCode, String regex){
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(htmlCode);
    while(matcher.find()){
      if(matcher.group("Valid")!=null){//if there is amatch with the group specified as "Valid", add it to the matches list
        matches.add(matcher.group("Valid"));
      }
    }
    //return the matcher after resetting it (for tests)
    return matcher.reset();
    
    
  }
  
  //method to remove scripts from the html string
  public String removeScripts(String string){
    //remove all the extra empty line spaces between the html code
    string = string.replaceAll("(?m)^[ \\t]*\\r?\\n", "");

    //match and remove the scripts and tags from the html code
    String regex = "(<script[\\s\\S]*?<\\/script>)|(<(.|\\n)*?>)"; 
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(string);
    
    String result="";
    //if a stack overflow error occurs (java regex) , return the full html (with no empty lines)
    try{
      result = matcher.replaceAll("");
    }catch(StackOverflowError e){
      return string;
    }
    //if all works well, return the now de-scripted, de-tagged html text
    return result; 
  }
  /**
   * Method to get the Linked List containing the matches
   * @return LinkedList containing the matched groups
   */
  public LinkedList<String> getMatches(){
    return matches;
  }
  
  
  
  
}