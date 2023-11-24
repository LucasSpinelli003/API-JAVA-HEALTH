package br.com.healthsolu.resource;
import java.sql.SQLException;
import br.com.healthsolu.exception.BadInfoException;
import br.com.healthsolu.exception.GenderNotFoundException;
import br.com.healthsolu.exception.GrauNotFoundException;
import br.com.healthsolu.exception.IdNotFoundException;
import br.com.healthsolu.model.Login;
import br.com.healthsolu.model.Usuario;
import br.com.healthsolu.service.LoginService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/login") //http://localhost:8080/HealthSolu/api/imc
public class LoginResource {
	
	private LoginService service;
	
	public LoginResource() throws ClassNotFoundException, SQLException {
		service = new LoginService();
	}
	
	
	//POST http://localhost:8080/HealthSolu/api/imc (Cadastrar um produto)
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response verificar(Login login, @Context UriInfo uri) throws ClassNotFoundException, SQLException, IdNotFoundException, GenderNotFoundException, GrauNotFoundException {
	        try {
	            Usuario usuario = service.verificar(login.getEmail(), login.getSenha());
	            if (usuario != null) {
	                // Usuário encontrado, você pode fazer algo com o objeto 'usuario'
	                return Response.ok().entity(usuario).build();
	            }

	            UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
	            return Response.created(uriBuilder.build()).build();
	        } catch (BadInfoException e) {
	            e.printStackTrace();
	            // Retornar o status 400 bad request
	            return Response.status(Response.Status.BAD_REQUEST)
	                    .entity(e.getMessage()).build();
	        }
	    }



}
