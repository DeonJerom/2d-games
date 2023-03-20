import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

/*
 * 
 * Author: Deon Jerom
 * 
 * Date: 15 November, 2022
 * 
 * Description: Additional Enhancment for the Original game. User is prompted with menu to guide them. 
 * 							If the user wish to change the speed of the rocket, they have to press settings. If they wish to go to menu while playing
 * 							, All they have to do is press enter. Their mission to complete 3 sets of asteroids and reach Earth and land safely.
 * 							Rocket are able to shoot at asteroids by presseing e. 
 * 
 * 
 * Additional Sources - https://docs.oracle.com/javase/7/docs/api/javax/swing/BorderFactory.html - To Change Border Style
 * 										- https://docs.oracle.com/javase/7/docs/api/javax/swing/JButton.html - Buttons to receive user input
 *  									- https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html - JPanel to contain components like JButtons and JTextArea
 * 										- https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextArea.html - Display Text to User
 *  									- https://docs.oracle.com/javase/7/docs/api/javax/swing/Timer.html - Used to animate most of the images which changes locations every 10 milliseconds
 *  									- https://docs.oracle.com/javase/7/docs/api/java/awt/Image.html - Used Image instead of ImageIcon so I can change the size o fthe image
 *  									- https://docs.oracle.com/javase/7/docs/api/java/awt/event/ActionEvent.html - To receive user  actions
 *  									- https://www.javatpoint.com/java-actionlistener
 *    									- https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html - In order to receive keyboard inputs by the user
 * 										- https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html - Used to store Asteroids locations and Bullet locations
 *   									- https://docs.oracle.com/javase/8/docs/api/java/util/Random.html - In order to randomly get values for asteroid locations
 *  
 */

public class ArcadeGame extends JPanel implements ActionListener, KeyListener {

	//globally declared component so that all non main method have access to it
    JFrame frame;
    Timer timer;
    
    // setting the instance to get the image for the rocket for different directions
    Image rocket = new ImageIcon("rocket.png").getImage();
    Image rocketUp = new ImageIcon("rocket.png").getImage();
    Image rocketDown = new ImageIcon("rocketDown.png").getImage();
    Image rocketRight = new ImageIcon("rocketRight.png").getImage();
    Image rocketLeft = new ImageIcon("rocketLeft.png").getImage();
    
    int rSpeed = 10; // speed of the rocket 

    // x and y coordinate of the rocket
    int x = 160;
    int y = 400;

    // image used in creation of the background
    Image img = new ImageIcon("background2.jpg").getImage();
    Image rotate = new ImageIcon("bgRotate.jpg").getImage();
    // variable to control the y coordinate of the 2nd bg
    int by2 = 0;
    int ySpeed2 = 10;
    // variables to control the y coordinate of the bg
    int by = 0;
    int ySpeed = 10;
    int y2 = 0;
    /// Controlling the asteroids
    Image asteroid = new ImageIcon("asteroid.png").getImage();
    
    //two arraylists to contain future asteroids and to remove asteroids that have collided
    ArrayList<Integer> xValues = new ArrayList<>();
    ArrayList<Integer> yValues = new ArrayList<>();

    //variables for bullets
    Image bullet = new ImageIcon("bullet.png").getImage();
    ArrayList<Integer> xBullets = new ArrayList<>();
    ArrayList<Integer> yBullets = new ArrayList<>();
    
    // Earth displaying details
    Image Earth = new ImageIcon("earth3.gif").getImage();
    int EarthCounter = 0;
    int yEarth = -100;

    //Username that user gives in the starting program
    String username;

    
    /**
     * Construtor in which the main components is build
     */
    public ArcadeGame() {
        
        //username sent as an argument is received and stored

        // //Friendly message to the user Informing them about what's happening
        // JOptionPane.showMessageDialog(null, username+" , you have been transported to outer space by unknown "+
        //                             "\ncreatures. Your Mission is to safely get back to earth by dodging " + 
        //                             "\nincoming asteroids and shooting them by pressing 'e' ");    


        getSettings();
        
        //First set of asteroids that will be shown in the frame are added to arrayLists
        xValues.add(50);
        xValues.add(180);
        xValues.add(250);
        xValues.add(310);
        xValues.add(360);

        yValues.add(-150);
        yValues.add(200);
        yValues.add(-300);
        yValues.add(-350);
        yValues.add(-80);

        // creates a Frame instance to contain other swing component
        frame = new JFrame();
        // changes the size of the frame
        frame.setSize(450, 580);
        // frame would be centered in users display
        frame.setLocationRelativeTo(null);
        // In order to close the frame
        frame.setDefaultCloseOperation(3);
        // User is not allowed to resize the frame
        frame.setResizable(true);
        // keylistener event added to the panel
        frame.addKeyListener(this);
        // panel component is added to Jframe
        frame.add(this);
        // sets the frame visible
        
        // Timer is used to call an action every 10 milliseconds which then performs certain activities
        timer = new Timer(10, this);

    }

