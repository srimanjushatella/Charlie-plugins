/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.advisor;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.util.Play;
import java.util.Map;



/**
 *
 * @author TSM
 */
public class BasicStrategy { 
    
    protected static Map<Integer, Play[]> section1Table;
    protected static Map<Integer, Play[]> section2Table;
    protected static Map<Integer, Play[]> section3Table;
    protected static Map<Integer, Play[]> section4Table;
    int horizontal = 0;
    int vertical = 0;
    Play Hint = Play.NONE;
    
    public final static Play H = Play.HIT;
    public final static Play S = Play.STAY;
    public final static Play D = Play.DOUBLE_DOWN;
    public final static Play SP = Play.SPLIT;
    
    
    Play Section1Table (Hand myHand, Card upCard)
    {
        
        Play[] rule20 = {S,S,S,S,S,S,S,S,S,S};
        Play[] rule19 = {S,S,S,S,S,H,H,H,H,H};
        Play[] rule18 = {S,S,S,S,S,H,H,H,H,H};
        Play[] rule17 = {S,S,S,S,S,H,H,H,H,H};
        Play[] rule16 = {S,S,S,S,S,H,H,H,H,H};
        Play[] rule15 = {S,S,S,S,S,H,H,H,H,H};
        Play[] rule14 = {S,S,S,S,S,H,H,H,H,H};
        Play[] rule13 = {S,S,S,S,S,H,H,H,H,H};
        Play[] rule12 = {H,H,S,S,S,H,H,H,H,H};
        
        Play[][] allrules = {rule20,rule19,rule18,rule17,rule16,rule15,rule14,rule13,rule12};
        
        if(myHand.getValue() < 17)
            horizontal = (17 - myHand.getValue());
        if(upCard.isAce())
            vertical = (rule12.length-1);
        else
            vertical = (upCard.value()-2);
        
        Hint = allrules[horizontal][vertical];
             
        return Hint;
    }
    
    protected Boolean isValid(Hand myhand) {
        if(myhand == null)
            return false;
        
        if(myhand.size() < 2)
            return false;
        
        for(int k=0; k < myhand.size(); k++)
            if (!isValid(myhand.getCard(k)))
                return false;
        
        return true;
    }
    
    protected Boolean isValid(Card mycard) {
        if(mycard == null)
            return false;
        
        for(int k=2; k==10; k++)
            if(mycard.getRank()==k)
                return true;
        
        switch(mycard.getRank()) {
            case Card.ACE:
            case Card.JACK:
            case Card.KING:
            case Card.QUEEN:
            return true;
        }
        return false;
    }
    
    Play Section2Table (Hand myHand, Card upCard)
    {
        Play[] rule11 = {D,D,D,D,D,D,D,D,D,H};
        Play[] rule10 = {D,D,D,D,D,D,D,D,H,H};
        Play[] rule9 = {H,D,D,D,D,H,H,H,H,H};
        Play[] rule8 = {H,H,H,H,H,H,H,H,H,H};
        Play[] rule7 = {H,H,H,H,H,H,H,H,H,H};
        Play[] rule6 = {H,H,H,H,H,H,H,H,H,H};
        Play[] rule5 = {H,H,H,H,H,H,H,H,H,H};
        
        Play[][] allrules = {rule11,rule10,rule9,rule8,rule7,rule6,rule5};
        
        horizontal = (11 - myHand.getValue());
        if(upCard.isAce())
            vertical = (rule5.length-1);
        else
            vertical = (upCard.value()-2);
        
        Hint = allrules[horizontal][vertical];
             
        return Hint;
    }
    
    Play Section3Table (Hand myHand, Card upCard)
    {
        
        Play[] ruleA10 = {S,S,S,S,S,S,S,S,S,S};
        Play[] ruleA9 = {S,S,S,S,S,S,S,S,S,S};
        Play[] ruleA8 = {S,S,S,S,S,S,S,S,S,S};
        Play[] ruleA7 = {S,D,D,D,D,S,S,H,H,H};
        Play[] ruleA6 = {H,D,D,D,D,H,H,H,H,H};
        Play[] ruleA5 = {H,H,D,D,D,H,H,H,H,H};
        Play[] ruleA4 = {H,H,D,D,D,H,H,H,H,H};
        Play[] ruleA3 = {H,H,H,D,D,H,H,H,H,H};
        Play[] ruleA2 = {H,H,H,H,D,H,H,H,H,H};
        
        Play[][] allrules = {ruleA10,ruleA9,ruleA8,ruleA7,ruleA6,ruleA5,ruleA5,ruleA4,ruleA3,ruleA2};
        
        Card hCardOne = myHand.getCard(0);
        Card hCardTwo = myHand.getCard(1);
        if(!hCardOne.isAce())
            horizontal = hCardOne.value();
        else
            horizontal = hCardTwo.value();
        horizontal = (10 - horizontal);
        
        if(upCard.isAce())
            vertical = (ruleA2.length-1);
        else
            vertical = (upCard.value()-1);
        Hint = allrules[horizontal][vertical];
        
        return Hint;
     
    }
    
     Play Section4Table (Hand myHand, Card upCard)
    {
        
        Play[] ruleAA = {SP,SP,SP,SP,SP,SP,SP,SP,SP,SP};
        Play[] rule1010 = {S,S,S,S,S,S,S,S,S,S};
        Play[] rule99 = {SP,SP,SP,SP,SP,SP,S,SP,SP,S};
        Play[] rule88 = {SP,SP,SP,SP,SP,SP,SP,SP,SP,SP};
        Play[] rule77 = {SP,SP,SP,SP,SP,SP,H,H,H,H};
        Play[] rule66 = {SP,SP,SP,SP,SP,H,H,H,H,H};
        Play[] rule55 = {D,D,D,D,D,D,D,D,H,H};
        Play[] rule44 = {H,H,H,SP,SP,H,H,H,H,H};
        Play[] rule33 = {SP,SP,SP,SP,SP,SP,H,H,H,H};
        Play[] rule22 = {SP,SP,SP,SP,SP,SP,H,H,H,H};
        
        Play[][] allrules = {ruleAA,rule1010,rule99,rule88,rule77,rule66,rule55,rule44,rule33,rule22};
        
        Card hCard = myHand.getCard(0);
        if(hCard.isAce())
            horizontal = 0;
        else
            horizontal = (11 - hCard.value());
        if(upCard.isAce())
            vertical = (rule22.length-1);
        else
            vertical = (upCard.value()-2);
        
        Hint = allrules[horizontal][vertical];
        return Hint;
    
    }
    
     public Play getPlays(Hand myHand, Card upCard)
     {
          if (myHand.getValue() >= 5 && myHand.getValue() < 11)
             return Section2Table(myHand, upCard);
          else if (myHand.getCard(0).isAce())
             return Section3Table(myHand, upCard);
          else if (myHand.isPair())
             return Section4Table(myHand ,upCard);
         return Section1Table(myHand, upCard);
     }
}

