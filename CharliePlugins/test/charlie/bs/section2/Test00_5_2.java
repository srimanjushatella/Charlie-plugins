/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.bs.section2;

import charlie.advisor.BasicStrategy;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Seat;
import charlie.util.Play;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author TSM
 */
public class Test00_5_2 {
    @Test
    public void test05()
    {
        System.out.println("test 5");
    Hand myHand = new Hand(new Hid(Seat.YOU));
    Card upCard = new Card(2,Card.Suit.HEARTS);
    myHand.hit(new Card(4,Card.Suit.SPADES));
    myHand.hit(new Card(5,Card.Suit.HEARTS));
    BasicStrategy bs = new BasicStrategy();
    assertEquals(bs.getPlays(myHand, upCard), Play.HIT);
    }
}
