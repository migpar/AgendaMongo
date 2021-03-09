package agenda;

import java.util.List;
import java.util.Scanner;

public class LecturaEscritura {

	private static Scanner sc = new Scanner(System.in);
	private static final int INTRODUCIR_CONTACTO = 1;
	private static final int MOSTRAR_AGENDA = 2;
	private static final int BORRAR = 3;
	private static final int SALIR = 5;

	public static void menu() {
		int opcion = LecturaEscritura.pedirOpcion();
		while (opcion != SALIR) {

			switch (opcion) {
			case INTRODUCIR_CONTACTO:
				Contacto contacto = LecturaEscritura.pedirContacto();
				AccesoDatos.insertarContacto(contacto);
				break;
			case MOSTRAR_AGENDA:
				List<Contacto> lista = AccesoDatos.listarContactos();
				LecturaEscritura.escribirContactos(lista);
				break;
			case BORRAR:
				String nombre = LecturaEscritura.pedirNombre();
				AccesoDatos.borrarContacto(nombre);
				break;

			case SALIR:
				System.out.println("Vuelva Pronto");
				break;

			default:
				System.out.println("Elija una opcion valida");
				break;
			}

			opcion = LecturaEscritura.pedirOpcion();
		}

	}

	private static String pedirNombre() {
		System.out.println("Ecriba el nombre del contacto");
		return sc.nextLine();
	}

	private static void escribirContactos(List<Contacto> lista) {
		for (Contacto c : lista) {
			System.out.println("nombre: " + c.getNombre()+ " telefono: " + c.getTelefono() + " email: " + c.getEmail() + "\n");
		}
		
	}

	private static Contacto pedirContacto() {
		System.out.println("escriba el nombre del contacto");
		String nombre = sc.nextLine();
		System.out.println("escriba el telefono del contacto");
		String telefono = sc.nextLine();
		System.out.println("escriba el email del contacto");
		String email = sc.nextLine();
		Contacto c = new Contacto(nombre, telefono, email);
		return c;
	}

	private static int pedirOpcion() {
		System.out.println("Diga que opcion desea elegir:\n"+INTRODUCIR_CONTACTO+"- introducir contacto\n"+MOSTRAR_AGENDA+"- Mostrar agenda\n" + 
					BORRAR+"- Borrar contacto\n"+SALIR+"- Salir");
		int n = Integer.parseInt(sc.nextLine());
		return n;
	}

}
