/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package songparser;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ASUS
 */
public class SongPlayer {
    
    public SongPlayer(){}
    
    static List<Integer> octaves = new ArrayList();
    static List<String> pitches = new ArrayList();
    static List<Integer> values = new ArrayList();

    public static void parseDSL(String dslText){
        octaves = new ArrayList();
        pitches = new ArrayList();
        values = new ArrayList();
        
        for(String note : dslText.split(" ")){
            if(note.contains("-")){
                octaves.add(Integer.parseInt(note.split("-")[0]));
                pitches.add(note.split("-")[1]);
                values.add(Integer.parseInt(note.split("-")[2]));
            }   
        }
    }

    public static void playSong(){
        MidiHandler midiHandler = new MidiHandler(400);
        int i;
        for(i=0;i<pitches.size();i++){
            System.out.println(pitches.get(i) + "--");
            System.out.println(octaves.get(i) +" "+ pitches.get(i) +" "+ values.get(i));
            try {
                midiHandler.play(octaves.get(i), pitches.get(i), values.get(i));
            } catch (InterruptedException ex) {
//                Logger.getLogger(SongPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    
    }
    
    
}
