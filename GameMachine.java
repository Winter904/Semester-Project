//------------------------------------------------------------
// GameMachine, contains rules for machine objects (objects that can be turned on/off, and that break down over time)
// Methods for:
// 1. getName: Gets machine's name
// 2. getDescription: Gets machine's description
// 3. isOn: Gets if machine is on or not
// 4. turnMachineOn: Turns machine on
// 5. turnMachineOff: Turns machine off
//------------------------------------------------------------

public class GameMachine {
    private String name;
    private String descriptionOff;
    private String descriptionOn;
    private boolean isOn;

    public GameMachine(String name, String descriptionOff, String descriptionOn, boolean isOn) {
        this.name = name;
        this.descriptionOff = descriptionOff;
        this.descriptionOn = descriptionOn;
        this.isOn = isOn;
    }

    // 1. Gets machine's name
    public String getName() {
        return name;
    }

    // 2. Gets machine's description
    public String getDescription() {
        if (isOn == true) {
            return descriptionOn;
        } else {
            return descriptionOff;
        }
    }

    // 3. Gets if machine is on or not
    public boolean isOn() {
        if (isOn == true) {
            return true;
        } else {
            return false;
        }
    }
    
    // 4. Turns machine on
    public String turnMachineOn() {
        if (isOn == true) {
            return "The " + name + " is already on.";
        } else {
            isOn = true;
            return "The " + name + " hums to life.";
        }
    }

    // 5. Turns machine off
    public String turnMachineOff() {
        if (isOn == false) {
            return "The " + name + " is already off.";
        } else {
            isOn = false;
            return "The " + name + " powers down.";
        }
    }
}

