package actividad1.act1_texto;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class Actividad1_1_Charset {

	/**
	 * Convierte el contenido de un archivo de texto codificado en ISO-8859-1
	 * a un nuevo archivo codificado en UTF-8.
	 * <p>
	 * El método lee el archivo de origen línea a línea y escribe cada línea
	 * en el archivo de destino utilizando la codificación UTF-8. Al finalizar,
	 * muestra por consola el número de líneas convertidas.
	 * </p>
	 *
	 * @param origen archivo de origen codificado en ISO-8859-1
	 * @param destino archivo de destino que se generará codificado en UTF-8
	 */
	public static void convertirLegacy(File origen, File destino) {
		// Bloque try-with-resources: Asegura el cierre implícito de los recursos
		try (
				BufferedReader br = new BufferedReader(
						new FileReader(origen, StandardCharsets.ISO_8859_1));
				BufferedWriter bw = new BufferedWriter(
						new FileWriter(destino, StandardCharsets.UTF_8));
				){
			String linea;
			int numLinea = 0; 
			
			while ((linea = br.readLine()) != null) {
				bw.write(linea);
				bw.newLine();
				numLinea++;
			}
			
			System.out.println("Migración completada: " + numLinea + " convertidas a UTF-8.");
			
		} catch (FileNotFoundException e) {
			System.err.println("Error: Archivo de origen no localizable: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error crítico de Entrada/Salida: " + e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		Path origen = Path.of("datos","usuarios_legacy.txt");
		Path destino = Path.of("datos", "usuarios_utf8.txt");
		
		convertirLegacy(origen.toFile(), destino.toFile());
	}

}
