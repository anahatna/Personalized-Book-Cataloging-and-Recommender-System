package ui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// creates a list of book recommendations based on user's recent read
public class BookRecs extends JFrame implements ActionListener {
    JFrame frame;
    JPanel panel;


    public BookRecs() {
        frame = new JFrame();
        panel = new JPanel();

        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // stub;
    }

}
