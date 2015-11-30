/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package songparser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author ASUS
 */
public class NewJFrame extends javax.swing.JFrame {

    
    static List<Integer> octaves = new ArrayList();
    static List<String> pitches = new ArrayList();
    static List<Integer> values = new ArrayList();
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Generate Source Code");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Input");

        jButton2.setText("Play Song");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jButton1)
                                .addGap(32, 32, 32)
                                .addComponent(jButton2)))
                        .addGap(0, 73, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
        // Generate Source Code
        writeSourceCode(jTextArea1.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //Play song
        parseDSL(jTextArea1.getText());
        playSong();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    public static void parseDSL(String dslText){
        octaves = new ArrayList();
        pitches = new ArrayList();
        values = new ArrayList();
        
        for(String note : dslText.split(" ")){
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
    
    public void writeSourceCode(String dslText){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("SongPlayer.java", "UTF-8");
            writer.print(souceCodeTop);
            writer.print(dslText);
            writer.print(SourceCodeBottom);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }
    
    static class MidiHandler {
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

    static String souceCodeTop = ""
            + "import java.awt.event.ActionEvent;\n"
            + "import java.awt.event.ActionListener;\n"
            + "import java.io.IOException;\n"
            + "import java.util.ArrayList;\n"
            + "import java.util.List;\n"
            + "import java.util.logging.Level;\n"
            + "import java.util.logging.Logger;\n"
            + "import javax.sound.midi.MidiChannel;\n"
            + "import javax.sound.midi.MidiSystem;\n"
            + "import javax.sound.midi.MidiUnavailableException;\n"
            + "import javax.sound.midi.Synthesizer;\n"
            + "import javax.swing.JButton;\n"
            + "import javax.swing.JFrame;\n"
            + "import javax.swing.JPanel;\n"

            + "public class SongPlayer {\n"

            + "    static List<Integer> octaves = new ArrayList();\n"
            + "    static List<String> pitches = new ArrayList();\n"
            + "    static List<Integer> values = new ArrayList();\n"
            + "    \n"
            + "    public static void main(String[] args) throws IOException{\n"
            + "        JFrame frame = new JFrame(\"Song Player\");                \n"
            + "        JPanel pane = new JPanel();                         \n"
            + "        JButton button1 = new JButton(\"Play Song\");            \n"
            + "        frame.getContentPane().add(pane);                   \n"
            + "        pane.add(button1);                                     \n"
            + "        frame.pack();                                       \n"
            + "        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);\n"
            + "        frame.show();\n"
            + "        \n"
            + "        button1.addActionListener(new ActionListener() {\n"
            + "            @Override\n"
            + "            public void actionPerformed(ActionEvent e) {\n"
            + "                playSong();\n"
            + "            }\n"
            + "            \n"
            + "        \n"
            + "        });\n"
            + "                \n"
            + "    }\n"
            + "    \n"
            + "    public static void playSong(){\n"
            + "        final MidiHandler midiHandler = new MidiHandler(400);\n"
            + "        parseDSL(\"";
    
    public static String SourceCodeBottom = ""
            + "\");\n"
            + "        int i;\n"
            + "        for(i=0;i<pitches.size();i++){\n"
            + "            try {\n"
            + "                midiHandler.play(octaves.get(i), pitches.get(i), values.get(i));\n"
            + "            } catch (InterruptedException ex) {\n"
            + "                Logger.getLogger(SongPlayer.class.getName()).log(Level.SEVERE, null, ex);\n"
            + "            }\n"
            + "        }\n"
            + "    }\n"
            + "    \n"
            + "    static class MidiHandler {\n"
            + "        Synthesizer syn = null;\n"
            + "        MidiChannel[] mc = null;\n"
            + "        int tempo = 1;\n"
            + "        public MidiHandler(int mTempo){\n"
            + "            try {\n"
            + "                syn = MidiSystem.getSynthesizer();\n"
            + "                syn.open();\n"
            + "                mc = syn.getChannels();\n"
            + "                tempo = mTempo;\n"
            + "            } catch (MidiUnavailableException ex) {\n"
            + "                Logger.getLogger(MidiHandler.class.getName()).log(Level.SEVERE, null, ex);\n"
            + "            }\n"
            + "        }\n"
            + "\n"
            + "        public void play(int octave, String pitch, int value) throws InterruptedException{\n"
            + "            int noteNumber = octave*12;\n"
            + "            switch(pitch){\n"
            + "                case \"C\":\n"
            + "                    break;\n"
            + "                case \"C#\":\n"
            + "                    noteNumber+=1;\n"
            + "                    break;\n"
            + "                case \"D\":\n"
            + "                    noteNumber+=2;\n"
            + "                    break;\n"
            + "                case \"D#\":\n"
            + "                    noteNumber+=3;\n"
            + "                    break;\n"
            + "                case \"E\":\n"
            + "                    noteNumber+=4;\n"
            + "                    break;\n"
            + "                case \"F\":\n"
            + "                    noteNumber+=5;\n"
            + "                    break;\n"
            + "                case \"F#\":\n"
            + "                    noteNumber+=6;\n"
            + "                    break;\n"
            + "                case \"G\":\n"
            + "                    noteNumber+=7;\n"
            + "                    break;\n"
            + "                case \"G#\":\n"
            + "                    noteNumber+=8;\n"
            + "                    break;\n"
            + "                case \"A\":\n"
            + "                    noteNumber+=9;\n"
            + "                    break;\n"
            + "                case \"A#\":\n"
            + "                    noteNumber+=10;\n"
            + "                    break;\n"
            + "                case \"B\":\n"
            + "                    noteNumber+=11;                \n"
            + "                    break;\n"
            + "            }\n"
            + "            if(!\"R\".equals(pitch)){\n"
            + "                mc[1].noteOn(noteNumber,600);\n"
            + "                Thread.sleep(50000*value/tempo);\n"
            + "                mc[1].noteOff(noteNumber);\n"
            + "            }else{\n"
            + "                Thread.sleep(50000*value/tempo);\n"
            + "            }\n"
            + "\n"
            + "\n"
            + "        }\n"
            + "\n"
            + "    }\n"
            + "\n"
            + "    public static void parseDSL(String dslText) {\n"
            + "        for(String note : dslText.split(\" \")){\n"
            + "            if(note.contains(\"-\")){\n"
            + "                octaves.add(Integer.parseInt(note.split(\"-\")[0]));\n"
            + "                pitches.add(note.split(\"-\")[1]);\n"
            + "                values.add(Integer.parseInt(note.split(\"-\")[2]));\n"
            + "            }   \n"
            + "        }\n"
            + "    }\n"
            + "    \n"
            + "}\n";
}
