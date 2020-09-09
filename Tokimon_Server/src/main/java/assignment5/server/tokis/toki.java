/**
 * the toki class implements the tokimon objects with their needed attributes includes setter and getter
 * for all of the attributes
 *
 * @author  Jovanjot Bapla
 */

package assignment5.server.tokis;

public class toki {

    private int id;
    private String name;
    private double weight;
    private double height;
    private String ability;
    private double strength;
    private String colour;

    public toki(String named, double weighs, double tall, String type, double strong, String color){
        this.name=named;
        this.weight=weighs;
        this.height= tall;
        this.ability=type;
        this.strength=strong;
        this.colour=color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }




}
