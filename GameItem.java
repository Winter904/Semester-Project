//------------------------------------------------------------
// GameItems, contains rules for Items (item which exists in the world before the player picks it up)
// Methods for:
// 1. addHeldItem: Adds a HeldItem to the Inventory
// 2. removeHeldItem: Deletes a HeldItem from the Inventory
// 3. hasItem: Checks for a specific HeldItem within the Inventory
// 4. getItem: Gets a specific HeldItem from the Inventory
// 5. listInventory: Gets a list of the HeldItems from within the Inventory
//------------------------------------------------------------

public class GameItem {
    private String name;
    private String description;
    private boolean isObtainable;
    private boolean isInWorld;
    private GameHeldItem heldVersion; // reference to the player's version

    public GameItem(String name, String description, boolean isObtainable, boolean isInWorld) {
        this.name = name;
        this.description = description;
        this.isObtainable = isObtainable;
        this.isInWorld = isInWorld;
    }

    // Link this world item to its held version
    public void linkHeldVersion(GameHeldItem heldItem) {
        this.heldVersion = heldItem;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getObtainability() {
        return isObtainable;
    }

    public boolean isInWorld() {
        return isInWorld;
    }

    // Toggles for control
    public void makeObtainable() {
        isObtainable = true;
    }

    public void makeUnobtainable() {
        isObtainable = false;
    }

    public void setInWorld(boolean inWorld) {
        this.isInWorld = inWorld;
    }

    // Updated take logic
    public String takeItem(Inventory inventory) {
        if (!isInWorld) {
            return "You can't take that — it's not here.";
        }

        if (!isObtainable) {
            return "You cannot take the " + name + ".";
        }

        // If it’s obtainable and in the world
        isInWorld = false;
        isObtainable = false;

        if (heldVersion != null) {
            heldVersion.setInInventory(true);
            inventory.addHeldItem(heldVersion);
        }

        return "You take the " + name + ".";
    }
}