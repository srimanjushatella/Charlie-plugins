/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.sidebet.test;

import charlie.card.Card;

/**
 *This class implements a test shoe
 * @author TSM
 */
public class Shoe extends charlie.card.Shoe {
    @Override
    public void init() {
        cards.clear();
        
        //Test case 1
        cards.add(new Card(7, Card.Suit.HEARTS));
        cards.add(new Card(Card.KING, Card.Suit.SPADES));
        
        cards.add(new Card(9, Card.Suit.SPADES));
        cards.add(new Card(9, Card.Suit.SPADES));
        
        cards.add(new Card(3, Card.Suit.DIAMONDS));
        
        //Test case 2
        cards.add(new Card(7, Card.Suit.SPADES));
        cards.add(new Card(Card.KING, Card.Suit.CLUBS));
        
        cards.add(new Card(9, Card.Suit.CLUBS));
        cards.add(new Card(8, Card.Suit.SPADES));
        
        cards.add(new Card(3, Card.Suit.DIAMONDS));
        
         //Test case 3
        cards.add(new Card(9, Card.Suit.SPADES));
        cards.add(new Card(Card.KING, Card.Suit.CLUBS));
        
        cards.add(new Card(7, Card.Suit.HEARTS));
        cards.add(new Card(8, Card.Suit.SPADES));
        
        cards.add(new Card(3, Card.Suit.HEARTS));
        
        //Test case 4
        cards.add(new Card(7, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.KING, Card.Suit.CLUBS));
        
        cards.add(new Card(9, Card.Suit.HEARTS));
        cards.add(new Card(10, Card.Suit.HEARTS));
        
        cards.add(new Card(3, Card.Suit.HEARTS));
        
         //Test case 5
        cards.add(new Card(9, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.KING, Card.Suit.SPADES));
        
        cards.add(new Card(7, Card.Suit.HEARTS));
        cards.add(new Card(10, Card.Suit.CLUBS));
        
        cards.add(new Card(3, Card.Suit.CLUBS));
        
        //Test case 6
        cards.add(new Card(Card.KING, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.KING, Card.Suit.CLUBS));
        
        cards.add(new Card(Card.QUEEN, Card.Suit.DIAMONDS));
        cards.add(new Card(8, Card.Suit.CLUBS));
        
        //Test case 7
        cards.add(new Card(Card.KING, Card.Suit.HEARTS));
        cards.add(new Card(Card.KING, Card.Suit.SPADES));
        
        cards.add(new Card(Card.QUEEN, Card.Suit.CLUBS));
        cards.add(new Card(8, Card.Suit.SPADES));
        
        //Test case 8
        cards.add(new Card(8, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.KING, Card.Suit.SPADES));
        
        cards.add(new Card(5, Card.Suit.HEARTS));
        cards.add(new Card(6, Card.Suit.CLUBS));
        
        cards.add(new Card(Card.KING, Card.Suit.HEARTS));
        
        //Test case 9
        cards.add(new Card(7, Card.Suit.CLUBS));
        cards.add(new Card(Card.KING, Card.Suit.HEARTS));
        
        cards.add(new Card(6, Card.Suit.DIAMONDS));
        cards.add(new Card(6, Card.Suit.CLUBS));
        
        cards.add(new Card(Card.KING, Card.Suit.SPADES));
        
        //Test case 10
        cards.add(new Card(6, Card.Suit.HEARTS));
        cards.add(new Card(Card.KING, Card.Suit.SPADES));
        
        cards.add(new Card(8, Card.Suit.DIAMONDS));
        cards.add(new Card(6, Card.Suit.SPADES));
        
        cards.add(new Card(Card.KING, Card.Suit.DIAMONDS));
        
    }
    
    @Override
    public boolean shuffleNeeded() {
        return false;
    }
}
