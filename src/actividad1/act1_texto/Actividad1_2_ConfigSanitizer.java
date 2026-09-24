package actividad1.act1_texto;

import java.io.*;
import java.nio.file.Path;

/* Actividad
 * Sanitizar ficheros de configuración .ini sucios antes de que una aplicación los cargue,
 * eliminando líneas vacías, comentarios ( # o ; ) y espacios en blanco, y normalizando las claves a mayúsculas.
 */
/**
 * Utilidad para sanitizar archivos de configuración en formato INI.
 * <p>
 * El proceso de sanitización elimina las líneas vacías y las líneas
 * de comentario que comienzan por {@code #} o {@code ;}. Además,
 * elimina los espacios innecesarios alrededor de las claves y valores
 * y convierte las claves a mayúsculas.
 * </p>
 *
 * <p>
 * Por ejemplo, una línea como:
 * </p>
 *
 * <pre>
 *     usuario = administrador
 * </pre>
 *
 * se transforma en:
 *
 * <pre>
 * USUARIO=administrador
 * </pre>
 *
 *  @author Carlos Jesús Ruiz Buscató
 *  @version 1.0
 *  @see <a href="mailto:carlos@ruizbuscato.dev">Contacto</a>
 */
public class Actividad1_2_ConfigSanitizer {

	/**
	 * Sanitiza un archivo de configuración INI y escribe el resultado
	 * en un nuevo archivo.
	 * <p>
	 * Durante el procesamiento se realizan las siguientes operaciones:
	 * </p>
	 * <ul>
	 *     <li>Se eliminan las líneas vacías.</li>
	 *     <li>Se ignoran los comentarios que comienzan por {@code #} o {@code ;}.</li>
	 *     <li>Se eliminan los espacios al principio y al final de cada línea.</li>
	 *     <li>Se separa cada propiedad utilizando el primer carácter {@code =}.</li>
	 *     <li>Se eliminan los espacios alrededor de la clave y del valor.</li>
	 *     <li>Las claves se convierten a mayúsculas.</li>
	 * </ul>
	 *
	 * <p>
	 * Si el archivo de origen no existe o se produce un error de
	 * entrada/salida, el método muestra el mensaje de error correspondiente
	 * por la salida de error estándar.
	 * </p>
	 *
	 * @param origen archivo INI original que se desea sanitizar
	 * @param destino archivo en el que se guardará el contenido sanitizado
	 */
	public static void sanitizarIni(File origen, File destino) {
		
		try(
			BufferedReader br = new BufferedReader(
					new FileReader(origen));
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(destino))
		){
			String linea;
			
			while ((linea = br.readLine()) != null) {
				linea = linea.trim();
				
				if (linea.isEmpty() || linea.startsWith("#") || linea.startsWith(";")) {
					continue;
				}
				
				String[] partes = linea.split("=", 2);
				String lineaSana = partes[0].trim().toUpperCase() + "=" + partes[1].trim();
				bw.write(lineaSana);
				bw.newLine();
				
			}
			
			System.out.println("Archivo .ini sanitizado.");
			

		} catch (FileNotFoundException e) {
			System.err.println("Error: Archivo de origen no localizable: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error crítico de Entrada/Salida: " + e.getMessage());
		}
		
	}
	
	/**
	 * Punto de entrada de la aplicación.
	 * <p>
	 * Define como archivo de origen {@code datos/CONFIG.INI} y como
	 * archivo de destino {@code datos/CONFIG_CLEAN.INI}, y ejecuta
	 * el proceso de sanitización.
	 * </p>
	 *
	 * @param args argumentos recibidos desde la línea de comandos;
	 *             actualmente no se utilizan
	 */
	public static void main(String[] args) {
		
		Path origen = Path.of("datos","CONFIG.INI");
		Path destino = Path.of("datos","CONFIG_CLEAN.INI");
		
		sanitizarIni(origen.toFile(),destino.toFile());
	}

}
