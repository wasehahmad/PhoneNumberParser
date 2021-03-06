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
  //set the constants for the frame
  public static final int 
    APPLICATION_WIDTH = 500,
    APPLICATION_HEIGHT = 600;
  
  //declare the variables of the GUI
  private JTextArea numberArea;
  private JButton generateButton;
  private JTextField urlInput;
  
  /**
   * Method to parse the html given the url
   * @param url the url of the website to parse
   * @return A linked list of strings containing the phone numbers present on the website. Null if url is not valid
   */
  public LinkedList<String> parse(String url){
    
    URLParser parser = new URLParser();
    String regex = "((?<![Ff]ax)(?<Valid>[\\s|\\(]*?(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{4})))|((?<=[Ff]ax)(?<invalid>.*?(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{4})))";
    if(parser.loadURL(url,regex)==false){//if the url is invalid
      return null;
    }else{//else return a linked list of the phone numbers
      return parser.getMatches();
    }
    
  }
  
  // draw the gui
  public void init() {
    
    //set up the GUI
    setTitle("Phone Number Parser");
    //button
    generateButton = new JButton("Parse");
    generateButton.addActionListener(this);
    //url input field
    urlInput = new JTextField();
    urlInput.setColumns(20);
    //display panel for the phone numbers
    numberArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(numberArea); 
    numberArea.setEditable(false);
    //add all to the GUI
    add(generateButton, NORTH);
    add(urlInput, NORTH);
    add(new JScrollPane(numberArea), CENTER);
    
  }
  
  public void actionPerformed (ActionEvent evt) {
    if (evt.getSource() == generateButton) {  
      
      LinkedList<String> numbers = parse(urlInput.getText());
      
      //If valid, print to the text area
      if(numbers !=null){
        
        numberArea.setText("");//clear the number display area
        String text = "";
        for(int i=0;i<numbers.size();i++){
          numberArea.append(numbers.get(i)+"\n");
        }
      }else{
        numberArea.setText("An error occurred. Please change the url or try again. Try adding the protocol to the beginning of the url");
      }
      
      
    }
    
  }
  
  public static void main(String[] args){
    
   Controller control =new Controller();
   control.start();
    
  }
    
    
  }
