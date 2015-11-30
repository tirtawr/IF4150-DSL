import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class SongPlayer {
    static List<Integer> octaves = new ArrayList();
    static List<String> pitches = new ArrayList();
    static List<Integer> values = new ArrayList();
    
    public static void main(String[] args) throws IOException{
        JFrame frame = new JFrame("Song Player");                
        JPanel pane = new JPanel();                         
        JButton button1 = new JButton("Play Song");            
        frame.getContentPane().add(pane);                   
        pane.add(button1);                                     
        frame.pack();                                       
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.show();
        
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSong();
            }
            
        
        });
                
    }
    
    public static void playSong(){
        final MidiHandler midiHandler = new MidiHandler(400);
        parseDSL("5-C-2 5-D-1 5-E-2 5-C-1 5-E-1 5-C-1 5-E-1 5-R-1 5-D-1 5-E-1 5-F-1 5-F-1 5-E-1 5-D-1 5-F-1 5-R-1 5-E-2 5-F-1 5-G-2 5-E-1 5-G-1 5-E-1 5-G-1 5-R-1 5-F-2 5-G-1 5-A-1 5-A-1 5-G-1 5-F-1 5-A-2 5-R-1 5-G-2 5-C-1 5-D-1 5-E-1 5-F-1 5-G-1 5-A-2 5-A-2 5-D-1 5-E-1 5-F-1 5-G-1 5-A-1 5-B-2 5-B-2  5-E-1 5-F-1 5-G-1 5-A-1 5-B-1 6-C-2 6-C-1 5-B-1 5-A-1 5-F-1 5-B-1 5-G-1 6-C-1 5-G-1 5-E-1 5-D-1 5-C-1");
        int i;
        for(i=0;i<pitches.size();i++){
            try {
                midiHandler.play(octaves.get(i), pitches.get(i), values.get(i));
            } catch (InterruptedException ex) {
                Logger.getLogger(SongPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    static class MidiHandler {
        Synthesizer syn = null;
        MidiChannel[] mc = null;
        int tempo = 1;
        public MidiHandler(int mTempo){
            try {
                syn = MidiSystem.getSynthesizer();
                syn.open();
                mc = syn.getChannels();
                tempo = mTempo;
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(MidiHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void play(int octave, String pitch, int value) throws InterruptedException{
            int noteNumber = octave*12;
            switch(pitch){
                case "C":
                    break;
                case "C#":
                    noteNumber+=1;
                    break;
                case "D":
                    noteNumber+=2;
                    break;
                case "D#":
                    noteNumber+=3;
                    break;
                case "E":
                    noteNumber+=4;
                    break;
                case "F":
                    noteNumber+=5;
                    break;
                case "F#":
                    noteNumber+=6;
                    break;
                case "G":
                    noteNumber+=7;
                    break;
                case "G#":
                    noteNumber+=8;
                    break;
                case "A":
                    noteNumber+=9;
                    break;
                case "A#":
                    noteNumber+=10;
                    break;
                case "B":
                    noteNumber+=11;                
                    break;
            }
            if(!"R".equals(pitch)){
                mc[1].noteOn(noteNumber,600);
                Thread.sleep(50000*value/tempo);
                mc[1].noteOff(noteNumber);
            }else{
                Thread.sleep(50000*value/tempo);
            }


        }

    }

    public static void parseDSL(String dslText) {
        for(String note : dslText.split(" ")){
            if(note.contains("-")){
                octaves.add(Integer.parseInt(note.split("-")[0]));
                pitches.add(note.split("-")[1]);
                values.add(Integer.parseInt(note.split("-")[2]));
            }   
        }
    }
    
}
