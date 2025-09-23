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

    public String getName() {
        return name;
    }

    public String getDescription() {
        if (isOn == true) {
            return descriptionOn;
        } else {
            return descriptionOff;
        }
    }

    public boolean getOnState() {
        if (isOn == true) {
            return true;
        } else {
            return false;
        }
    }
    
    public String turnMachineOn() {
        if (isOn == true) {
            return "The " + name + " is already on.";
        } else {
            isOn = true;
            return "The " + name + " hums to life.";
        }
    }

    public String turnMachineOff() {
        if (isOn == false) {
            return "The " + name + " is already off.";
        } else {
            isOn = false;
            return "The " + name + " powers down.";
        }
    }
}

