//------------------------------------------------------------
// Inventory for player, houses Hash map of heldItems
// Methods for:
// 1. addHeldItem: Adds a HeldItem to the Inventory
// 2. removeHeldItem: Deletes a HeldItem from the Inventory
// 3. hasItem: Checks for a specific HeldItem within the Inventory
// 4. getItem: Gets a specific HeldItem from the Inventory
// 5. listInventory: Gets a list of the HeldItems from within the Inventory
//------------------------------------------------------------

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private String name;
    private String description;
    private Map<String, GameHeldItem> heldItems;

    // Constructor
    public Inventory(String name, String description) {
        this.name = name;
        this.description = description;
        this.heldItems = new HashMap<>();
    }

    // 1. Adds a held item to the inventory
    public void addHeldItem(GameHeldItem item) {
        heldItems.put(item.getName().toLowerCase(), item);
    }

    // 2. Removes a held item by name
    public void removeHeldItem(String itemName) {
        GameHeldItem removed = heldItems.remove(itemName.toLowerCase());
        if (removed != null) {
            System.out.println("You removed the " + removed.getName() + " from your inventory.");
        } else {
            System.out.println("You don't have a " + itemName + " in your inventory.");
        }
    }

    // 3. Check if the inventory has a specific item
    public boolean hasItem(String itemName) {
        return heldItems.containsKey(itemName.toLowerCase());
    }

    // 4. Get a specific item from the inventory
    public GameHeldItem getItem(String itemName) {
        return heldItems.get(itemName.toLowerCase());
    }

    // 5. Returns a formatted list of all items
    public void listInventory() {
    boolean foundAny = false;
    System.out.println("You are carrying:");
        for (GameHeldItem item : heldItems.values()) {
            if (item.isInInventory()) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
                foundAny = true;
            }
        }
        if (!foundAny) {
            System.out.println("Nothing at the moment.");
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}