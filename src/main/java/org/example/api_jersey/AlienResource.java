package org.example.api_jersey;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/aliens")
public class AlienResource {

    AlienRepository alienRepository = new AlienRepository();

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})       //only json working
    public List<Alien> getAliens() {
        return alienRepository.getAliens();
    }

    @GET
    @Path("/alien/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alien getAlienById(@PathParam("id") int id){
        return alienRepository.getAlienById(id);
    }

    @POST
    @Path("/alien")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAlien(Alien alien) {
        System.out.println(alien);
        alienRepository.createAlien(alien);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/alien")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAlien(Alien alien) {
        System.out.println(alien);
        if(alienRepository.getAlienById(alien.getId()).getId() == 0){
            alienRepository.createAlien(alien);
            return Response.status(Response.Status.CREATED).build();
        }
        else{
            alienRepository.updateAlien(alien);
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("/alien/{id}")
    public Response deleteAlien(@PathParam("id") int id) {
        if(alienRepository.getAlienById(id).getId() != 0){
            alienRepository.deleteAlien(id);
        }
        return Response.status(202).entity("User deleted successfully !!").build();
    }

    /*@POST
    @Path("/alien")
    @Consumes(MediaType.APPLICATION_JSON)
    public Alien createAlien(Alien alien) {
        System.out.println(alien);
        alienRepository.createAlien(alien);
        return alien;
    }

    @PUT
    @Path("/alien")
    @Consumes(MediaType.APPLICATION_JSON)
    public Alien updateAlien(Alien alien) {
        System.out.println(alien);
        if(alienRepository.getAlienById(alien.getId()).getId() == 0){
            alienRepository.createAlien(alien);
        }
        else{
            alienRepository.updateAlien(alien);
        }
        return alien;
    }

    @DELETE
    @Path("/alien/{id}")
    public void deleteAlien(@PathParam("id") int id) {
        if(alienRepository.getAlienById(id).getId() != 0){
            alienRepository.deleteAlien(id);
        }
    }*/
}
