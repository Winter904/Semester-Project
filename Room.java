//------------------------------------------------------------
// Room, contains rules and list of rooms in the game (can contain gameObjects, and items)
// Methods for:
// 1. getName: Gets room's name
// 2. getDescription: Gets room's description
// 3. addObject: Adds new objects to the room
// 4. getObject: Gets an object from the room by its name
// 5. addMachine: Adds a new machine to the room
// 6. getMachine: Gets an machine from the room by its name
// 7. addContainer: Adds a new machine to the room
// 8. getContainer: Gets an container from the room by its name
//------------------------------------------------------------

import java.util.Map;
import java.util.HashMap;

public class Room {
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

    // 1. Gets room's name
    public String getName() {
        return this.name;
    }

    // 2. Gets room's description
    public String getDescription() {
        return this.description;
    }

    // 3. Adds new objects to the room
    public void addObject(GameObject obj) {
        this.objects.put(obj.getName().toLowerCase(), obj);
    }

    // 4. Gets an object from the room by its name
    public GameObject getObject(String objectName) {
        return this.objects.get(objectName.toLowerCase());
    }

    // 5. Adds a new machine to the room
    public void addMachine(GameMachine mac) {
        this.machines.put(mac.getName().toLowerCase(), mac);
    }

    // 6. Gets a machine from the room by its name
    public GameMachine getMachine(String machineName) {
        return this.machines.get(machineName.toLowerCase());
    }

    // 7. Adds a new container to the room
    public void addContainer(GameContainer con) {
        this.containers.put(con.getName().toLowerCase(), con);
    }

    // 8. Gets a container from the room by its name
    public GameContainer getContainer(String containerName) {
        return this.containers.get(containerName.toLowerCase());
    }

    

    
}
