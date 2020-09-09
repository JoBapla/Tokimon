/**
 * the toki class implements the tokimon objects with their needed attributes includes setter and getter
 * for all of the attributes
 *
 * @author  Jovanjot Bapla
 */

package sample.tools;

public class toki {

    private int id;
    private String name;
    private double weight;
    private double height;
    private String ability;
    private double strength;
    private String colour;

    @Override
    public String toString(){
        return this.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getAbility() {
        return ability;
    }

    public double getStrength() {
        return strength;
    }

    public String getColour() {
        return colour;
    }



}
