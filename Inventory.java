import java.util.Map;
import java.util.HashMap;

public class Inventory {
    private String name;
    private String description;
    private Map<String, GameHeldItem> items;

    // A constructor to initialize the room with a name and description
    public Inventory(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new HashMap<>(); // Use a HashMap to store objects by name
    }



    // A method to add a new held item to the room
    public void addHeldItem(GameHeldItem ite) {
        this.items.put(ite.getName().toLowerCase(), ite);
    }

    // A method to get a held item object from the inventory by its name
    public GameHeldItem getHeldItem(String heldItemName) {
        return this.items.get(heldItemName.toLowerCase());
    }

    // A method to get the held item's name
    public String getName() {
        return this.name;
    }

    // A method to get the held item's description
    public String getDescription() {
        return this.description;
    }
}

