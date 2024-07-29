/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author patel
 */

import java.util.Scanner;

public class BlackjackGame {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;

    public BlackjackGame() {
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
    }

    public void dealInitialCards() {
        playerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
    }

    public void playerHit() {
        playerHand.addCard(deck.deal());
    }

    public void dealerHit() {
        dealerHand.addCard(deck.deal());
    }

    public boolean playerBusted() {
        return playerHand.getValue() > 21;
    }

    public boolean dealerBusted() {
        return dealerHand.getValue() > 21;
    }

    public boolean playerWins() {
        return playerHand.getValue() <= 21 && playerHand.getValue() > dealerHand.getValue();
    }

    public boolean dealerWins() {
        return dealerHand.getValue() <= 21 && dealerHand.getValue() > playerHand.getValue();
    }

    public boolean push() {
        return playerHand.getValue() == dealerHand.getValue();
    }

    public void play() {
        dealInitialCards();
        Scanner scanner = new Scanner(System.in);
        boolean playerTurn = true;

        while (playerTurn) {
            System.out.println("Player's hand:");
            System.out.println(playerHand);
            System.out.println("Player's hand value: " + playerHand.getValue());
            System.out.println("Dealer's hand:");
            System.out.println(dealerHand);
            System.out.println("Dealer's hand value: " + dealerHand.getValue());

            if (playerBusted()) {
                System.out.println("Player busts! Dealer wins.");
                return;
            }

            System.out.println("Do you want to hit or stand? (hit/stand)");
            String decision = scanner.nextLine();

            if ("hit".equalsIgnoreCase(decision)) {
                playerHit();
            } else if ("stand".equalsIgnoreCase(decision)) {
                playerTurn = false;
            } else {
                System.out.println("Invalid input. Please type 'hit' or 'stand'.");
            }
        }

        while (dealerHand.getValue() < 17) {
            dealerHit();
        }

        System.out.println("Final hands:");
        System.out.println("Player's hand:");
        System.out.println(playerHand);
        System.out.println("Player's hand value: " + playerHand.getValue());
        System.out.println("Dealer's hand:");
        System.out.println(dealerHand);
        System.out.println("Dealer's hand value: " + dealerHand.getValue());

        if (dealerBusted()) {
            System.out.println("Dealer busts! Player wins.");
        } else if (playerWins()) {
            System.out.println("Player wins!");
        } else if (dealerWins()) {
            System.out.println("Dealer wins!");
        } else if (push()) {
            System.out.println("It's a push!");
        }
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.play();
    }
    
}
