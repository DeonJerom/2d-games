import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class hangManv2 extends JFrame implements ActionListener, KeyListener, MouseListener {

	static String[] images = {"hangMan1.png","hangMan2.png","hangMan3.png",
            "hangMan4.png","hangMan6.png",
            "hangMan5.png","hangMan7.png","hangman8maybe.png"};
	
    static JLabel label;
    
    static int lossCount;
    
    static HashSet<Character> Guess = new HashSet<>();
    
    static JTextField box;
    
    static JPanel endGame;
    
    static JTextField guessBox;
    
    static JTextField pastGuess;
    
    static JLabel accept;
    
    static JButton reset;
    
    static String word;

    static JLabel lose;

    static JLabel GameOVER;
    
    static JLabel win;

    static int wordCount;
	
	
	
	hangManv2(ArrayList<String> Words){
		//Random word choice
        Random random = new Random();
        word =  Words.get(random.nextInt(Words.size()));
        System.out.println(word);

        //basic border settings for buttons and the frame
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white,5));
        Border border1 = BorderFactory.createLineBorder(Color.BLACK,3);

        //settings adjusted for my game HangMan frame
        this.setTitle("HangMan");
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(370,630);

        //this will the panel that we will be putting buttons and images into
        JPanel panel = new JPanel();
        panel.setBounds(45,80,280,369);
        this.add(panel);

        //this panel will be shown when player wins or loses the game
        endGame = new JPanel();
        endGame.setBackground(Color.LIGHT_GRAY);
        endGame.setBounds(20,455,315,120);
        endGame.setBorder(border);
        endGame.setVisible(false);

        //When user loses the program stops and displays the word
        lose = new JLabel("Your word was: "+word);
        lose.setFont(new Font("Calisto MT",Font.ITALIC,20));
        lose.setHorizontalAlignment(JLabel.CENTER);
        lose.setVisible(false);
        endGame.add(lose);

        //Displays GAME OVER
        GameOVER = new JLabel("GAME OVER! or is it?");
        GameOVER.setFont(new Font("Calisto MT",Font.ITALIC,20));
        GameOVER.setVisible(false);
        endGame.add(GameOVER);

        //this displays when users finds the correct word
        win = new JLabel();
        String[] winImage = {"youwin1.png","youwin2.png","youwin3.png"};
        win.setIcon(new ImageIcon(winImage[random.nextInt(winImage.length)]));
        win.setVisible(false);
        endGame.add(win);
        this.add(endGame);

        //now we work on entry
        accept = new JLabel();
        accept.setIcon(new ImageIcon("images-removebg-preview.png"));
        accept.setBounds(0,530,175,42);
        accept.addMouseListener(this);
        this.add(accept);

        //this belongs to text field
        box = new JTextField();
        box.setBounds(100,490,145,30);
        box.setFont(new Font("Calisto M",Font.BOLD,17));
        box.setBorder(border1);
        box.addKeyListener(this);
        this.add(box);

        //this is related to user guesses
        pastGuess = new JTextField();
        pastGuess = new JTextField();
        pastGuess.setBounds(45,25,283,30);
        pastGuess.setEditable(false);
        pastGuess.setFocusable(false);
        pastGuess.setBorder(border1);
        pastGuess.setFont(new Font("Calisto M",Font.BOLD,18));
        pastGuess.setHorizontalAlignment(JTextField.CENTER);
        this.add(pastGuess);

        //this belong to guess box
        guessBox = new JTextField();
        guessBox.setBounds(45,455,283,30);
        guessBox.setEditable(false);
        guessBox.setFocusable(false);
        guessBox.setBorder(border1);
        guessBox.setFont(new Font("Calisto M",Font.BOLD,18));
        guessBox.setHorizontalAlignment(JTextField.CENTER);
        this.add(guessBox);

        //now we work on reset
        reset = new JButton("reset");
        reset.setBounds(205,535,100,30);
        reset.setBorder(border);
        reset.setFont(new Font("Calisto M",Font.BOLD,15));
        reset.setFocusable(false);
        reset.addActionListener(this);
        this.add(reset);

        ///background settings
        JPanel background = new JPanel();
        background.setBackground(Color.BLACK);
        
        JLabel backgroundImage = new JLabel();
        backgroundImage.setOpaque(true);
        background.setBackground(Color.BLACK);
        background.add(backgroundImage);
        this.add(background);

        //label in which the hangMan image is placed at
        label = new JLabel();
        label.setIcon(new ImageIcon(images[0]));
        panel.add(label);
        //panel.setBackground(Color.BLACK);
        this.setVisible(true);

        //this setting is placed for user to to see the word outline
        for(int i=0;i<word.length();i++){
            if(word.charAt(i)==' ') {
                guessBox.setText(guessBox.getText().concat(" "));
            }
            else {
                guessBox.setText(guessBox.getText().concat("- "));
            }
        }
	}
	@Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource()==accept){
                char guess = box.getText().charAt(0);
                String fakeGuess = String.valueOf(guess);
                Guess.add(guess);
                box.setText(null);
                guessBox.setText(null);

                if(!word.contains(fakeGuess)){
                    lossCount++;
                }
                if(log()){
                    reset.setVisible(false);
                    //accept.setVisible(false);
                    guessBox.setVisible(false);
                    box.setVisible(false);
                    win.setVisible(true);
                    endGame.setVisible(true);
                }
                System.out.println(guess);
            }
            if(e.getSource()==reset){
                box.setText(null);
            }
        }catch(StringIndexOutOfBoundsException exception){}

    }
    boolean log(){
        //hang man image changes according to the players wrong guesses
        if(lossCount==1){
            label.setIcon(new ImageIcon(images[1]));
        } else if (lossCount==2) {
            label.setIcon(new ImageIcon(images[2]));
        }else if (lossCount==3) {
            label.setIcon(new ImageIcon(images[3]));
        }else if (lossCount==4) {
            label.setIcon(new ImageIcon(images[4]));
        }else if (lossCount==5) {
            label.setIcon(new ImageIcon(images[5]));
        }else if (lossCount==6) {
            label.setIcon(new ImageIcon(images[6]));
        }else if(lossCount==7){
            label.setIcon(new ImageIcon(images[7]));
            reset.setVisible(false);
            guessBox.setVisible(false);
            box.setVisible(false);
            lose.setVisible(true);
            GameOVER.setVisible(true);
            endGame.setVisible(true);
        }
        pastGuess.setText(null);
        for(Character x: Guess){
            pastGuess.setText(pastGuess.getText().concat(String.valueOf(x+",")));
        }
        wordCount=0;
        for(int i=0;i<word.length();i++) {
            if(Guess.contains(word.charAt(i))) {
                guessBox.setText(guessBox.getText().concat(String.valueOf(word.charAt(i))));
                wordCount++;
            }
            else {
                if(word.charAt(i)==' ') {
                    guessBox.setText(guessBox.getText().concat("  "));
                }
                else {
                    guessBox.setText(guessBox.getText().concat("- "));
                }
            }
        }
        return word.replaceAll(" ","").length()==wordCount;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        try{
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                char guess = box.getText().charAt(0);
                String fakeGuess = String.valueOf(guess);
                Guess.add(guess);
                box.setText(null);
                guessBox.setText(null);

                if(!word.contains(fakeGuess)){
                    lossCount++;
                }
                if(log()){
                    System.out.println("game over");
                    reset.setVisible(false);
                    accept.setVisible(false);
                    //guessBox.setVisible(false);
                    box.setVisible(false);
                    win.setVisible(true);
                    endGame.setVisible(true);
                }
                System.out.println(guess);
            }
        }catch(StringIndexOutOfBoundsException exception){}

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try{
            char guess = box.getText().charAt(0);
            String fakeGuess = String.valueOf(guess);
            Guess.add(guess);
            box.setText(null);
            guessBox.setText(null);
            if(!word.contains(fakeGuess)){
                lossCount++;
            }
            if(log()){
                reset.setVisible(false);
                accept.setVisible(false);
                guessBox.setVisible(false);
                box.setVisible(false);
                win.setVisible(true);
                endGame.setVisible(true);
            }
            System.out.println(guess);
        }catch(StringIndexOutOfBoundsException exception){}
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
