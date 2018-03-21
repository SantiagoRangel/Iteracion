package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohaTM;
import vos.PersonaNatural;

@Path( "personasNaturales" )
public class PersonaNaturalService {

	@Context
	private ServletContext context;

	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	private String doBuenMessaje(){
		return "{ \"Bien\": \" borró el personasNaturales \"}";
	}
	

	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPersonasNaturales() {
		
		try {
			AlohaTM tm = new AlohaTM(getPath());
			
			List<PersonaNatural> personasNaturaless;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			personasNaturaless = tm.getAllPersonasNaturales();
			return Response.status(200).entity(personasNaturaless).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarPersonaNatural(PersonaNatural personasNaturales) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.registrarPersonaNatural(personasNaturales);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(personasNaturales).build();
	}
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getPersonaNaturalById( @PathParam( "id" ) Long id )
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			PersonaNatural apto = tm.getPersonaNaturalById( id );
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public Response borrarPersonaNatural( @PathParam( "id" ) Long id ) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.borrarPersonaNatural(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(doBuenMessaje()).build();
	}
}
