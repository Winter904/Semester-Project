//------------------------------------------------------------
// MachineTimer: separate Thread that oversees a machine's runtime
// before it breaks down, with optional CPU burn mode
//------------------------------------------------------------

public class MachineTimer extends Thread {
    private GameMachine machine;
    private int duration;
    private boolean slowMode;
    private int cpuBurnMillis; // how long to burn CPU per cycle when slow mode is enabled

    /**
     * @param machine         machine to monitor
     * @param duration        time before shutdown (ms)
     * @param slowMode        whether to artificially increase CPU load
     * @param cpuBurnMillis   how long each loop should burn CPU (ms) if slowMode=true
     */
    public MachineTimer(GameMachine machine, int duration, boolean slowMode, int cpuBurnMillis) {
        this.machine = machine;
        this.duration = duration;
        this.slowMode = slowMode;
        this.cpuBurnMillis = cpuBurnMillis;
    }

    // Convenience constructor (slow mode disabled by default)
    public MachineTimer(GameMachine machine, int duration) {
        this(machine, duration, false, 0);
    }

    @Override
    public void run() {
        try {
            if (slowMode) {
                // Instead of sleeping in one block, alternate CPU burn + sleep
                long endTime = System.currentTimeMillis() + duration;
                while (System.currentTimeMillis() < endTime) {
                    burnCpu(cpuBurnMillis); // heavy CPU work
                    Thread.sleep(50);       // brief cooldown so the system stays responsive
                }
            } else {
                Thread.sleep(duration);
            }

            machine.turnMachineOff();

            GameConsole.println("- - -");
            GameConsole.println("A machine sputters and breaks down!");
            GameConsole.println("> ");

        } catch (InterruptedException e) {
            System.out.println("The timer was interrupted.");
        }
    }

    /**
     * Burns CPU for the given number of milliseconds
     */
    private void burnCpu(int millis) {
        long end = System.currentTimeMillis() + millis;
        double x = 0;
        while (System.currentTimeMillis() < end) {
            // heavy useless math — easy for the CPU, hard to optimize away
            x += Math.sqrt(x * 1.000001 + 123.456) * Math.sin(x + 987.654);
        }
    }
}
