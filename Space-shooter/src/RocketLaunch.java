import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * Author: Deon Jerom
 * 
 * Date: 15 November, 2022
 * 
 * Description: A Simulation of a SpaceShip travelling through the sky is created using Graphics. 
 * 						First User will be prompted to enter their name and their choice of speed for the rocket.
 * 						First the rocket will go to top left of the screen, then continue going all the way to top right,
 * 						Then the rocket will go down until a portal appears which then the rocket switchs direction and goes inside of it.
 * 						After user controls their tast is their other game, The rocket exits the portal and continues to go left and down until it reaches it's starting point.
 * 
 * Additional Sources - https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html , used to change the font style and size of a text
 * 
 */

public class RocketLaunch extends JFrame {

    // Variables for userSpeed and Name is intialized as Static in order to be user
    // inside a
    // static method such as the main method
    static int userSpeed;
    static String userName;

    // setting the instance to get the image for the background
    Image background = new ImageIcon("LandScape.jpg").getImage();

    // setting the instance to get the image for the rocket
    Image rocket;
    Image rocketUp = new ImageIcon("rocket.png").getImage();
    Image rocketDown = new ImageIcon("rocketDown.png").getImage();
    Image rocketRight = new ImageIcon("rocketRight.png").getImage();
    Image rocketLeft = new ImageIcon("rocketLeft.png").getImage();
    int x = 0;
    int y = 0;
    int xSpeed = 0;
    int ySpeed = userSpeed;

    // these are used to make the rocket go to the portal
    int x1 = 0;
    int xSpeed1 = 0;
    int height = 120;
    int width = 120;
    // getting the image for a portal
    Image portal;
    // boolean to check phase two
    boolean success;

    public static void main(String[] args) {

        // In order to make my code smaller I modified my old combobox reader from IO to
        // support JTextField and ComboBox inside one JOptionPane

        // User choices are added to a Combobox using IO class
        IO.combo("Very Slow,Slow,Normal,Fast,Super Fast");

        // IO Method that is named box String will return a String containing the user
        // name and user speed choice
        // Which are separated by a comma and that return value is then splited into an
        // Array
        String[] value = IO.boxString("User Information").split(",");

        // Checks the index 1 of the array which contains information about user speed
        // choice
        // Speed of the rocket is adjusted according to that.
        if (value[1].equals("Very Slow")) {
            userSpeed = 1;
        } else if (value[1].equals("Slow")) {
            userSpeed = 2;
        } else if (value[1].equals("Normal")) {
            userSpeed = 4;
        } else if (value[1].equals("Fast")) {
            userSpeed = 6;
        } else if (value[1].equals("Super Fast")) {
            userSpeed = 8;
        }

        // In order to avoid the program from running, I made a If Statement to check
        // If Index 0 is basically null, the program would avoid from running
        if (!value[0].equals("")) {
            userName = value[0];
            new RocketLaunch(false);
        }

    }

    public RocketLaunch(boolean success) {

        // this constructor aceepts a boolean as an argument which is then stored inside
        // a boolean varible
        // Which checks whether to run the part where the rocket is landing, This can
        // only happen if
        // the calling method sends true as it's argument and in this case, class Arcade
        // Game sends it after user enters Earth
        this.success = success;

        setSize(850, 500);
        // frame would be centered in users display
        setLocationRelativeTo(null);
        // In order to close the frame
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        // User is not allowed to resize the frame
        setResizable(false);
        // sets the frame visible
        setVisible(true);

        rocket = new ImageIcon("shuttle1.png").getImage();
    }

