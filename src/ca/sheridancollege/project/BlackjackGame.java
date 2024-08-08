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
        return playerHand.getValue() > dealerHand.getValue() && !playerBusted();
    }

    public boolean dealerWins() {
        return dealerHand.getValue() > playerHand.getValue() && !dealerBusted();
    }

    public boolean push() {
        return playerHand.getValue() == dealerHand.getValue();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        dealInitialCards();
        System.out.println("Player's hand: " + playerHand);
        System.out.println("Dealer's hand: " + dealerHand);

        while (true) {
            System.out.print("Do you want to hit or stand? (h/s): ");
            String action = scanner.nextLine();
            if (action.equalsIgnoreCase("h")) {
                playerHit();
                System.out.println("Player's hand: " + playerHand);
                if (playerBusted()) {
                    System.out.println("Player busted! Dealer wins.");
                    return;
                }
            } else {
                break;
            }
        }

        while (dealerHand.getValue() < 17) {
            dealerHit();
            System.out.println("Dealer's hand: " + dealerHand);
            if (dealerBusted()) {
                System.out.println("Dealer busted! Player wins.");
                return;
            }
        }

        if (playerWins()) {
            System.out.println("Player wins with " + playerHand.getValue() + " points!");
        } else if (dealerWins()) {
            System.out.println("Dealer wins with " + dealerHand.getValue() + " points!");
        } else if (push()) {
            System.out.println("It's a push!");
        } else {
            System.out.println("Dealer wins.");
        }
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.play();
    }
}


