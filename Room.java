import java.util.Map;
import java.util.HashMap;

public class Room {
    //test
    private String name;
    private String description;
    private Map<String, GameObject> objects;
    private Map<String, GameMachine> machines;
    private Map<String, GameContainer> containers;

    // A constructor to initialize the room with a name and description
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.objects = new HashMap<>(); // Use a HashMap to store objects by name
        this.machines = new HashMap<>(); // Use a HashMap to store machines by name
        this.containers = new HashMap<>(); // Use a HashMap to store containers by name
    }

    // A method to add a new object to the room
    public void addObject(GameObject obj) {
        this.objects.put(obj.getName().toLowerCase(), obj);
    }

    // A method to add a new machine to the room
    public void addMachine(GameMachine mac) {
        this.machines.put(mac.getName().toLowerCase(), mac);
    }


    // A method to add a new container to the room
    public void addContainer(GameContainer con) {
        this.containers.put(con.getName().toLowerCase(), con);
    }

    // A method to get an object from the room by its name
    public GameObject getObject(String objectName) {
        return this.objects.get(objectName.toLowerCase());
    }

    // A method to get the room's description
    public String getDescription() {
        return this.description;
    }

    // A method to get the room's name
    public String getName() {
        return this.name;
    }
}
