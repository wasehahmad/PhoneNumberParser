import java.util.*;
import java.net.*;
import java.io.*;
import java.util.regex.*;

public class URLParser{
  private String currentURL;
  private LinkedList<String> matches=new LinkedList<String>();;
  
  public void loadURL(String website,String regex){
    
    
    
    currentURL = website; //store the current website url
    
    Scanner scan = null;
    try{
      //set up the url connection and pass the input stream of the URL to a scanner
      URL url = new URL(currentURL);
      URLConnection connection = url.openConnection();
      scan = new Scanner(connection.getInputStream());
      scan.useDelimiter(/*"(<(.|\\n)*?>)|((<script)(.|\\n)*?(<\\/script>))"*/"\\Z"); //end of line in HTML
    }
    catch(IOException e){
      e.printStackTrace(); 
    }
    
    String html = "";
    
    //scan the document
    while(scan.hasNext()){
      html = html+scan.next();   
    }
//   System.out.println(html);
    //remove scripts
    html = removeScripts(html);
//    System.out.println(html);
    
    
    findMatches(html,regex);
    
    scan.close();
  }
  
  public Matcher findMatches(String htmlCode, String regex){
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(htmlCode);
    while(matcher.find()){
      matches.add(matcher.group());
    }
    return matcher.reset();
    
    
  }
  
  //method to remove scripts from the html string
  public String removeScripts(String string){
    string = string.replaceAll("(?m)^[ \\t]*\\r?\\n", "");
//    try(  PrintWriter out = new PrintWriter( "filename.txt" )  ){
//      out.println( string );
//    }catch(Exception e){
//      e.printStackTrace();
//    }
    String regex = "(<script[\\s\\S]*?<\\/script>)|(<(.|\\n)*?>)"; 
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(string);
    System.out.println("_________________________________________"); 
//    while(matcher.find()){
//      System.out.println(matcher.group()); 
//    }
    
    return matcher.replaceAll("");
    
    
    //use the regex pattern to find matches in the string
    
//    string = string.replaceAll(regex,"");
//    return string.replaceAll("(<(.|\\n)*?>)","");
    
    //System.out.println(html);
    
  }
  
  public LinkedList<String> getMatches(){
   return matches;
  }
  
  
  
  
}