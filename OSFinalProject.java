//------------------------------------------------------------
// Main Class, houses the main game loop and the creation of rooms, items, objects, etc
//------------------------------------------------------------

import java.util.Scanner;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import com.sun.management.OperatingSystemMXBean;

public class OSFinalProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitGame = false;
        boolean cmdOn = false;
        String lastTarget = null;

        Thread computerThread = new Thread(new ComputerMenu());

        // ROOM LIST
        Room startRoom = new Room("The Main Chamber", "You are in a dusty chamber. The walls are covered in strange symbols. You see a machine and a sign.");

        // GAMEOBJECT LIST
        GameMachine copperMachine = new GameMachine("Copper Machine", "A large, quiet, copper machine with buttons and levers.", "A large, humming, copper machine with buttons and levers.", false);
        GameMachine rustyMachine = new GameMachine("Rusty Machine", "An old, quiet, rusty steel machine worn with use.", "An old, rumbling, rusty steel machine barely functioning.", false);
        GameObject sign = new GameObject("Wooden Sign", "A wooden sign with faded text.", false);
        GameContainer chest = new GameContainer("Iron Chest", "An old rusting chest of some kind. It appears to be closed", "An old rusting chest of some kind. It has been opened.","An old rusting chest of some kind. It has a silver key sitting inside.", false, false);

        // GAME ITEM LIST
        Inventory inv = new Inventory("Inventory", "Player's Inventory");
        GameItem silverKey = new GameItem("Silver Key", "A small silver key with intricate designs on its surface.", false, true);
        GameHeldItem heldSilverKey = new GameHeldItem("Silver Key", "A small silver key with intricate designs on its surface.", false);
        
        startRoom.addMachine(copperMachine);
        startRoom.addMachine(rustyMachine);
        startRoom.addObject(sign);
        startRoom.addContainer(chest);
        chest.addItem(silverKey);
        inv.addHeldItem(heldSilverKey);

        System.out.println("You find yourself inside a dusty warehouse, filled with old machines and worn down equipment.");
        System.out.println("There is a worn sign here.");
        System.out.println("There is a Copper Machine here.");
        System.out.println("There is a Rusty Machine here.");
        System.out.println("There is a Rusty Chest here.");
        System.out.println("There is an old boxy computer here.");
        System.out.println("There is glitchy digital panel attached to the wall here.");

        // == Performance monitor MXBeans ==
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        // THE GAME
        while (!exitGame) {
            String input;
            if (!computerThread.isAlive()) {
                System.out.print("> ");
                input = scanner.nextLine().toLowerCase();
            } else {
                continue;
            }

            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String target = (parts.length > 1) ? parts[1] : "";

            if (target.equals("it")) target = lastTarget;
            lastTarget = target;

            switch (command) {
                case "look":
                case "l":
                    if (target.equals("at sign")) System.out.println(sign.getDescription());
                    else if (target.equals("at copper machine")) System.out.println(copperMachine.getDescription());
                    else if (target.equals("at rusty machine")) System.out.println(rustyMachine.getDescription());
                    else if (target.equals("at chest")) System.out.println(chest.getDescription());
                    else if (target.equals("at panel")) System.out.println("A dusty boxy copper panel installed into a nearby wall. It looks like it's hooked up to the building and is displaying performance statistics.");
                    else System.out.println(startRoom.getDescription());
                    break;

                case "examine":
                case "x":
                    if (target.equals("sign")) System.out.println(sign.getDescription());
                    else if (target.equals("machine")) System.out.println(copperMachine.getDescription());
                    else if (target.equals("copper machine")) System.out.println(copperMachine.getDescription());
                    else if (target.equals("rusty machine")) System.out.println(copperMachine.getDescription());
                    else if (target.equals("chest")) System.out.println(chest.getDescription());
                    else if (target.equals("panel")) System.out.println("A dusty boxy copper panel installed into a nearby wall. It looks like it's hooked up to the building and is displaying performance statistics.");
                    else System.out.println(startRoom.getDescription());
                    break;

                case "take":
                case "t":
                    if (target.equals("key")) System.out.println(silverKey.takeItem(inv));
                    break;

                case "read":
                case "r":
                    if (target.equals("sign")) System.out.println(sign.getDescription());
                    else System.out.println("There is nothing to read.");
                    break;

                case "turn":
                    if (target.equals("on machine")) {
                        if (!copperMachine.isOn()) new MachineTimer(copperMachine, 10000).start();
                        System.out.println(copperMachine.turnMachineOn());
                    } else if (target.equals("off machine")) {
                        System.out.println(copperMachine.turnMachineOff());
                    } else System.out.println("You can't turn that.");
                    break;

                case "open":
                case "o":
                    if (target.equals("chest")) {
                        if (!chest.isOpen()) {
                            System.out.println(chest.openContainer());
                            if (chest.hasItem()) silverKey.makeObtainable();
                        } else System.out.println("The chest is already open.");
                    } else System.out.println("You can't really open that.");
                    break;

                case "close":
                case "c":
                    if (target.equals("chest")) {
                        if (chest.isOpen()) {
                            System.out.println(chest.closeContainer());
                            if (chest.hasItem()) silverKey.makeUnobtainable();
                        } else System.out.println("The chest is already closed.");
                    } else System.out.println("You can't really close that.");
                    break;

                case "unlock":
                case "u":
                    if (target.equals("chest")) System.out.println(chest.unlockContainer());
                    else System.out.println("You can't really unlock that.");
                    break;

                case "inventory":
                case "inv":
                case "i":
                    inv.listInventory();
                    break;

                case "use":
                    if (target.equals("computer")) {
                        System.out.println("You sit down at the computer and power it on...");
                        computerThread.start();
                    } 
                    else if (target.equals("panel")) {
                        System.out.println("\n=== PERFORMANCE MONITOR ===");
                        System.out.println("Press ENTER at any time to return.\n");

                        Thread stopper = new Thread(() -> scanner.nextLine());
                        stopper.start();

                        long startTime = System.currentTimeMillis();

                        while (stopper.isAlive()) {
                            MemoryUsage heap = memoryBean.getHeapMemoryUsage();
                            double cpuLoad = osBean.getProcessCpuLoad() * 100;
                            long uptime = (System.currentTimeMillis() - startTime) / 1000;

                            System.out.printf(
                                "\rUptime: %ds | Heap: %dMB / %dMB | CPU: %.2f%%   ",
                                uptime,
                                heap.getUsed() / 1_000_000,
                                heap.getMax() / 1_000_000,
                                cpuLoad
                            );

                            System.out.flush();
                            try { Thread.sleep(1000); } catch (InterruptedException e) {}
                        }

                        System.out.println("\n\nReturning to game...\n");
                    }
                    else {
                        System.out.println("You can't use that.");
                    }
                    break;


                case "activatecmd":
                    cmdOn = true;
                    break;

                case "cmd":
                    if (cmdOn) {
                        if (target.equals("machine")) {
                            System.out.println("Name: " + copperMachine.getName());
                            System.out.println("Desc: " + copperMachine.getDescription());
                            System.out.println("isOn: " + copperMachine.isOn());
                        } else if (target.equals("sign")) {
                            System.out.println("Name: " + sign.getName());
                            System.out.println("Desc: " + sign.getDescription());
                        } else if (target.equals("chest")) {
                            System.out.println("Name: " + chest.getName());
                            System.out.println("Desc: " + chest.getDescription());
                            System.out.println("isOpen: " + chest.isOpen());
                            System.out.println("isUnlocked: " + chest.isUnlocked());
                        } else if (target.equals("lasttarget")) {
                            System.out.println("lastTarget: " + lastTarget);
                        } else System.out.println("I don't understand that command.");
                    } else System.out.println("I don't understand that command.");
                    break;

                case "exit":
                case "quit":
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
