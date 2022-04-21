package entities;

import javax.persistence.*;

@Entity
@Table(name = "boat")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String make;
    private String name;



    @ManyToOne
    @JoinColumn(name = "owner_ID")
    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public Boat(Long id, String brand, String make, String name) {
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.name = name;
    }

    public Boat() {
    }
    public Boat( String brand, String make, String name) {
        this.brand = brand;
        this.make = make;
        this.name = name;
    }

    public Long getId() {
        return id;
    }


    public void addOwner(Owner owner){
        this.owner = owner;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setOwner(Owner owner) {
    }

    @Override
    public String toString() {
        return "Boat{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", make='" + make + '\'' +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }
}