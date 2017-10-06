/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.bot.server;

import charlie.advisor.BasicStrategy;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Dealer;
import charlie.dealer.Seat;
import charlie.plugin.IBot;
import charlie.util.Play;
import java.util.List;
import java.util.Random;

/**
 *
 * @author TSM
 */
public class Huey  implements IBot,Runnable {
    
      protected Hand myHand;
    protected Hand dealerHand;
    protected Dealer dealer;
    protected Seat mine;
    protected final int RESPONSE_TIME = 7;
    Random random = new Random();
    
    @Override
    public Hand getHand() {
        
        return myHand;
        
    }

    @Override
    public void setDealer(Dealer dealer) {
        
        this.dealer = dealer;
        
    }

    @Override
    public void sit(Seat seat) {
        
        this.mine = seat;
        Hid hid = new Hid(seat);
        Hand hand = new Hand(hid);
        this.myHand = hand;
        
    }

    @Override
    public void startGame(List<Hid> hids, int shoeSize) {
        
        Seat seat = Seat.NONE;
        for (int number = 0; number < hids.size(); number++){
            if (hids.get(number).getSeat() == Seat.DEALER) {
                seat = hids.get(number).getSeat();
            }
        }
        Hand hueyHand = new Hand(new Hid(seat));
        dealerHand = hueyHand;
            
    }

    @Override
    public void endGame(int shoeSize) {
    }

    @Override
    public void deal(Hid hid, Card card, int[] values) {
        
        if(card == null)
            return;
        if (hid.getSeat() == Seat.DEALER)
            dealerHand.hit(card);
    }

    @Override
    public void insure() {

    }

    @Override
    public void bust(Hid hid) {

    }

    @Override
    public void win(Hid hid) {

    }

    @Override
    public void blackjack(Hid hid) {

    }

    @Override
    public void charlie(Hid hid) {

    }

    @Override
    public void lose(Hid hid) {

    }

    @Override
    public void push(Hid hid) {

    }

    @Override
    public void shuffling() {

    }

    @Override
    public void play(Hid hid) {

        if(hid.getSeat() != mine)
            return;
        new Thread(this).start();
        
    }

    protected void respond() throws InterruptedException{
        
         int response = random.nextInt(RESPONSE_TIME)*1200;
         Thread.sleep(response);      
    }
    
    
    @Override
    public void run() {
        try{
            Card upCard = dealerHand.getCard(0);
            BasicStrategy botAdvise = new BasicStrategy();
            Play botPlay = botAdvise.getPlays(myHand, upCard);
            
            respond();
            
            if(botPlay == Play.DOUBLE_DOWN){
                dealer.doubleDown(this, myHand.getHid());
                Hid hid = myHand.getHid();
                hid.setAmt(10.0);
                hid.dubble();
                if(hid.getSeat() != mine)
                    return;
                 new Thread(this).start();
            }
            
            if(botPlay == Play.HIT){
                dealer.hit(this, myHand.getHid());
                Hid hid = myHand.getHid();
                hid.setAmt(10.0);
                if(hid.getSeat() != mine)
                    return;
                 new Thread(this).start();
            }
            
            if(botPlay == Play.SPLIT){
                int dealerCardsValue = dealerHand.getValue();
                
                if(myHand.getValue() >= 17){
                    dealer.stay(this, myHand.getHid());
                }
                
                if(dealerCardsValue >= 8){
                    dealer.stay(this, myHand.getHid());
                }
                
                if(dealerCardsValue <= 12){
                    dealer.hit(this, myHand.getHid());
                    Hid hid = myHand.getHid();
                    hid.setAmt(10.0);
                    if(hid.getSeat() != mine)
                    return;
                     new Thread(this).start();
                }
                
                dealer.stay(this, myHand.getHid());
            }
            
            if(botPlay == Play.STAY){
              dealer.stay(this, myHand.getHid());
            }
            
        } catch (InterruptedException e) {
            
        }

    }
}
