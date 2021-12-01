/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.ws;

import com.ejb.ProductosFacadeLocal;
import com.entidades.Productos;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author USER
 */
@Path("productosServices")
public class ProductosServices {

    @Context
    private UriInfo context;
    @Inject // cdi de la interfaz local del EJB
    private ProductosFacadeLocal service;

    /**
     * Creates a new instance of ProductosServices
     */
    public ProductosServices() {
    }

    /**
     * Devuelve todos los datos
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getJson() {
        try {
            List<Productos> findAll = service.findAll();
            GenericEntity<List<Productos>> genericEntity = new GenericEntity<List<Productos>>(findAll) {
            };
            return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * POST para crear un nuevo recurso de Productos
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(Productos producto) {
        service.create(producto);
        String result = "Record entered: "+ producto;
        return Response.status(201).entity(result).build();
    }
}
