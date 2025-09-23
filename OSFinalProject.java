/* This is the beginning of a great text-based adventure game!! */

// Main class
import java.util.Scanner;

public class OSFinalProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // This is a simple way to keep track of the room. A more complex game might use a Room object
        // and have rooms linked to each other.
        boolean exitGame = false;
        boolean cmdOn = false;
        String lastTarget = null;

        // ROOM LIST
        Room startRoom = new Room("The Main Chamber", "You are in a dusty chamber. The walls are covered in strange symbols. You see a machine and a sign.");

        // GAMEOBJECT LIST
        GameMachine copperMachine = new GameMachine("Copper Machine", "A large, quiet, copper machine with buttons and levers.", "A large, humming, copper machine with buttons and levers.", false);
        GameObject sign = new GameObject("Wooden Sign", "A wooden sign with faded text.", false);
        GameContainer chest = new GameContainer("Iron Chest", "An old rusting chest of some kind. It appears to be closed", "An old rusting chest of some kind. It has been opened.", false, false, true);
        
        // GAME ITEM LIST
        Inventory inv = new Inventory("Inventory", "Player's Inventory");
        GameItem key = new GameItem("Silver Key", "A small silver key with intricate designs on its surface.", false, true);
        GameHeldItem heldKey = new GameHeldItem("Silver Key", "A small silver key with intricate designs on its surface.", false);

        startRoom.addMachine(copperMachine);
        startRoom.addObject(sign);
        startRoom.addContainer(chest);
        chest.addItem(key);
        inv.addHeldItem(heldKey);



        // THE GAME
        while (!exitGame) {
            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase();    
            String[] parts = input.split(" ", 2); // Split input into command and argument

            if (parts.length < 1) {
                System.out.println("Please enter a command.");
                continue;
            }
            String command = parts[0];
            String target = (parts.length > 1) ? parts[1] : "";
            
            if (target.equals("it")) {
                target = lastTarget;
            }

            lastTarget = target;

            switch (command) {
                case "look":
                    if (target.equals("at sign")) {
                        System.out.println(sign.getDescription());
                    } else if (target.equals("at machine")) {
                        System.out.println(copperMachine.getDescription());
                    } else if (target.equals(null)) {
                        System.out.println(startRoom.getDescription());
                    } else if (target.equals("at chest")) {
                        System.out.println(chest.getDescription());
                        if (chest.getContainsItem() == true && chest.getOpenState() == true) {
                            System.out.println("It's filled with rubbish except for a shiny silver key sitting near the top");
                        }
                    }
                    break;
                case "examine":
                    if (target.equals("sign")) {
                        System.out.println(sign.getDescription());
                    } else if (target.equals("machine")) {
                        System.out.println(copperMachine.getDescription());
                    } else if (target.equals(null)) {
                        System.out.println(startRoom.getDescription());
                    } else if (target.equals("chest")) {
                        System.out.println(chest.getDescription());
                        if (chest.getContainsItem() == true && chest.getOpenState() == true) {
                            System.out.println("It's filled with rubbish except for a shiny silver key sitting near the top");
                        }
                    }
                    break;
                case "take":
                    if (target.equals("key")) {
                        if (key.getObtainability() == true) {
                            System.out.println(key.takeItem());
                            key = null;

                        }
                        
                        // add item to inventory
                        break;
                    }
                case "read":
                    if (target.equals("sign")) {
                        System.out.println(sign.getDescription());
                    } else if (target.equals(null)) {
                        System.out.println("There is nothing to read.");
                    } else {
                        System.out.println("That's not something you can really read.");
                    }
                    break;
                case "turn":
                    if (target.equals("on machine")) {
                        if (copperMachine.getOnState() == false) {
                            MachineTimer timer = new MachineTimer(copperMachine, 5000);
                            timer.start();
                        }
                        System.out.println(copperMachine.turnMachineOn());
                    } else if (target.equals("off machine")) {
                        System.out.println(copperMachine.turnMachineOff());
                    } else {
                        System.out.println("You can't turn that.");
                    }
                    break;
                case "open":
                    if (target.equals("chest")) {
                        if (chest.getOpenState() == false) {
                            System.out.println(chest.openContainer());
                            if (chest.getContainsItem() == true) {
                                key.makeObtainable();
                            }
                        } else {
                            System.out.println("The chest is already open.");
                        }
                        break;
                    } else {
                        System.out.println("You can't really open that.");
                    }
                case "close":
                    if (target.equals("chest")) {
                        if (chest.getOpenState() == true) {
                            System.out.println(chest.closeContainer());
                            if (chest.getContainsItem() == true) {
                                key.makeUnobtainable();
                            }
                        } else {
                            System.out.println("The chest is already closed.");
                        }
                            break;
                    } else {
                        System.out.println("You can't really close that.");
                    }
                case "unlock":
                    if (target.equals("chest")) {
                        System.out.println(chest.unlockContainer());
                        break;
                    } else {
                        System.out.println("You can't really unlock that.");
                    }
                case "activatecmd":
                    cmdOn = true;
                    break;
                case "cmd":
                    if (cmdOn == true) {
                        if (target.equals("machine")) {
                            System.out.println("Name: " + copperMachine.getName());
                            System.out.println("Desc: " + copperMachine.getDescription());
                            System.out.println("isOn: " + copperMachine.getOnState());
                            break;
                        } else if (target.equals("sign")) {
                            System.out.println("Name: " + sign.getName());
                            System.out.println("Desc: " + sign.getDescription());
                            break;
                        } else if (target.equals("chest")) {
                            System.out.println("Name: " + chest.getName());
                            System.out.println("Desc: " + chest.getDescription());
                            System.out.println("isOpen: " + chest.getOpenState());
                            System.out.println("isUnlocked: " + chest.getUnlockedState());
                            break;
                        } else if (target.equals("lasttarget")) {
                            System.out.println("lastTarget: " + lastTarget);
                            break;
                        } else {
                            System.out.println("I don't understand that command.");
                            break;
                        }
                    } else {
                        System.out.println("I don't understand that command.");
                        break;
                    }
                case "exit":
                    exitGame = true;
                    break;
                default:
                    System.out.println("I don't understand that command.");
                    break;
            }
        }
        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
