public class GameHeldItem {
    private String name;
    private String description;
    private boolean isInInventory;

    public GameHeldItem(String name, String description, boolean isInInventory) {
        this.name = name;
        this.description = description;
        this.isInInventory = isInInventory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsInInventory() {
        return isInInventory;
    }
    
}
