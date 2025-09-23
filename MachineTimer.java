public class MachineTimer extends Thread {
    private GameMachine machine;
    private int duration;

    public MachineTimer(GameMachine machine, int duration) {
        this.machine = machine;
        this.duration = duration;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration); // This pauses the thread for the specified duration
            machine.turnMachineOff(); // Turn off the machine after the sleep

            // This is the new part: The message that appears after the machine turns off
            System.out.println("- - -");
            System.out.println("The machine sputters and breaks down!");
            
            // After turning off the machine, we print a new line and the prompt.
            System.out.print("> "); 

        } catch (InterruptedException e) {
            System.out.println("The timer was interrupted.");
        }
    }
}

