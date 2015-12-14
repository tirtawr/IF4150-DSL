/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package songparser;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Tirta
 */
public class MidiHandler {
        Synthesizer syn = null;
        MidiChannel[] mc = null;
        int tempo = 100;
        public MidiHandler(int mTempo){
            try {
                tempo = mTempo;
                syn = MidiSystem.getSynthesizer();
                syn.open();
                mc = syn.getChannels();
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

