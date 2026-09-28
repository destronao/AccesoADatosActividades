package actividad1.act1_texto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Actividad1_3_FacturaPrintWriter {

	public static void main(String[] args) {

		File archivo = new File("datos/factura.txt");

		try (PrintWriter pw = new PrintWriter(archivo, StandardCharsets.UTF_8)) {

			// Cabecera
			
			imprimirCabecera(pw);

			// Artículos

			imprimirArticulo(pw,"PORT-01","Portátil 15 Pulgadas",750.50,2);
			imprimirArticulo(pw,"RAT-02","Ratón Inalámbrico",18.90,5);
			imprimirArticulo(pw,"MON-03","Monitor 27 IPS",199.99,1);
			
			// Cálculos
			
			double baseImponible =
					750.50 * 2
					+ 18.90 * 5
					+ 199.99;

			double iva = baseImponible * 0.21;
			double total = baseImponible + iva;

			imprimirSeparador(pw);

			imprimirTotales(pw, "Base imponible:", baseImponible);
			imprimirTotales(pw, "IVA (21%):", iva);
			imprimirTotales(pw, "TOTAL fACTURA:", total);

			System.out.println("Factura generada correctamente.");

		} catch (FileNotFoundException e) {
			System.err.println("No se ha podido crear el archivo.");
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private static void imprimirArticulo(PrintWriter pw, String nombre, String descripcion, double precio, int cantidad) {
		
		String cantidadTexto;
		double total;
		if (cantidad == 1) {
			cantidadTexto = cantidad + " ud";
			total = precio;
		} else {
			cantidadTexto = cantidad + " uds";
			total = precio * cantidad;
		}
		
		pw.printf("%-10s | %-25s | %8.2f€ | %8s | %8.2f€%n",
				nombre,
				descripcion,
				precio,
				cantidadTexto,
				total);
	}

	private static void imprimirSeparador(PrintWriter pw) {
		pw.println("-----------------------------------------------------------------------------");
	}

	private static void imprimirTotales(PrintWriter pw, String nombre, double total) {
		pw.printf("%-61s %10.2f€%n",
				nombre,
				total);
	}

	private static void imprimirCabecera(PrintWriter pw) {
		pw.println("=============================================================================");
		pw.println("                          FACTURA COMERCIAL");
		pw.println("=============================================================================");

		pw.printf("%-10s | %-25s | %9s | %8s | %8s%n",
				"CÓDIGO",
				"DESCRIPCIÓN",
				"PRECIO",
				"CANTIDAD",
				"TOTAL");

		imprimirSeparador(pw);
	}
}