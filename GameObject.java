//------------------------------------------------------------
// GameObject, contains rules for generic objects (objects that can be interacted with in some simple way)
// Methods for:
// 1. getName: Gets object's name
// 2. getDescription: Gets object's description
// 3. interact: Interacts with object
//------------------------------------------------------------

public class GameObject {
    private String name;
    private String description;
    private boolean isInteractable;

    public GameObject(String name, String description, boolean isInteractable) {
        this.name = name;
        this.description = description;
        this.isInteractable = isInteractable;
    }

    // 1. Gets object's name
    public String getName() {
        return name;
    }

    // 2. Gets object's description
    public String getDescription() {
        return description;
    }
    
    // 3. Interacts with object
    public void interact() {
        if (isInteractable) {
            System.out.println("You interact with the " + name + ".");
        } else {
            System.out.println("You can't interact with the " + name + ".");
        }
    }
}