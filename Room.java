package model;

import exceptions.FurnitureDoesNotFit;
import exceptions.FurnitureNotFound;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// Represents a Room with its name, width, height and length (in inches), having a list of Furniture
public class Room extends JPanel implements Saveable, Loadable {

    private HashMap<String, Furniture> currentFurniture;

    private static String roomName = "Your Room";

    protected static int roomWidth = 200;
    protected static int roomHeight = 200;
    protected static int roomLength = 200;

    //EFFECTS: constructs Room
    public Room() {
        this.roomName = roomName;
        this.roomWidth = roomWidth;
        this.roomHeight = roomHeight;
        this.roomLength = roomLength;
        currentFurniture = new HashMap<>();
    }

    //EFFECTS: returns list of furniture
    public HashMap<String, Furniture> getFurniture() {
        return currentFurniture;
    }

    //EFFECTS: return true if the given width of Furniture is within boundaries of the Room
    public boolean containsFurnitureWidth(int furnitureWidth, int roomWidth) {
        return furnitureWidth <= roomWidth;
    }

    //EFFECTS: return true if the given height of Furniture is within the bounds of the Room
    public boolean containsFurnitureHeight(int furnitureHeight, int roomHeight) {
        return furnitureHeight <= roomHeight;
    }

    //EFFECTS: return true if the given length of Furniture is within the bounds of the Room
    public boolean containsFurnitureLength(int furnitureLength, int roomLength) {
        return furnitureLength <= roomLength;
    }

    //REQUIRES: width, height, and length are within boundary of Room
    //MODIFIES: this
    //EFFECTS: returns true if Furniture (name, height, width, length) can be added to Room
    public boolean fitsRoom(int fw, int fh, int fl) throws FurnitureDoesNotFit {
        if (!containsFurnitureWidth(fw, roomWidth)) {
            throw new FurnitureDoesNotFit();
        } else if (!containsFurnitureHeight(fh, roomHeight)) {
            throw new FurnitureDoesNotFit();
        } else if (!containsFurnitureLength(fl, roomLength)) {
            throw new FurnitureDoesNotFit();
        }
        return true;
    }

    //MODIFIES: this
    //EFFECTS: adds furniture to current furniture list
    public void addFurniture(Furniture f) throws FurnitureDoesNotFit {
        if (fitsRoom(f.getHeight(), f.getWidth(), f.getLength())) {
            currentFurniture.put(f.getName(), f);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes furniture from Room
    public void removeFurniture(String furnitureName) throws FurnitureNotFound {

        if (!currentFurniture.containsKey(furnitureName)) {
            throw new FurnitureNotFound();
        } else {
            currentFurniture.remove(furnitureName);
        }
    }

    //EFFECTS: counts iterations
    public int countFurniture() {
        return currentFurniture.size();
    }

    //EFFECTS: returns true if list contains given piece of Furniture
    public boolean currentContains(Furniture f) {
        return currentFurniture.containsValue(f);
    }

    //EFFECTS prints given furniture
    public String printFurniture(Furniture f) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nName: ").append(f.getName())
                .append("\n   Width:    ").append(f.getWidth())
                .append("\n   Height:   ").append(f.getHeight())
                .append("\n   Length:  ").append(f.getLength());
        return sb.toString();
    }

    //EFFECTS: returns list of furniture in this room
    public HashMap<String, Furniture> getCurrentFurniture() {
        return currentFurniture;
    }

    //EFFECTS: prints furniture in currentFurniture list
    public String printCurrentFurniture() {
        StringBuilder sb = new StringBuilder();
        if (currentFurniture.size() >= 1) {
            for (Map.Entry<String, Furniture> entry : currentFurniture.entrySet()) {
                sb.append("\nName: ").append(entry.getKey()).append("\n   Width:    ")
                        .append(entry.getValue().getWidth()).append("\n   Height:   ")
                        .append(entry.getValue().getHeight()).append("\n   Length:  ")
                        .append(entry.getValue().getLength());
            }
        } else {
            sb.append("\nNo furniture in room.");
        }
        return sb.toString();
    }

    //MODIFIES: this
    //EFFECTS: saves furniture in organizer to text file
    public void save(Room room) throws IOException {
        PrintWriter writer = new PrintWriter("MyRoom.out", "UTF-8");
        for (Map.Entry<String, Furniture> entry : currentFurniture.entrySet()) {
            System.out.println("Name: " + entry.getKey());
            System.out.println(" Width: " + entry.getValue().getWidth());
            System.out.println(" Height: " + entry.getValue().getHeight());
            System.out.println(" Length: " + entry.getValue().getLength());
            writer.println(entry.getKey() + " " + entry.getValue().getWidth() + " " + entry.getValue().getHeight()
                    + " " + entry.getValue().getLength());
        }
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: loads the items from the text file and adds them to the room
    public void load(Room room) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("MyRoom.out"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Furniture furniture = new Furniture(partsOfLine.get(0),
                    Integer.parseInt(partsOfLine.get(1)),
                    Integer.parseInt(partsOfLine.get(2)),
                    Integer.parseInt(partsOfLine.get(3)));
            furniture.setFw(Integer.parseInt((partsOfLine.get(1))));
            furniture.setFh(Integer.parseInt((partsOfLine.get(2))));
            furniture.setFl(Integer.parseInt((partsOfLine.get(3))));
            room.currentFurniture.put(partsOfLine.get(0), furniture);
        }
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] split = line.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }
}