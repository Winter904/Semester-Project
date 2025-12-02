import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import com.sun.management.OperatingSystemMXBean;

public class OSFinalProject {
    public static void main(String[] args) {

        GameConsole.init(); // 🚀 open GUI window

        boolean exitGame = false;
        boolean cmdOn = false;
        String lastTarget = null;

        Thread computerThread = new Thread(new ComputerMenu());

        Room startRoom = new Room("The Main Chamber", "You are in a dusty chamber. The walls are covered in strange symbols. You see a machine and a sign.");

        GameMachine copperMachine = new GameMachine("Copper Machine", "A large, quiet, copper machine with buttons and levers.", "A large, humming, copper machine with buttons and levers.", false);
        GameMachine rustyMachine = new GameMachine("Rusty Machine", "An old, quiet, rusty steel machine worn with use.", "An old, rumbling, rusty steel machine barely functioning.", false);
        GameMachine heavyDutyMachine = new GameMachine("Large Machine", "An large, quiet, industrial machine that looks like it would take a lot to power.", "An large, rumbling, industrial machine. It looks like it's taking a lot of juice to power.", false);
        GameObject sign = new GameObject("Wooden Sign", "A wooden sign with faded text.", false);
        GameContainer chest = new GameContainer("Iron Chest", "An old rusting chest of some kind. It appears to be closed", "An old rusting chest of some kind. It has been opened.","An old rusting chest of some kind. It has a silver key sitting inside.", false, false);

        Inventory inv = new Inventory("Inventory", "Player's Inventory");
        GameItem silverKey = new GameItem("Silver Key", "A small silver key with intricate designs on its surface.", false, true);
        GameHeldItem heldSilverKey = new GameHeldItem("Silver Key", "A small silver key with intricate designs on its surface.", false);

        startRoom.addMachine(copperMachine);
        startRoom.addMachine(rustyMachine);
        startRoom.addMachine(heavyDutyMachine);
        startRoom.addObject(sign);
        startRoom.addContainer(chest);
        chest.addItem(silverKey);
        inv.addHeldItem(heldSilverKey);

        // intro text
        GameConsole.println("You find yourself inside a dusty warehouse, filled with old machines and worn down equipment.");
        GameConsole.println("There is a worn sign here.");
        GameConsole.println("There is a Copper Machine here.");
        GameConsole.println("There is a Rusty Machine here.");
        GameConsole.println("There is a Large Machine here.");
        GameConsole.println("There is a Rusty Chest here.");
        GameConsole.println("There is an old boxy computer here.");
        GameConsole.println("There is glitchy digital panel attached to the wall here.");

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        while (!exitGame) {

            String input;
            if (!computerThread.isAlive()) {
                GameConsole.println(" ");
                input = GameConsole.nextCommand().toLowerCase();
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
                    if (target.equals("at sign")) GameConsole.println(sign.getDescription());
                    else if (target.equals("at copper machine")) GameConsole.println(copperMachine.getDescription());
                    else if (target.equals("at rusty machine")) GameConsole.println(rustyMachine.getDescription());
                    else if (target.equals("at chest")) GameConsole.println(chest.getDescription());
                    else if (target.equals("at panel")) GameConsole.println("A dusty boxy copper panel installed into a nearby wall. It looks like it's hooked up to the building and is displaying performance statistics.");
                    else GameConsole.println(startRoom.getDescription());
                    break;

                case "examine":
                case "x":
                    if (target.equals("sign")) GameConsole.println(sign.getDescription());
                    else if (target.equals("machine")) GameConsole.println(copperMachine.getDescription());
                    else if (target.equals("copper machine")) GameConsole.println(copperMachine.getDescription());
                    else if (target.equals("rusty machine")) GameConsole.println(copperMachine.getDescription());
                    else if (target.equals("chest")) GameConsole.println(chest.getDescription());
                    else if (target.equals("panel")) {
                        GameConsole.println("\n=== PERFORMANCE MONITOR ===");
                        GameConsole.println("Press ENTER to return.\n");

                        Thread stopper = new Thread(() -> GameConsole.nextCommand());
                        stopper.start();

                        long startTime = System.currentTimeMillis();

                        while (stopper.isAlive()) {
                            MemoryUsage heap = memoryBean.getHeapMemoryUsage();
                            double cpuLoad = osBean.getProcessCpuLoad() * 100;
                            long uptime = (System.currentTimeMillis() - startTime) / 1000;

                            GameConsole.println(
                                String.format("Uptime: %ds | Heap: %dMB / %dMB | CPU: %.2f%%",
                                    uptime,
                                    heap.getUsed() / 1_000_000,
                                    heap.getMax() / 1_000_000,
                                    cpuLoad
                                )
                            );

                            try { Thread.sleep(1000); } catch (InterruptedException e) {}
                        }

                        GameConsole.println("\nReturning to game...\n");
                    }
                    else GameConsole.println(startRoom.getDescription());
                    break;

                case "take":
                case "t":
                    if (target.equals("key")) GameConsole.println(silverKey.takeItem(inv));
                    break;

                case "read":
                case "r":
                    if (target.equals("sign")) GameConsole.println(sign.getDescription());
                    else GameConsole.println("There is nothing to read.");
                    break;

                case "turn":
                    if (target.equals("on machine")) {
                        GameConsole.println("Which machine would you like to turn on?");
                        String whichMachine = GameConsole.nextCommand().toLowerCase();
                        if (whichMachine.equals("copper") || whichMachine.equals("copper machine")) {
                            if (!copperMachine.isOn()) new MachineTimer(copperMachine, 15000).start();
                            GameConsole.println(copperMachine.turnMachineOn());
                        } else if (whichMachine.equals("rusty") || whichMachine.equals("rusty machine")) {
                            if (!rustyMachine.isOn()) new MachineTimer(rustyMachine, 4000).start();
                            GameConsole.println(rustyMachine.turnMachineOn());
                        } else if (whichMachine.equals("large") || whichMachine.equals("large machine")) {
                            if (!heavyDutyMachine.isOn()) new MachineTimer(heavyDutyMachine, 10000, true, 10000).start();
                            GameConsole.println(heavyDutyMachine.turnMachineOn());
                        }
                    }
                    else if (target.equals("off machine")) {
                        GameConsole.println("Which machine would you like to turn off?");
                        String whichMachine = GameConsole.nextCommand().toLowerCase();
                        if (whichMachine.equals("copper") || whichMachine.equals("copper machine")) {
                            GameConsole.println(copperMachine.turnMachineOff());
                        } else if (whichMachine.equals("rusty") || whichMachine.equals("rusty machine")) {
                            GameConsole.println(rustyMachine.turnMachineOff());
                        } else if (whichMachine.equals("large") || whichMachine.equals("large machine")) {
                            GameConsole.println(heavyDutyMachine.turnMachineOff());
                        }
                    }
                    else GameConsole.println("You can't turn that.");
                    break;

                case "open":
                case "o":
                    if (target.equals("chest")) {
                        if (!chest.isOpen()) {
                            GameConsole.println(chest.openContainer());
                            if (chest.hasItem()) silverKey.makeObtainable();
                        } else GameConsole.println("The chest is already open.");
                    } else GameConsole.println("You can't really open that.");
                    break;

                case "close":
                case "c":
                    if (target.equals("chest")) {
                        if (chest.isOpen()) {
                            GameConsole.println(chest.closeContainer());
                            if (chest.hasItem()) silverKey.makeUnobtainable();
                        } else GameConsole.println("The chest is already closed.");
                    } else GameConsole.println("You can't really close that.");
                    break;

                case "unlock":
                case "u":
                    if (target.equals("chest")) GameConsole.println(chest.unlockContainer());
                    else GameConsole.println("You can't really unlock that.");
                    break;

                case "inventory":
                case "inv":
                case "i":
                    inv.listInventory();
                    break;

                case "use":
                    if (target.equals("computer")) {
                        GameConsole.println("You sit down at the computer and power it on...");
                        computerThread.start();
                    }
                    else GameConsole.println("You can't use that.");
                    break;

                case "activatecmd":
                    cmdOn = true;
                    break;

                case "cmd":
                    if (cmdOn) {
                        if (target.equals("machine")) {
                            GameConsole.println("Name: " + copperMachine.getName());
                            GameConsole.println("Desc: " + copperMachine.getDescription());
                            GameConsole.println("isOn: " + copperMachine.isOn());
                        } else if (target.equals("sign")) {
                            GameConsole.println("Name: " + sign.getName());
                            GameConsole.println("Desc: " + sign.getDescription());
                        } else if (target.equals("chest")) {
                            GameConsole.println("Name: " + chest.getName());
                            GameConsole.println("Desc: " + chest.getDescription());
                            GameConsole.println("isOpen: " + chest.isOpen());
                            GameConsole.println("isUnlocked: " + chest.isUnlocked());
                        } else if (target.equals("lasttarget")) {
                            GameConsole.println("lastTarget: " + lastTarget);
                        } else GameConsole.println("I don't understand that command.");
                    } else GameConsole.println("I don't understand that command.");
                    break;

                case "exit":
                case "quit":
                    exitGame = true;
                    break;

                default:
                    GameConsole.println("I don't understand that command.");
                    break;
            }
        }

        GameConsole.println("Thank you for playing!");
    }
}
