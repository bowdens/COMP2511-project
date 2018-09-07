import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

public class ToolBar extends JFrame {

    public ToolBar(){}

    public void display(){
        final JToolBar toolBar = new JToolBar();

        //Create the popup menu for difficulty level
        final JPopupMenu popup = new JPopupMenu();
        popup.add(new JMenuItem(new AbstractAction("Easy") {
          public void actionPerformed(ActionEvent e) {
              //JOptionPane.showMessageDialog(frame, "You selected Easy difficulty");
          }
        }));
        popup.add(new JMenuItem(new AbstractAction("Medium") {
          public void actionPerformed(ActionEvent e) {
             // JOptionPane.showMessageDialog(frame, "You selected Medium difficulty");
          }
        }));

        popup.add(new JMenuItem(new AbstractAction("Hard") {
          public void actionPerformed(ActionEvent e) {
              //JOptionPane.showMessageDialog(frame, "You selected Hard difficulty");
          }
        }));

        // create popup menu for options menu
        final JPopupMenu popup2 = new JPopupMenu();
        popup2.add(new JMenuItem(new AbstractAction("Option1") {
          public void actionPerformed(ActionEvent e) {
              //JOptionPane.showMessageDialog(frame, "Option 1 selected");
          }
        }));
        popup2.add(new JMenuItem(new AbstractAction("Option2") {
          public void actionPerformed(ActionEvent e) {
              //JOptionPane.showMessageDialog(frame, "Option 2 selected");
          }
        }));

        popup2.add(new JMenuItem(new AbstractAction("Option3") {
          public void actionPerformed(ActionEvent e) {
             // JOptionPane.showMessageDialog(frame, "Option 3 selected");
          }
        }));


        final JButton difficultyButton = new JButton("Select Difficulty");
        difficultyButton.addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
              popup.show(e.getComponent(), e.getX(), e.getY());
          }
        });


        final JButton optionsButton = new JButton("Play Game");
        optionsButton.addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            InteractivePuzzleGame puzzle = new InteractivePuzzleGame();
            Thread t = new Thread(puzzle);
            t.start();
            dispose();
              //popup2.show(e.getComponent(), e.getX(), e.getY());
          }
        });

        toolBar.add(difficultyButton);
        toolBar.add(optionsButton);

        getContentPane().add(toolBar, BorderLayout.NORTH);
        pack();

    }
}
