import junit.framework.*;
import java.util.*;
import java.util.regex.*;


/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestURLParser extends TestCase {
  
  /**
   * Test to remove scripts and tags from a string (html)
   */
  public void testScriptRemover() {
    URLParser parser = new URLParser();
    
    //System.out.print("This is a test \n what about now");
    
    
    
    //strings of code to test for removal of scripts and tags
    String test1 = parser.removeScripts("<a><A><A>This is a test \n what about now<fgh>");//testing basic removal + next line
    assertTrue(test1.compareTo("This is a test \n what about now")==0);
    
    String test2 = parser.removeScripts("<script>This is a test \n what about now</script>");//scripts should be removed in their entirity
    assertTrue(test2.compareTo("")==0);
    
    //scripts should be removed even if their are words outside of internal tags inside the script
    String test3 = parser.removeScripts("<script><a><A><A>This is a test \n what about now<fgh>This is a test \n what about now</script>");
    assertTrue(test3.compareTo("")==0);
    
    //scripts should be removed even if their are words outside of external tags of the script
    String test4 = parser.removeScripts("<a><A><A>This is a test 1</A><script> \n what about now This is a test \n what about now</script><fgh>");
    assertTrue(test4.compareTo("This is a test 1")==0);
    
    
    //scripts should be removed even even with a specifi type
    String test5 = parser.removeScripts("<script type=\"text/javascript\">Testing a javascript </script>");
    assertTrue(test5.compareTo("")==0);
    
    //System.out.println(test4);
    
    
  }
  
  //tests to retrieve phone numbers from a string
  public void testPhoneParsing(){
    URLParser parser = new URLParser();
    String regex = "((?<![Ff]ax)(?<Valid>[\\s|\\(]*?(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{4})))|((?<=[Ff]ax)(?<invalid>.*?(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{4})))";
    
    String test1 = ("(123)456-7890 \n 123 456 7890 \n 123.456.7890 \n 123-45-67890 \n 123-456-7890 \n (123) 456 789 \n fax 987 654 3210 But this is 123-456 7890");
    Matcher matcher =parser.findMatches(test1,regex);
    
    LinkedList<String> matched = parser.getMatches();
    
    while(matcher.find()) {
      System.out.println(matcher.group("Valid")==null?" ":matcher.group("Valid")); 
    }
    
  }
  
}
