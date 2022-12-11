import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class Texteditor implements ActionListener {
   JFrame frame;
   JTextArea jTextArea;
   JMenuBar jMenuBar;
   JMenu File,Edit,Close;
   JMenuItem newFile,openFile,saveFile,printFile;
   JMenuItem cut,copy,paste;
   JMenuItem close;

    Texteditor(){
            frame=new JFrame("Simple Text Editor");
            frame.setBounds(0,0,800,1000);
           //UI OF SIMPLE TEXT EDITOR
            jTextArea=new JTextArea("Welcome to the editor");
            frame.add(jTextArea);
            jMenuBar=new JMenuBar();

            File=new JMenu("File");
            Edit=new JMenu("Edit");
            Close=new JMenu("Close");

            jMenuBar.add(File);
            jMenuBar.add(Edit);
            jMenuBar.add(Close);

            frame.setJMenuBar(jMenuBar);

            newFile =new JMenuItem("New");
            newFile.addActionListener(this);
            openFile=new JMenuItem("Open");
            openFile.addActionListener(this);
            saveFile=new JMenuItem("Save");
            saveFile.addActionListener(this);
            printFile=new JMenuItem("Print");
            printFile.addActionListener(this);

            File.add(newFile);
            File.add(openFile);
            File.add(saveFile);
            File.add(printFile);

            cut=new JMenuItem("Cut");
            cut.addActionListener(this);
            copy=new JMenuItem("Copy");
            copy.addActionListener(this);
            paste=new JMenuItem("Paste");
            paste.addActionListener(this);

            Edit.add(cut);
            Edit.add(copy);
            Edit.add(paste);

            close=new JMenuItem("Close");
            close.addActionListener(this);
            Close.add(close);

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
  }
  public static void main(String[] args){
        Texteditor tx=new Texteditor();
  }

    @Override
    public void actionPerformed(ActionEvent e) {
      String s=e.getActionCommand();
      if(s.equals("Copy")){
          jTextArea.copy();
      }else if(s.equals("Cut")){
          jTextArea.cut();
      } else if (s.equals("Paste")) {
          jTextArea.paste();
      } else if (s.equals("Print")) {
          try{
              jTextArea.print();
          }catch (PrinterException ex){
              throw new RuntimeException(ex);
          }
      } else if (s.equals("New")) {
          jTextArea.setText("");
      } else if (s.equals("Close")) {
          frame.setVisible(false);
      } else if (s.equals("Open")) {
          JFileChooser jFileChooser=new JFileChooser("C:");
          int ans=jFileChooser.showOpenDialog(null);
          if(ans==JFileChooser.APPROVE_OPTION){
              File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
              String s1="",s2="";

                 try {
                     //medium to read in the file
                     BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                     //first line stored in s2
                     s2=bufferedReader.readLine();
                     while((s1=bufferedReader.readLine())!=null)
                     {
                         s2+=s1+"\n";
                     }
                     //print the result s2 in the screen
                     jTextArea.setText(s2);
                 } catch (FileNotFoundException ex) {
                     throw new RuntimeException(ex);
                 } catch (IOException ex) {
                     throw new RuntimeException(ex);
                 }
             }
      } else if (s.equals("Save")) {
          //choosing file on which path mentioned
          JFileChooser jFileChooser=new JFileChooser("C:");
          //choose open dialogue
          int ans=jFileChooser.showOpenDialog(null);;
          //if the file is choosed/approved then store its path in flie
          if(ans==jFileChooser.APPROVE_OPTION){
              File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
             //medium to write over the file for user
              BufferedWriter writer=null;
              try{
                  writer = new BufferedWriter(new FileWriter(file,false));
                 //write whatever in the jTextArea
                  writer.write(jTextArea.getText());
                  writer.flush();//writer is empty
                  writer.close();//if not close it then it take too memory
              }
              catch (IOException ex){
                  throw new RuntimeException(ex);
              }
          }

      }
    }
}
