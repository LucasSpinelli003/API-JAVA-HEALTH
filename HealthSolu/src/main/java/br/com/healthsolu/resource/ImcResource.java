package br.com.healthsolu.resource;
import java.sql.SQLException;
import java.util.List;

import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.GenderNotFoundException;
import br.com.healthsolu.exception.GrauNotFoundException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.model.Imc;
import br.com.healthsolu.service.ImcService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/imc") //http://localhost:8080/HealthSolu/api/imc
public class ImcResource {
	
	private ImcService service;
	
	public ImcResource() throws ClassNotFoundException, SQLException {
		service = new ImcService();
	}
	
	//POST http://localhost:8080/HealthSolu/api/imc (Cadastrar um produto)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Imc imc, @Context UriInfo uri) throws ClassNotFoundException, SQLException, IdNotFoundException, GenderNotFoundException, GrauNotFoundException {
		try {
			service.cadastrar(imc);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(imc.getId()));
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			//Retornar o status 400 bad request
			return Response.status(Status.BAD_REQUEST)
								.entity(e.getMessage()).build();
		}
	}
	
	//GET http://localhost:8080/HealthSolu/api/imc   (Listar todos os produtos)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Imc> lista() throws ClassNotFoundException, SQLException, IdNotFoundException {
		return service.listar();
	}

	//GET http://localhost:8080/HealthSolu/api/imc /1 (Pesquisar pelo Id)
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



}
