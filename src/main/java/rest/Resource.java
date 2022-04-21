package rest;

import com.google.gson.Gson;
import entities.Boat;
import entities.Owner;
import entities.User;
import facades.UserFacade;
import utils.EMF_Creator;
import utils.ParallelJokes;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Path("info")
public class Resource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    UserFacade userFacade = UserFacade.getUserFacade(EMF_Creator.createEntityManagerFactory());
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery ("select u from User u",entities.User.class);
            List<User> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (user): " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin): " + thisuser + "\"}";
    }
    //
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("jokes")
    public String getJokes() {

        Gson gson = new Gson();

        List<String> jokeList = new ArrayList<>();

        try{
            jokeList = ParallelJokes.getJokes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gson.toJson(jokeList);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("owners")
    public String getOwners() {

        Gson gson = new Gson();

        List<Owner> ownerList = userFacade.getAllOwners();
        List<String> ownerNames = new ArrayList<>();

        for (int i = 0; i < ownerList.size(); i++) {
            ownerNames.add(ownerList.get(i).getName());
        }

        try{
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gson.toJson(ownerNames);
    }


//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("boat")
//    public Boat createBoat(String content) {
//        Boat boat = gson.fromJson(content, Boat.class);
//
//
////        return Response
////                .ok()
////                .entity(gson.toJson(boat))
////                .build();
//        return userFacade.createBoat(boat.getBrand(), boat.getMake(), boat.getName());
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("boat")
    public Boat createBoat(String content) {
        Boat boat = gson.fromJson(content,Boat.class);

        return userFacade.createBoat(boat.getBrand(), boat.getMake(), boat.getName());
    }

}