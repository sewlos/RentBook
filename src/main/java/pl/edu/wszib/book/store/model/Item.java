package pl.edu.wszib.book.store.model;

import javax.persistence.*;

@Entity(name = "t_item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Manufacturer manufacturer;
    private double price;
    private String description;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Item(String name, Manufacturer manufacturer,
                double price, String description, int quantity, Type type) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
        this.quantity = quantity;

        this.type = type;
    }

    public Item() {
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        Book,
        Stationery
    }
}
