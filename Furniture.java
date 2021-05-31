package model;

import java.awt.*;
import java.util.Objects;

// Represents a piece of Furniture having a name, height, width and length in inches
public class Furniture extends Component {

    private String name;      // name of piece of furniture
    protected int fw;        // how wide/deep the piece is
    protected int fl;       // how long the piece is
    protected int fh;       // how tall the piece is

    public Furniture(String name, int w, int h, int l) {
        this.name = name;
        this.fw = w;
        this.fh = h;
        this.fl = l;
    }

    //setters
    //EFFECTS: changes local height
    public void setFh(int h) {
        this.fh = fh;
    }

    //EFFECTS: changes local width
    public void setFw(int w) {
        this.fw = fw;
    }

    //EFFECTS: changes local length
    public void setFl(int l) {
        this.fl = fl;
    }

    //getters
    //EFFECTS: return Furniture name
    public String getName() {
        return name;
    }

    //EFFECTS: return Furniture height
    public int getHeight() {
        return fh;
    }

    //EFFECTS: return Furniture width
    public int getWidth() {
        return fw;
    }

    //EFFECTS: return Furniture length
    public int getLength() {
        return fl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || getClass() != o.getClass()) {
            return false;
        }
        Furniture furniture = (Furniture) o;
        return Objects.equals(name, furniture.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}









