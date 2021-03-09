package agenda;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class AccesoDatos {
	private static MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	private static DB database = mongoClient.getDB("agenda");
	private static DBCollection tablacontacto = database.getCollection("contactos");

	public static void insertarContacto(Contacto contacto) {
		DBObject objeto = new BasicDBObject("nombre", contacto.getNombre()).append("telefono", contacto.getTelefono()).append("email", contacto.getEmail());
		tablacontacto.insert(objeto);
	}
	
	public static List<Contacto> listarContactos() {
		List<Contacto> listaContactos = new ArrayList<Contacto>();
		Iterable<DBObject> cursor = tablacontacto.find();
		
		for(DBObject dbobject : cursor) {
			Contacto c = new Contacto((String) dbobject.get("nombre"),(String) dbobject.get("telefono"), (String) dbobject.get("email"));
			listaContactos.add(c);
		}
		return listaContactos;
	}

	public static void borrarContacto(String nombre) {
		DBObject query = new BasicDBObject("nombre", nombre);
		tablacontacto.remove(query);
	}

}