    public void paint(Graphics g) {

        if (!success) { // This part of the code runs when the argument send is false
            rocket = rocketUp; // When the main program is run first, The rocket is set to go upwards.
            for (int i = 0; i < 3000; i++) {
                // draws the background
                g.drawImage(background, 0, 0, 850, 500, null);

                // drawing the area to display user's name and speed of the rocket
                g.setColor(Color.darkGray); // changes color
                g.fillRect(490, 390, 270, 95);
                g.setColor(Color.LIGHT_GRAY); // changes color
                g.fillRect(500, 400, 250, 75);

                g.setColor(Color.BLACK); // changes color
                g.setFont(new Font("Times New Roman", Font.ITALIC, 20)); // Font is changed using SetFont method which
                                                                         // accepts a 3 arguments, Font style, font size
                                                                         // and bold/italic and other features
                g.drawString("Name: " + userName, 510, 425);
                g.drawString("Speed: " + userSpeed, 510, 450);

                // base of the building
                g.setColor(Color.DARK_GRAY);
                g.fillRect(100, 499, 240, 5);
                g.fillRect(120, 480, 200, 20);

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(135, 460, 170, 20);

                g.setColor(Color.gray);
                g.fillRect(125, 485, 190, 13);

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(145, 300, 150, 170);

                g.setColor(Color.DARK_GRAY);
                g.fillRect(160, 385, 120, 95);

                g.setColor(Color.gray);
                g.fillRect(172, 300, 95, 170);
                g.fillRect(165, 390, 110, 90);

                // drawing the the US flag
                g.setColor(Color.blue);
                g.fillRect(185, 330, 20, 20);
                g.setColor(Color.RED);
                g.fillRect(205, 330, 20, 2);
                g.fillRect(205, 335, 20, 2);
                g.fillRect(205, 340, 20, 2);
                g.fillRect(205, 345, 20, 2);
                g.fillRect(185, 354, 40, 2);
                g.fillRect(185, 360, 40, 2);

                g.setColor(Color.WHITE);
                for (int j = 0; j < 20; j += 5) {
                    for (int m = 0; m < 20; m += 5) {
                        g.fillOval(186 + m, 330 + j, 2, 2);
                    }
                }

                g.setColor(Color.BLACK);
                g.fillRect(30, 400, 5, 100);
                g.fillRect(30, 430, 15, 5);
                g.fillRect(30, 460, 15, 5);

                // rocket controls
                g.drawImage(rocket, x, 380 + y, width, height, null);

                // When the rocket reaches the top of the frame, The speed of y is set to 0 so
                // nothing is added to y
                // Direction of the rocket is changed to right
                // Now xSpeed of the rocket is set to what user Picked, so that it may go right
                if (y < -375) {
                    ySpeed = 0;
                    rocket = rocketRight;
                    xSpeed = userSpeed;
                }

                // When the rocket reaches top right frame, The speed of x is set to 0 so that
                // the rocket stops moving sideways
                // Direction of the rocket is changed to downwards
                // y speed is set to a negative value so that the rocket goes downwards (-)(-)
                if (x > 730) {
                    ySpeed = -userSpeed;
                    rocket = rocketDown;
                    xSpeed = 0;
                }

                // rocket direction is changed to left
                // portal is intialized and can be seen by the user
                // x Speed is changed to a negative to go left
                // x Speed 1 is used as a pseudo variable to check a if statement later on
                if (x > 730 && y > -240) {
                    ySpeed = 0;
                    rocket = rocketLeft;
                    xSpeed = -userSpeed;
                    xSpeed1 = userSpeed;
                    portal = new ImageIcon("portal.png").getImage();
                }

                /////// SETTINGS
                x = x + xSpeed; // x position of the rocket is changed according to whatever is being added
                y = y - ySpeed; // y position of the rocket is changed according to whatever is being
                                // substracted

                x1 = x1 + xSpeed1;

                // draws the portal
                g.drawImage(portal, 540, 170, 75, 75, null);

                // slows the rocket according to the user's choice
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Best way to check if the rocket reached the portal was to use a different int
                // varible
                // This will break out of the for loop
                if (x1 > 170) {
                    break;
                }

            }
        } else {
            // runs after user is back from dodging Asteroids
            for (int i = 0; i < 3000; i++) {

                xSpeed = -userSpeed; // rocket goes left
                ySpeed = 0; // for now rocket doesn't go up or down

                // draws the background
                g.drawImage(background, 0, 0, 850, 500, null);

                // drawing the area to display user's name and speed of the rocket
                g.setColor(Color.darkGray);
                g.fillRect(490, 390, 270, 95);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(500, 400, 250, 75);

                g.setColor(Color.BLACK);
                g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
                g.drawString("Name: " + userName, 510, 425);
                g.drawString("Speed: " + userSpeed, 510, 450);

                // base of the building
                g.setColor(Color.DARK_GRAY);
                g.fillRect(100, 499, 240, 5);
                g.fillRect(120, 480, 200, 20);

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(135, 460, 170, 20);

                g.setColor(Color.gray);
                g.fillRect(125, 485, 190, 13);

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(145, 300, 150, 170);

                g.setColor(Color.DARK_GRAY);
                g.fillRect(160, 385, 120, 95);

                g.setColor(Color.gray);
                g.fillRect(172, 300, 95, 170);
                g.fillRect(165, 390, 110, 90);

                // drawing the the US flag
                g.setColor(Color.blue);
                g.fillRect(185, 330, 20, 20);
                g.setColor(Color.RED);
                g.fillRect(205, 330, 20, 2);
                g.fillRect(205, 335, 20, 2);
                g.fillRect(205, 340, 20, 2);
                g.fillRect(205, 345, 20, 2);
                g.fillRect(185, 354, 40, 2);
                g.fillRect(185, 360, 40, 2);

                // Drawing white stripes for the american flag using For Loops
                g.setColor(Color.WHITE);
                for (int j = 0; j < 20; j += 5) {
                    for (int m = 0; m < 20; m += 5) {
                        g.fillOval(186 + m, 330 + j, 2, 2);
                    }
                }

                // Draws a stand for the rocket is attach back into
                g.setColor(Color.BLACK);
                g.fillRect(30, 400, 5, 100);
                g.fillRect(30, 430, 15, 5);
                g.fillRect(30, 460, 15, 5);

                // rocket controls
                g.drawImage(rocket, 540 + x, 155 + y, width, height, null);

                // When user is right below the attachment, the direction of the rocket is
                // changed downwards
                // Rocket is stopped from moving left
                // Rocket y is increaded to go downwards
                if (x < -535) {
                    xSpeed = 0;
                    ySpeed = -userSpeed;
                    rocketLeft = rocketUp;

                }

                // When the rocket lands on the attachment, Rocket is stopped from moving
                if (y > 230) {
                    xSpeed = 0;
                    ySpeed = 0;
                }

                /////// SETTINGS
                x = x + xSpeed; // x position of the rocket is changed according to whatever is being added
                y = y - ySpeed; // y position of the rocket is changed according to whatever is being
                // substracted

                rocket = rocketLeft; // Rocket direction is set to left so it starts from going left

                portal = new ImageIcon("portal.png").getImage(); // Portal image is intialized

                g.drawImage(portal, 540, 170, 75, 75, null); // Image of the portal is visible to the user

                // slows the rocket according to the user's choice
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }

        // for the first time, The frame is removed and the arcade game is called
        // But when this method is called from arcadeGame, It only Closes the program
        dispose();
        if (!success) {
            new ArcadeGame();
        }

    }

}