    public void paint(Graphics g) {


        { // everything inside here makes the background loop and draaw
            if (y2 == 630) {
                by = -640;
            }

            if (y2 == 1260) {
                by2 = 0;
                y2 = 0;

            }
            // First background layer
            g.drawImage(img, 0, by, 450, 650, null);

            // Second background layer
            g.drawImage(rotate, 0, -640 + by2, 450, 640, null);
        }

        {// Displaying bullets
            
            if(!xBullets.isEmpty() && !yBullets.isEmpty()){
                for(int i=0;i<xBullets.size();i++){
                    g.drawImage(bullet, xBullets.get(i), yBullets.get(i), 25, 25, null);
                }
            }

        }

        { // this section is for drawing and controlling the rocket

            g.drawImage(rocket, x, y, 100, 100, null);

        }
        
        {// Displaying the asteroids
            if(!xValues.isEmpty()){
                for(int i=0;i<xValues.size();i++){
                    g.drawImage(asteroid, xValues.get(i), yValues.get(i), 50, 50, null);
                }
            }
            
        }

        {// Displaying the Earth After the user finishes 3 sets of Asteroids
            if(EarthCounter == 3){
                g.drawImage(Earth, 275, yEarth, 150, 150, null);                
            }
            
            //Stops the looped background when Earth stops moving
            if(yEarth==30){
                ySpeed = 0;
                ySpeed2 = 0;
            }

            // Program checks if user rocket entered the vicinity of Earth 
            if(yEarth+130 > y){
                int difference = 275 - x;
                if(55>difference){
                    frame.dispose();
                    new RocketLaunch(true);
                }
                
            }

        }

    }

    
    //Global Declared Component so that ActionListener could detect if user presses any button
    JButton Play;
    JButton Setting;
    JButton Help;
    JPanel Menu;
    JFrame frame2;

    String play = "Play";

    JFrame Settings;
    JButton returnMenu;

