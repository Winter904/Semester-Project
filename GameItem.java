public class GameItem {
    private String name;
    private String description;
    private boolean isObtainable;
    private boolean isInWorld;

    public GameItem(String name, String description, boolean isObtainable, boolean isInWorld) {
        this.name = name;
        this.description = description;
        this.isObtainable = isObtainable;
        this.isInWorld = isInWorld;
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

    public void makeObtainable() {
        if (isInWorld) {
            isObtainable = true;
        }
    }

    public void makeUnobtainable() {
        if (isInWorld) {
            isObtainable = false;
        }
    }

    public String takeItem() {
        if (isInWorld) {
            if (isObtainable = true) {
                return "You take the " + name;
            } else {
                return "You cannot obtain the " + name;
            }
        } else {
            return "You can't really take that.";
        }
    }
    
}
