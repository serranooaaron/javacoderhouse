package com.coderhouse;


import com.coderhouse.clases.Auto;

public class Main {
    public static void main(String[] args) {
        Auto auto1 = new Auto("Palio", "Fiat", 4);
        try {
            auto1.setRuedas(-3);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(auto1);
    }
}