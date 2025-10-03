import java.util.Map;
import java.util.HashMap;
//test test
public class GameContainer {
    private String name;
    private String descriptionClosed;
    private String descriptionOpen;
    private boolean isOpen;
    private boolean isUnlocked;
    private boolean containsItem;
    private Map<String, GameItem> items;

    // constructor
    public GameContainer(String name, String descriptionClosed, String descriptionOpen, boolean isOpen, boolean isUnlocked, boolean containsItem) {
        this.name = name;
        this.descriptionClosed = descriptionClosed;
        this.descriptionOpen = descriptionOpen;
        this.isOpen = isOpen;
        this.isUnlocked = isUnlocked;
        this.containsItem = containsItem;
        this.items = new HashMap<>(); // Use a HashMap to store items by name
    }

    // A method to add a new item to the room
    public void addItem(GameItem ite) {
        this.items.put(ite.getName().toLowerCase(), ite);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        if (isOpen == true) {
            return descriptionOpen;
        } else {
            return descriptionClosed;
        }
    }

    public boolean getOpenState() {
        if (isOpen == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getUnlockedState() {
        if (isUnlocked == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getContainsItem() {
        return containsItem;
    }
    
    public String openContainer() {
        if (isUnlocked == true) {
            if (isOpen == true) {
                return "The " + name + " is already open.";
            } else {
                isOpen = true;
                return "You open the " + name;
            }
        } else {
            return "You try to open the " + name + " but it appears to be locked.";
        }
    }

    public String closeContainer() {
        if (isOpen == true) {
            isOpen = false;
            return "You close the " + name;
        } else {
            return "The " + name + " is already closed.";
        }
    }

    public String unlockContainer() {
        if (isOpen == true) {
            return "The " + name + " is already open.";
        } else if (isOpen == false && isUnlocked == true) {
            return "The "+ name + " is already unlocked.";
        } else {
            isUnlocked = true;
            return "You unlock the " + name;
        }
    }

}



