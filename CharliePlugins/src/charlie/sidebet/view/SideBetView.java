/*
 Copyright (c) 2014 Ron Coleman

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package charlie.sidebet.view;

import charlie.audio.Effect;
import charlie.audio.SoundFactory;
import charlie.card.Hid;
import charlie.plugin.ISideBetView;
import charlie.view.sprite.Chip;
import charlie.util.Constant;
import charlie.view.AMoneyManager;
import charlie.view.sprite.AtStakeSprite;

import charlie.view.sprite.ChipButton;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the side bet view
 * @author Ron Coleman, Ph.D.
 */
public class SideBetView implements ISideBetView {
    private final Logger LOG = LoggerFactory.getLogger(SideBetView.class);
    
    public final static int X = 400;
    public final static int Y = 200;
    public final static int DIAMETER = 50;
    
    public final static int PLACE_COIN_X = X + AtStakeSprite.DIAMETER + 10;
    public final static int PLACE_COIN_Y = Y + AtStakeSprite.DIAMETER / 4;
    protected AtStakeSprite wager = new AtStakeSprite(X-DIAMETER/2, Y-DIAMETER/2-5,50);
    
    protected String text;
    protected Font sidebetFont = new Font("Arial", Font.BOLD, 18);
    protected Color looseColorBackGround = new Color(250,58,5);
    protected Color looseColorForeGround = Color.WHITE;
    protected Color winColorBackGround = new Color(116,255,4);
    protected Color winColorForeGround = Color.BLACK;
    protected Color pushColorBackGround = Color.CYAN;
    protected Color pushColorForeGround = Color.BLACK;
    protected Color background;
    protected Color foreground;
    
    protected List<Chip> chips = new ArrayList<>();
    protected Integer[] amounts = {100, 25, 5};
    
    protected Font font = new Font("Arial", Font.BOLD, 18);
    protected BasicStroke stroke = new BasicStroke(3);
    
    // See http://docs.oracle.com/javase/tutorial/2d/geometry/strokeandfill.html
    protected float dash1[] = {10.0f};
    protected BasicStroke dashed
            = new BasicStroke(3.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, dash1, 0.0f);   

    protected List<ChipButton> buttons;
    protected int amt = 0;
    public final int width;
    private double finalBet;
    private boolean startGame;
    protected AMoneyManager moneyManager;
    protected final static String[] FILES = {"chip-100-1.png", "chip-25-1.png", "chip-5-1.png"};
    protected Random random = new Random();

    /**
     * Constructor
     */
    public SideBetView() {
        LOG.info("side bet view constructed");
        ImageIcon icon = new ImageIcon(Constant.DIR_IMGS + FILES[0]);
        
        Image image = icon.getImage();
        this.width = image.getWidth(null);
    }
    
    /**
     * Sets the money manager.
     * @param moneyManager 
     */
    @Override
    public void setMoneyManager(AMoneyManager moneyManager) {
        this.moneyManager = moneyManager;
        this.buttons = moneyManager.getButtons();
    }
    
    /**
     * Registers a click for the side bet.
     * @param x X coordinate
     * @param y Y coordinate
     */
    @Override
    public void click(int x, int y) {
        int oldAmt = amt;
        
        // Test if any chip button has been pressed.
        for (ChipButton button: buttons) {
            if (button.isPressed(x, y)) {
                amt += button.getAmt();
                LOG.info("A. side bet amount "+button.getAmt()+" updated new amt = "+amt);
                
                //calculates random chip placement
                int n = chips.size();
                int placeX = PLACE_COIN_X + n * width/3 + random.nextInt(10)-10;
                int placeY = PLACE_COIN_Y + random.nextInt(5)-5;
                
                //places wagered chips in the stake area
                Chip chip = new Chip(button.getImage(),placeX-20,placeY-34,button.getAmt());
                chips.add(chip);
                
                SoundFactory.play(Effect.CHIPS_IN);
                
            } 
        }
        
        //Tests if stake area was clicked to be cleared.
        //Clears stake area dollar amount and chips.
        if (this.wager.isPressed(x, y)) {
            if (oldAmt == amt) {
                amt = 0;
                LOG.info("B. side bet amount cleared");
                //clears chips and prompts win or loose banner
                chips.clear();
                
                SoundFactory.play(Effect.CHIPS_OUT);
              
            }
        }
    }

