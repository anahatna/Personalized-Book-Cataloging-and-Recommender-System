package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// user options display page
public class UserOptions extends JFrame implements ActionListener {

    public static final Color VERY_DARK_GREY = new Color(51,51,51);
    JButton getBookRecsButton;
    JButton viewLibraryButton;

    // EFFECTS: creates the user option buttons to view library and get book recommendations
    public UserOptions() {

        getBookRecsButton = new JButton("Get Book Recommendations");
        getBookRecsButton.setBounds(130,220,250,90);
        add(getBookRecsButton);
        getBookRecsButton.addActionListener(this);

        viewLibraryButton = new JButton("View My Library");
        viewLibraryButton.setBounds(130,120,250,90);
        add(viewLibraryButton);
        viewLibraryButton.addActionListener(this);

        setLayout(null);
        setSize(500,500);
        this.getContentPane().setBackground(VERY_DARK_GREY);
        setVisible(true);
    }

    // EFFECTS: handles the cases where the buttons are clicked:
    //          if the getBookRecsButton is clicked, open the bookRecs display and dispose the current frame;
    //          if the viewLibraryButton  is clicked, open the ViewLibrary display and dispose the current frame
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(getBookRecsButton)) {
            dispose();
            BookRecs bookRecs = new BookRecs();
        }
        if (e.getSource().equals(viewLibraryButton)) {
            dispose();
            ViewLibrary viewLibrary = new ViewLibrary();
        }
    }
}



