import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import acm.program.*;
import acm.util.*;
import acm.gui.*;


public class Controller extends Program{
  public static final int 
    APPLICATION_WIDTH = 500,
    APPLICATION_HEIGHT = 600;
  
  private JTextArea numberArea;
  private JButton generateButton;
  private JTextField urlInput;
  
  public LinkedList<String> parse(){
    
    URLParser parser = new URLParser();
    String regex = "((?<![Ff]ax)(?<Valid>[\\s|\\(]*?(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{4})))|((?<=[Ff]ax)(?<invalid>.*?(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{4})))";
    parser.loadURL("https://www.allmenus.com/pa/easton/66281-dominos-pizza/menu/",regex);
    return parser.getMatches();
  }
  
  // draw the gui
  public void init() {
    
    setTitle("Phone Number Parser");
    
    generateButton = new JButton("Parse");
    generateButton.addActionListener(this);
    
    urlInput = new JTextField();
    urlInput.setColumns(20);
    
    numberArea = new JTextArea();
    
    add(generateButton, NORTH);
    add(urlInput, NORTH);
    add(new JScrollPane(numberArea), CENTER);
    
  }
  
  public void actionPerformed (ActionEvent evt) {
    if (evt.getSource() == generateButton) {  
      //check to see if the url is valid use try catch
      
      //if it is, return the parsed numbers
      
      
      //
      
    }
    
  }
    
    
    
    
    
//  public static void main(String[] args){
//    
//    Controller parser = new Controller(); //create an instantiation of the controller
//    
//   // parser.run(); //run the controller
//    
//    
//    
//  }
    
  }
