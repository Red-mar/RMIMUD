package com.company;

import com.company.mud.GameClient;
import com.company.mud.ViewOnChat;
import com.company.network.RMIClient;
import com.company.network.Session;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        boolean isRunning = true;
        Scanner input = new Scanner(System.in);
        GameClient client = null;

        System.out.println("Client using registry");

        String ip = "localhost";
        int port = 4321;

        try {
            client = new GameClient(ip, port);
        } catch (RemoteException e) {
            System.out.println("Could not connect to server: " + e.getMessage());
            System.exit(0);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        System.out.println("Hello welcome to game");
        System.out.println("Do you already have an account? y/n");
        if (input.next().equals("n")){
            System.out.println("Creating new account...");
            System.out.print("Username:");
            String user = input.next();
            System.out.print("Password");
            String password = input.next();
            if(client.getClient().createAccount(user, password)){
                System.out.println("Succesfully created a new account!");
            } else {
                System.out.println("Error creating new account.");
                System.exit(0);
            }
        }
        System.out.println("Please login");
        System.out.print("Username:");
        String user = input.next();
        System.out.print("Password:");
        String password;
        if (System.console() != null){ //Does not work in IDE
            password = System.console().readPassword().toString();
        } else{
            password = input.next();
        }
        client.setSession(client.getClient().login(user,password));
        if (client.getSession() != null){
            System.out.println("Logged in!");
            try {
                client.setMap(client.getSession().loadMap());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("Login failed");
            System.exit(0);
        }

        while (isRunning){
            switch (input.next()){
                case "say":
                    try {
                        client.getClient().sendMessage(client.getSession().getCharacter().getName() + ": " + input.nextLine());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case "logout":
                    try {
                        client.getSession().logout();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    client.setSession(null);
                    isRunning = false;
                    input.close();
                    System.out.println("logged out");
                    break;
                case "info":
                    try {
                        System.out.println("--- Session Info ---");
                        System.out.println("Username: " + client.getSession().getCharacter().getName());
                        System.out.println("Location: " + client.getSession().getCharacter().getLocation().getName());
                        System.out.println("---      End     ---");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case "go":
                    break;
                case "exit":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Unknown Command.");
                    break;
            }
        }
        System.exit(0);
    }
}
