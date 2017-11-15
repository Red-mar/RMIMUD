package com.mudteam.mud;

import com.mudteam.network.RMIClient;
import com.mudteam.network.Session;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameClient {

    private RMIClient client;
    private Session session;
    private ArrayList<Location> map;
    private Character currentCharacter = null;

    public GameClient(String ip, int port) throws RemoteException, NotBoundException {
        client = new RMIClient(ip, port);
        ViewOnChat viewOnChat = new ViewOnChat();

        Running();
    }

    public RMIClient getClient() {
        return client;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setMap(ArrayList<Location> map) {
        this.map = map;
    }

    public ArrayList<Location> getMap() {
        return map;
    }

    private void Running(){
        boolean isRunning = true;
        Scanner input = new Scanner(System.in);

        System.out.println("Hello welcome to game");
        System.out.println("Do you already have an account? y/n");
        if (input.next().equals("n")){
            System.out.println("Creating new account...");
            System.out.print("Username:");
            String user = input.next();
            System.out.print("Password");
            String password = input.next();
            if(client.createAccount(user, password)){
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
        setSession(client.login(user,password));
        if (getSession() != null){
            System.out.println("Logged in!");
            try {
                setMap(getSession().loadMap());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("Login failed");
            System.exit(0);
        }

        System.out.println("What do you want to do?");
        System.out.println("[1] Create a new character.");
        System.out.println("[2] Play the game.");
        System.out.println("[3] Logout.");

        switch (input.next()){
            case "1":
                System.out.println("What is the name of your character?");
                String name = input.next();
                try {
                    session.CreateCharacter(name);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                System.out.println("Select a character.");
                ArrayList<Character> characters = null;
                try {
                    characters = session.getCharacters();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < characters.size(); i++) {
                    System.out.println("[" + i + "] " + characters.get(i).getName());
                }
                currentCharacter = characters.get(input.nextInt());
                break;
            case "3":
                break;
        }

        while (isRunning){
            switch (input.next()){
                case "say":
                    try {
                        client.sendMessage(currentCharacter.getName() + ": " + input.nextLine());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case "logout":
                    logout();
                    isRunning = false;
                    input.close();
                    break;
                case "info":
                    getInfo();
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
        //System.exit(0);
    }

    private void getInfo(){
        System.out.println("--- Session Info ---");
        System.out.println("Username: " + currentCharacter.getName());
        System.out.println("Locations: " + map.toString());
        //System.out.println("Location: " + getSession().getCharacter().getLocation().getName());
        System.out.println("---      End     ---");
    }

    private void logout(){
        try {
            getSession().logout();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        setSession(null);
        System.out.println("logged out");
    }
}
