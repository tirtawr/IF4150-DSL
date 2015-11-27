/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package songplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class SongPlayer {

    static List<Integer> octaves = new ArrayList();
    static List<String> pitches = new ArrayList();
    static List<Integer> values = new ArrayList();
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
            final MidiHandler midiHandler = new MidiHandler(100);
            parseFile("song.lagu");
        
            JFrame frame = new JFrame("Sound1");                
            JPanel pane = new JPanel();                         
            JButton button1 = new JButton("C");
            JButton button2 = new JButton("D");
            frame.getContentPane().add(pane);                   
            pane.add(button1); 
            pane.add(button2);
            frame.pack();                                       
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.show();
            
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        midiHandler.play(5, "C", 1);
                        midiHandler.play(5, "D", 1);
                        midiHandler.play(5, "E", 1);
                        midiHandler.play(5, "F", 1);
                        midiHandler.play(5, "G", 1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SongPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });    // END OF THE ACTION LISTENER
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i;
                    for(i=0;i<pitches.size();i++){
                        System.out.println(pitches.get(i) + "--");
                        System.out.println(octaves.get(i) +" "+ pitches.get(i) +" "+ values.get(i));
                        try {
                            midiHandler.play(octaves.get(i), pitches.get(i), values.get(i));
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SongPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });    // END OF THE ACTION LISTENER
    }       // END OF THE MAIN METHOD  
    
    public static void parseFile(String fileName) throws IOException{
        String songString = readFile(fileName);
        System.out.println(songString);
        for(String note : songString.split(" ")){
            if(note.contains("-")){
                System.out.println("|"+note.split("-")[0]+"|");
                System.out.println("|"+note.split("-")[1]+"|");
                System.out.println("|"+note.split("-")[2]+"|");
                octaves.add(Integer.parseInt(note.split("-")[0]));
                pitches.add(note.split("-")[1]);
                values.add(Integer.parseInt(note.split("-")[2]));
            }
            
        }
        
        
    }
    
    static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(" ");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

}
    

