package utils;

import entities.Boat;
import entities.Owner;
import entities.Role;
import entities.User;
import facades.UserFacade;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Generate {
    public static void main(String[] args) {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

//        UserFacade userFacade = UserFacade.getUserFacade(EMF_Creator.createEntityManagerFactory());
//
//        System.out.println(userFacade.getAllOwners().toString());
//        userFacade.createBoat("234234234", "234234","342342");


        Owner owner1 = new Owner("Mohammed", "Tobaksvejen", "42446790");
        Owner owner2 = new Owner("Peter", "Hulevejen", "54365345");
        Owner owner3 = new Owner("Rabee", "Blomstervejen", "32465326");

        Boat boat1 = new Boat("Siemens", "Boat","BoatyMcBoatyFace");
        Boat boat2 = new Boat("Siemens", "Bigboat","BoatyMcBoatyFace2");
        Boat boat3 = new Boat("Siemens", "Bigboat","BoatyMcBoatyFace3");
        Boat boat4 = new Boat("Siemens", "SmallBoat","BoatyMcBoatyFace4");
        Boat boat5 = new Boat("Siemens", "Regularboat","BoatyMcBoatyFace5");
        Boat boat6 = new Boat("Siemens", "Bigboat","BoatyMcBoatyFace6");
        Boat boat7 = new Boat("Siemens", "Boat","BoatyMcBoatyFace7");





        em.getTransaction().begin();

        owner1.addBoat(boat1);
        owner1.addBoat(boat2);
        owner1.addBoat(boat3);
        owner2.addBoat(boat4);
        owner2.addBoat(boat5);
        owner2.addBoat(boat6);
        owner3.addBoat(boat7);

        em.persist(owner1);
        em.persist(owner2);
        em.persist(owner3);

        em.persist(boat1);
        em.persist(boat2);
        em.persist(boat3);
        em.persist(boat4);
        em.persist(boat5);
        em.persist(boat6);
        em.persist(boat7);


        em.getTransaction().commit();
        System.out.println("Created owners and boats");

    }
}
