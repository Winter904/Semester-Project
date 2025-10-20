//------------------------------------------------------------
// GameHeldItems, contains rules for HeldItems (item which is held by the player, not one that exists in the world)
// Methods for:
// 1. addHeldItem: Adds a HeldItem to the Inventory
// 2. removeHeldItem: Deletes a HeldItem from the Inventory
// 3. hasItem: Checks for a specific HeldItem within the Inventory
// 4. getItem: Gets a specific HeldItem from the Inventory
// 5. listInventory: Gets a list of the HeldItems from within the Inventory
//------------------------------------------------------------

public class GameHeldItem {
    private String name;
    private String description;
    private boolean isInInventory;

    // Constructor
    public GameHeldItem(String name, String description, boolean isInInventory) {
        this.name = name;
        this.description = description;
    }

    // 1. Gets HeldItem's name
    public String getName() {
        return name;
    }

    // 2. Gets HeldItem's description
    public String getDescription() {
        return description;
    }

    // 3. Gets whether or not a HeldItem is in the player's Inventory or not (is it hidden or visible)
    public boolean isInInventory() {
        return isInInventory;
    }

    //
    public void setInInventory(boolean inInventory) {
        this.isInInventory = inInventory;
    }

}
