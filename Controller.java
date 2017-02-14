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
  
  public LinkedList<String> parse(String url){
    
    URLParser parser = new URLParser();
    String regex = "((?<![Ff]ax)(?<Valid>[\\s|\\(]*?(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{4})))|((?<=[Ff]ax)(?<invalid>.*?(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{3})([\\)\\s\\-\\.]{0,2})(\\d{4})))";
    if(parser.loadURL(url,regex)==false){
      return null;
    }else{
      return parser.getMatches();
    }
    
  }
  
  // draw the gui
  public void init() {
    
    setTitle("Phone Number Parser");
    
    generateButton = new JButton("Parse");
    generateButton.addActionListener(this);
    
    urlInput = new JTextField();
    urlInput.setColumns(20);
    
    numberArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(numberArea); 
    numberArea.setEditable(false);
    
    add(generateButton, NORTH);
    add(urlInput, NORTH);
    add(new JScrollPane(numberArea), CENTER);
    
  }
  
  public void actionPerformed (ActionEvent evt) {
    if (evt.getSource() == generateButton) {  
      //should return null if url was invalid
      
      LinkedList<String> numbers = parse(urlInput.getText());
      
      //If valid, print to the text area
      if(numbers !=null){
        
        numberArea.setText("");//clear the number display area
        String text = "";
        for(int i=0;i<numbers.size();i++){
          numberArea.append(numbers.get(i)+"\n");
        }
        //numberArea.setText(text);
      }else{
        numberArea.setText("An error occurred. Please change the url or try again");
      }
      
      
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