    public void getSettings(){

        //creates a Frame instance to contain other swing component
        frame2  = new JFrame();
        //changes the size of the frame
        frame2.setSize(220,230);
        //frame would be centered in users display
        frame2.setLocationRelativeTo(null);
        //In order to close the frame
        frame2.setDefaultCloseOperation(3);
        //User is not allowed to resize the frame
        frame2.setResizable(false);
        //panel component is added to Jframe


        Menu = new JPanel();
        //a new panel instance is created to use as settings
        Menu.setLayout(null);
        //layout of the menu is disabled
        Menu.setBackground(Color.GRAY);
        //changes the color of the background
        Menu.setBounds(120,150,200,200);
        //changes the location and size of the panel
        Menu.setBorder(BorderFactory.createLineBorder(Color.BLUE,3,true));
        //sets a border to the panel
        frame2.add(Menu);
        //panel settings is added to frame

        /*
         * Buttons for Guiding the user are created, such for starting the game, help guide and settings
         * Used set Bounds to change the location of where the button is placed and there size are changed
         * Removed button hovering effect by using set Focusable false
         * Border of the button is changed using BorderFactory and setBorder
         * An ActionListener is added to each button to receive for the program to know which button has been clicked
         * These buttons are then added to a panel to be visible
         * 
         */

        Play = new JButton(play);
        Play.setBounds(50,25,100,40);
        Play.setFocusable(false);
        Play.setBorder(BorderFactory.createLineBorder(Color.BLUE,3,true));
        Play.addActionListener(this);
        Play.setToolTipText("Play the Game");
        Menu.add(Play);


        Setting = new JButton("Settings");
        Setting.setBounds(50,75,100,40);
        Setting.setFocusable(false);
        Setting.setBorder(BorderFactory.createLineBorder(Color.BLUE,3,true));
        Setting.setToolTipText("Rocket Settings");
        Setting.addActionListener(this);
        Menu.add(Setting);


        Help = new JButton("Help Me");
        Help.setBounds(50,125,100,40);
        Help.setFocusable(false);
        Help.setBorder(BorderFactory.createLineBorder(Color.BLUE,3,true));
        Help.setToolTipText("Controls");
        Help.addActionListener(this);
        Menu.add(Help);

        
        //creates a Frame instance to contain other swing component
        Settings = new JFrame();
        //changes the size of the frame
        Settings.setSize(225,260);
        //frame would be centered in users display
        Settings.setLocationRelativeTo(null);
        //In order to close the frame
        Settings.setDefaultCloseOperation(3);
        //User is not allowed to resize the frame
        Settings.setResizable(false);
        // Layout is set to null so that the program is able to place any component anywhere
        Settings.setLayout(null);


        JTextArea text = new JTextArea();
        text.setBackground(Color.GRAY);
        //changes the color of the background
        text.setBounds(0,0,210,190);
        //changes the location and size of the panel
        text.setBorder(BorderFactory.createLineBorder(Color.BLUE,3,true));
        //sets a border to the panel
        text.setFont(new Font("Times New Roman",Font.BOLD,15));
        // Font style and size changed using set Font(new Font()) 
        text.setEditable(false);
        //user can't edit the text

        //Pre made text are added to TextArea for user to see
        text.append("              OBJECTIVE");
        text.append("\n Get Back to Earth By Dodging \n Asteroids and shooting them");
        text.append("\n----------------------------------------------");

        text.append("\n              CONTROLS");
        text.append("\n  Press 'w'/↑ - move forward");
        text.append("\n  Press 's'/↓ - move downward");
        text.append("\n  Press 'a'/← - move left");
        text.append("\n  Press 'd'/→ - move right");
        text.append("\n  Press 'e'/1 - shoot");
        //text area is added to JFrame settings
        Settings.add(text);


        /*
         * A Button is created so that user could back to Menu
         * The Location and size has been changed using setBounds
         * Then ActionListener is added so Program could detect when user pressed it
         * Finally It's added to Frame settings
         */

        returnMenu = new JButton("Return");
        returnMenu.setBounds(65,195,80,25);
        returnMenu.addActionListener(this);
        Settings.add(returnMenu);


        frame2.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {

        // If the source of action clicked is Play Button
        if(e.getSource()==Play){
            timer.start(); // Timer starts in order for the images to move
            frame2.dispose(); // The frame containing the settings and help are removed
            // sets the visiblity of the panel
            frame.setVisible(true); // The Main Frame is set to Visible when user presses Play
        }

        //If user presses the return Butten, the frame containing the controls is closed and settings frame is setvisible
        if(e.getSource()==returnMenu){
            Settings.dispose();
            frame2.setVisible(true);
        }

        // Help guide panel opens if user presses help
        if(e.getSource()==Help){
            Settings.setVisible(true);
            frame2.setVisible(false);
        }

        //If user wanted to change the setting of the rocket
        // I used IO class to utilize a new method that I created to ask user for a selection
        // If user exits without picking anything, a default value is assigned
        // IO.combo first string splices into an array then adds into a combobox
        // A return value is sent back as a result when user selects an option
        if(e.getSource()==Setting){
            IO.combo("Slow,Normal,Fast,Very Fast");
            String choice = IO.getChoice("Rocket Speed");
            if(choice.equals("Slow")){
                rSpeed = 5;
            }else if(choice.equals("Normal")){
                rSpeed =  10;
            }else if(choice.equals("Fast")){
                rSpeed =  20;
            }else if(choice.equals("Very Fast")){
                rSpeed =  30;
            }

        }

        by = by + ySpeed; // y coordinate of the first background increase, causing it to go down

        by2 = by2 + ySpeed2; // y coordinate of the second background increases, causing it to go down

        y2 = y2 + ySpeed2; // used to time exactly when to loop a background

        // The y position of Earth is changed when three sets of Asteroids are done
        if(EarthCounter==3 && yEarth<30){
            yEarth++;
        }
        
        // y coordinate of the asteroids contained inside the ArrayList is increased by 2 to make it seem like it going down 
        for(int i=0;i<yValues.size();i++){
            yValues.set(i, yValues.get(i) + 2);
        }

        /*
         * In order to check if the rocket collides with the Asteroids
         * The program first checks if y coordinate of the asteroid plus 40 is greater than the y coordinate of Rocket
         * Then checks If the difference between the x coordinate of the asteroid  and x of the rocket
         * If the difference is between the given domain then an Collision has happened between the rocket and the asteroid
         * So the xValue and yValue of that asteroid is removed which then removes the asteroid from the frame
         */
        for(int i = 0; i < xValues.size(); i++){
            if((yValues.get(i) + 40 > y) && y > yValues.get(i)){
                int difference = xValues.get(i) - x;
                if(70>difference && difference>-20){
                    yValues.remove(i);
                    xValues.remove(i);
                }
            }   
        }

        // Asteroids are removed from the arraylists if their Y coordinate is beyond the frame height
        for(int i=0;i<yValues.size();i++){
            if(yValues.get(i) > 550){
                yValues.remove(i);
                xValues.remove(i);
            }
        }

        // when the arrayList containing the values for the asteroids are empty, More asteroids are created by calling a method
        if(yValues.isEmpty() && EarthCounter<3){
            EarthCounter++;
            getAsteroids();
        }
        
        /*
         * If the bullets arrayList is not empty, the y coordinate of the bullet is decreased 
         * in order to look like to moving towards the asteroids
         */
        if(!xBullets.isEmpty()) {
            for(int i=0;i<yBullets.size();i++){
                yBullets.set(i, yBullets.get(i)-10);
            }
        }

        // If the bullet is off from the jframe, then it's x and y values are removed from the arrayList to save memory
        for(int i=0;i<yBullets.size();i++){
            if(yBullets.get(i) < -10){
                yBullets.remove(i);
                xBullets.remove(i);
            }
        }


        //checking if the bullet collides with the asteroids
        // If a bullet collides with an asteroid, the asteroids x and y postion from the arrayLists are removed
        // As well as the position of the bullets
        
        for(int i=0;i<yValues.size();i++){
            for(int m=0;m<yBullets.size();m++){
                if(yValues.get(i)+40 > yBullets.get(m)){                
                    int difference = xValues.get(i) - xBullets.get(m);
                    if(12>difference && difference>-38){
                        yValues.remove(i);
                        xValues.remove(i);
                        yBullets.remove(m);
                        xBullets.remove(m);
                        break;
                    }
                }
              }
        }

        // Repaints the whole background so that no trail is left behind from the other components
        repaint();
    }
    
    public void keyPressed(KeyEvent e) {
        
        /*
             * When the timer is on, I added key Listeners to get user key inputs so that the rocket could move
             * e.getKeyChar gets the key input pressed by the user and compares to the given controls of the game
             * Using If statements, I have given boundaries to how far the rocket can go, side to side and up and down.
             * When user presses 'w', the y coordinate of the rocket decreases so that it looks like it's going up
             * When user presses 's', the y coordinate of the rocket increases so that it looks like it's going down
             * When user presses 'a', the x coordinate of the rocket decreases so that it looks like it's going left
             * When user presses 'd', the x coordinate of the rocket increases so that it looks like it's going right
        */

        if (timer.isRunning()) {
            
            if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
                if (y > 4) {
                    y = y - rSpeed;
                }
            } else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
                rocket = rocketDown;
                if (y < 440) {
                    y = y + rSpeed;
                }
            } else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
                rocket = rocketLeft;
                if (x > 0) {
                    x = x - rSpeed;
                }
            } else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rocket = rocketRight;
                if (x < 340) {
                    x = x + rSpeed;
                }
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            timer.stop(); // Timer stops which stops the player from continue playing
            play = "Resume"; // Text for continue the game is changed from play to resume
            frame.setVisible(false); // frame containing the game is turned off 
            getSettings(); // Program shows user the settings by a method
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {

        // rocket direction is switched 
        rocket = rocketUp; 

        // If the timer is running, when user press and releases 'e' the rocket shoots bullets 
        if(timer.isRunning()){
            if(e.getKeyChar()=='e'){
                shoot();
            }
        }
    }
    
