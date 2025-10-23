//------------------------------------------------------------
// GameContainer, contains rules for container objects (objects that can be opened/closed, locked/unlocked, and can store items inside)
// Methods for:
// 1. addItem: Adds an item to the container
// 2. removeItem: Removes an item from the container
// 3. hasItem: Checks to see if the container has an item in it
// 4. containsItem: Checks to see if the container has a specific item in it
// 5. getItem: Gets item that's in the container
// 6. getName: gets container's name
// 7. getDescription: Gets container's description
// 8. isOpen: Gets if container is open
// 9. isUnlocked: Gets if container is unlocked
// 10. openContainer: Opens container
// 11. closeContainer: Closes container
// 12. unlockContainer: Unlocks container
// 13. lockContainer: Locks container
//------------------------------------------------------------


import java.util.Map;
import java.util.HashMap;

public class GameContainer {
    private String name;
    private String descriptionClosed;
    private String descriptionOpenEmpty;
    private String descriptionOpenWithItem;
    private boolean isOpen;
    private boolean isUnlocked;
    private Map<String, GameItem> items;

    // Constructor
    public GameContainer(String name, String descriptionClosed, String descriptionOpenEmpty, String descriptionOpenWithItem, boolean isOpen, boolean isUnlocked) {
        this.name = name;
        this.descriptionClosed = descriptionClosed;
        this.descriptionOpenEmpty = descriptionOpenEmpty;
        this.descriptionOpenWithItem = descriptionOpenWithItem;
        this.isOpen = isOpen;
        this.isUnlocked = isUnlocked;
        this.items = new HashMap<>();
    }

    // 1. Add an item to the container
    public void addItem(GameItem item) {
        items.put(item.getName().toLowerCase(), item);
    }

    // 2. Remove an item by name
    public GameItem removeItem(String itemName) {
        return items.remove(itemName.toLowerCase());
    }

    // 3. Check if container has any items
    public boolean hasItem() {
        return !items.isEmpty();
    }

    // 4. Check if a specific item exists inside
    public boolean containsItem(String itemName) {
        return items.containsKey(itemName.toLowerCase());
    }

    // 5. Return the item object (without removing it)
    public GameItem getItem(String itemName) {
        return items.get(itemName.toLowerCase());
    }

    // 6. Get container name
    public String getName() {
        return name;
    }

    // 7. Get container description
    public String getDescription() {
        if (!isOpen) {
            return descriptionClosed;
        } else if (hasItem()) {
            return descriptionOpenWithItem;
        } else {
            return descriptionOpenEmpty;
        }
    }

    // 8. Get if container is open/closed
    public boolean isOpen() {
        return isOpen;
    }

    // 9. Get if container is locked/unlocked
    public boolean isUnlocked() {
        return isUnlocked;
    }

    // 10. Open the container
    public String openContainer() {
        if (!isUnlocked) {
            return "You try to open the " + name + ", but it's locked.";
        }
        if (isOpen) {
            return "The " + name + " is already open.";
        }
        isOpen = true;
        return "You open the " + name + ".";
    }

    // 11. Close the container
    public String closeContainer() {
        if (!isOpen) {
            return "The " + name + " is already closed.";
        }
        isOpen = false;
        return "You close the " + name + ".";
    }

    // 12. Unlock container
    public String unlockContainer() {
        if (isUnlocked) {
            return "The " + name + " is already unlocked.";
        }
        isUnlocked = true;
        return "You unlock the " + name + ".";
    }

    // 13. Lock container
    public String lockContainer() {
        if (!isUnlocked) {
            return "The " + name + " is already locked.";
        }
        isUnlocked = false;
        return "You lock the " + name + ".";
    }
}