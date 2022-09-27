package nl.han.oose.dea.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.FetchProfile;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import nl.han.oose.dea.rest.services.ItemService;
import nl.han.oose.dea.rest.services.dto.ItemDTO;

import java.net.URI;
import java.util.List;

@Path("/items")
public class  ItemsResource {

    private ItemService itemService = new ItemService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {
        return Response.ok().entity(itemService.getAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public ItemDTO getItem(@PathParam("id") int id) {
//        return itemService.getAll().stream().filter((item) -> item.getId() == id).collect();
        ItemDTO returnValue = null;
        for(ItemDTO item : itemService.getAll()) {
            if(item.getId() == id) {
                returnValue = item;
            }
        }
        return returnValue;
    }


    @POST
    @ApplicationScoped
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(ItemDTO item) {
        itemService.addItem(item);
        return Response.created(UriBuilder.fromPath("items/{id}").build(item.getId())).build();
    }


}