    public void getAsteroids(){

        int value;
        Random random = new Random(); // Random Method Instance is created to be used
        
        //first get new positions for x positions then y values
        xValues.add(40);
        yValues.add(-100);

        /*
         * Using a for loop, The program randomly picks a value between 40 and 450 for the x coordinate of the asteroid
         * Then the program checks If the value is divisible by 10 in order for a better accurate collision detection
         * Then checks If the numbers are quite distant from the previous asteroid, so that the asteroids don't stay in the same place
         * lastly the value is added to  x asteroid ArrayList
         */
        for(int i=0;i<5;i++){
            value = random.nextInt(450)+40;
            if(value%10==0 && (xValues.get(i) - value > 80 || value - xValues.get(i) > 80)){
                xValues.add(value);    
            }else{
                i--;
            }

        }    

        /*
         * Next using another for loop to randomly pick values for the y coordinates of the asteroids
         * Then the program checks If the value is divisible by 10 in order for a better accurate collision detection
         * lastly the value is added to  y asteroid ArrayList
         */

        for(int i=0;i<5;i++){
            value = random.nextInt(150)-350;
            if(value%10==0){
                yValues.add(value);    
            }else{
                i--;
            }

        }     
    }
    public void shoot(){

        //when this method is called, a new bullet is added to the array 
        //the location starts from where the rocket is placed
        xBullets.add(x+38);
        yBullets.add(y+10);
        
    }

}
