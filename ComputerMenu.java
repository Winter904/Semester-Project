import java.util.Scanner;

public class ComputerMenu implements Runnable {
    private boolean running = true;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Computer Online Ordering System ===");
        System.out.println("Type 'order' to order a part, 'status' to check delivery, or 'exit' to log off.\n");

        while (running) {
            System.out.print("COMPUTER> ");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "order":
                    System.out.println("Placing your order...");
                    simulateOrder();
                    break;

                case "status":
                    System.out.println("Delivery still processing... please wait.");
                    break;

                case "exit":
                    System.out.println("Logging off computer...");
                    running = false;
                    break;

                default:
                    System.out.println("Unknown command. Try 'order', 'status', or 'exit'.");
                    break;
            }
        }
        //scanner.close();
    }

    private void simulateOrder() {
        Thread deliveryThread = new Thread(() -> {
            try {
                Thread.sleep(10000); // simulate delivery time
                System.out.println("\n[ALERT] Your ordered part has been delivered to your inventory!\n");
            } catch (InterruptedException e) {
                System.out.println("[ERROR] Delivery interrupted.");
            }
        });
        deliveryThread.start();
    }
}
