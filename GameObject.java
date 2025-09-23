public class GameObject {
    private String name;
    private String description;
    private boolean isInteractable;

    public GameObject(String name, String description, boolean isInteractable) {
        this.name = name;
        this.description = description;
        this.isInteractable = isInteractable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public void interact() {
        if (isInteractable) {
            System.out.println("You interact with the " + name + ".");
        } else {
            System.out.println("You can't interact with the " + name + ".");
        }
    }
}