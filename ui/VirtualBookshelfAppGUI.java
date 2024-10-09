package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// begins execution of the app
public class VirtualBookshelfAppGUI extends JFrame implements ActionListener {

    public static final Color VERY_DARK_GREY = new Color(51,51,51);
    JFrame welcomeFrame;
    JPanel welcomePanel;
    JLabel message;
    JLabel option;
    JButton yesButton;
    JButton noButton;
    private UserOptions userOptions;


    // calls VirtualBookshelfAppGUI
    public static void main(String[] args) {
        VirtualBookshelfAppGUI gui = new VirtualBookshelfAppGUI();
    }

    // EFFECTS: creates the welcome display page for the user
    public VirtualBookshelfAppGUI() {
        welcomeFrame = new JFrame();
        welcomePanel = new JPanel();

        welcomeFrame.setSize(500,500);
        welcomeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        welcomeFrame.add(welcomePanel);

        welcomePanel.setLayout(null);

        welcomeMessage();

        yesButton = new JButton("Yes");
        yesButton.setBounds(170,230,80,25);
        welcomePanel.add(yesButton);
        yesButton.addActionListener(this);

        noButton = new JButton("No");
        noButton.setBounds(260,230,80,25);
        welcomePanel.add(noButton);
        noButton.addActionListener(this);

        welcomePanel.setBackground(VERY_DARK_GREY);
        welcomeFrame.setVisible(true);
    }

    // EFFECTS: creates the welcome labels for the display page
    private void welcomeMessage() {
        message = new JLabel("Welcome to your Virtual Bookshelf!");
        message.setBounds(145,150,300,65);
        message.setForeground(Color.WHITE);
        welcomePanel.add(message);

        option = new JLabel("Would you like to continue?");
        option.setBounds(168,170,300,65);
        option.setForeground(Color.WHITE);
        welcomePanel.add(option);
    }


    // EFFECTS: if the yes button is clicked, then it opens a new user options display, otherwise it with the app
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(yesButton)) {
            welcomeFrame.dispose();
            userOptions = new UserOptions();
            userOptions.setVisible(true);
        }
        if (e.getSource().equals(noButton)) {
            welcomeFrame.dispose();
        }
    }
}


