package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Operador extends Usuario {

	public Operador(@JsonProperty(value="usuario") String usuario,
			@JsonProperty(value="contrasena")String contrasena,
                        @JsonProperty(value="idUsuario") Long idUsuario) 
        {
		super(usuario, contrasena, idUsuario);
	}
}
