package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    @OneToMany (mappedBy= "owner")
    private Set<Boat> boats= new HashSet<>();

    public Owner() {
    }

    public Set<Boat> getBoats() {
        return boats;
    }
    public void addBoat(Boat boat) {
        boats.add(boat);
        boat.addOwner(this);

    }
    public void setBoats(Set<Boat> boats) {
        this.boats = boats;
    }

    public Owner(Long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    public Owner(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}