    /**
     * Informs view the game is over and it's time to update the bankroll for the hand.
     * @param hid Hand id
     */
    @Override
    public void ending(Hid hid) {
        double bet = hid.getSideAmt();
        this.startGame = false;
        this.finalBet = bet;
        
        if(bet == 0)
            return;

        LOG.info("side bet outcome = "+bet);
        
        // Update the bankroll
        moneyManager.increase(bet);
        
        LOG.info("new bankroll = "+moneyManager.getBankroll());
    }

    /**
     * Informs view the game is starting
     */
    @Override
    public void starting() {
        this.startGame = true;
    }

    /**
     * Gets the side bet amount.
     * @return Bet amount
     */
    @Override
    public Integer getAmt() {
        return amt;
    }

    /**
     * Updates the view
     */
    @Override
    public void update() {
    }

    /**
     * Renders the view
     * @param g Graphics context
     */
    @Override
    public void render(Graphics2D g) {
        // Draw the at-stake place on the table
        g.setColor(Color.BLACK); 
        g.setStroke(dashed);
        g.drawOval(X-DIAMETER/2, Y-DIAMETER/2 - 6, DIAMETER, DIAMETER);
        
        //sets at-stake amount color and font
        g.setFont(font);
        g.setColor(Color.WHITE);
        String text = amt + "";
        FontMetrics fm = g.getFontMetrics(font);
        
        //centers the at-stake dollar amount in the stake area
        int x = X-DIAMETER/2 + DIAMETER/2 - fm.charsWidth(text.toCharArray(), 0, text.length())/2;
        int y = Y-DIAMETER/2 - 6 + DIAMETER/2 + fm.getHeight()/4;
        
        // Draw the at-stake amount
        g.drawString(amt+"", x, y);
        
        //Draws sidebet descriptions on table
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString("Super 7 pays 3:1", X+75, Y+100);
        g.drawString("Royal Match pays 25:1", X+75, Y+115);
        g.drawString("Exactly 13 pays 3:1", X+75, Y+130);
        
        //Draws the at-stake chips on table
        for (int i=0; i < chips.size(); i++) {
            Chip chip = chips.get(i);
            chip.render(g);
        }
        
        //Draws win/loose banner at end-game
        //over the stake chips if the side bet is made
        if (this.finalBet != 0 && this.startGame != true) {
            //set win banner
            if (this.finalBet >0) {
               text = " WIN !";
               background = winColorBackGround;
               foreground = winColorForeGround;
            }
             //set lose banner
            else {
               text = " LOSE !";
               background = looseColorBackGround;
               foreground = looseColorForeGround;
            }
            
            int n = chips.size() - 1;
            
            //keeps the banner centerd over stake chips
            int placeX = ((X +(AtStakeSprite.DIAMETER/2)+5)+(X +(AtStakeSprite.DIAMETER/2)+5+(n*width/3)))/2;
            int placeY = y;
            
            //determines width and height of the win/loose banner characters
            fm = g.getFontMetrics(font);
            int w = fm.charsWidth(text.toCharArray(), 0, text.length()); 
            int h = fm.getHeight();
            
            //Draws banner background
            g.setColor(background);
            g.fillRoundRect(placeX, placeY-h+5, w, h, 5, 5);
            
            //Sets banner text
            g.setColor(foreground);
            g.setFont(font);
            
            //Draws banner text
            g.drawString(text, placeX, placeY);
            
        }
    }
}