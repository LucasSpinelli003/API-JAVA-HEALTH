package br.com.healthsolu.resource;
import java.sql.SQLException;
import java.util.List;

import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.model.Usuario;
import br.com.healthsolu.service.UsuarioService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/usuario") //http://localhost:8080/HealthSolu/api/usuario
public class UsuarioResource {
	
	private UsuarioService service;
	
	public UsuarioResource() throws ClassNotFoundException, SQLException {
		service = new UsuarioService();
	}
	
	//POST http://localhost:8080/HealthSolu/api/usuario (Cadastrar um produto)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Usuario login, @Context UriInfo uri) throws ClassNotFoundException, SQLException, IdNotFoundException {
		try {
			service.cadastrar(login);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(login.getId()));
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			//Retornar o status 400 bad request
			return Response.status(Status.BAD_REQUEST)
								.entity(e.getMessage()).build();
		}
	}
	
	//GET http://localhost:8080/HealthSolu/api/usuario  (Listar todos os produtos)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> lista() throws ClassNotFoundException, SQLException, IdNotFoundException {
		return service.listar();
	}
	//GET http://localhost:8080/HealthSolu/api/usuario/1 (Pesquisar pelo Id)
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response busca(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			return Response.ok(service.pesquisar(id)).build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
		//PUT http://localhost:8080/Solutech/api/usuario/1 (Atualizar um produto)
		@PUT
		@Path("/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response atualizar(Usuario usuario, @PathParam("id") int id) throws ClassNotFoundException, SQLException {
			try {
				usuario.setId(id);
				service.atualizar(usuario);
				return Response.ok().build();
			} catch (IdNotFoundException e) {
				return Response.status(Status.NOT_FOUND).build();
			} catch (BadInfoException e) {
				return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
			}
		}





}
