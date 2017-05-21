package co.com.analisispatronesdiseno.main;

import java.util.Scanner;

import co.com.analisispatronesdiseno.decorator.Hamburguesa;
import co.com.analisispatronesdiseno.decorator.impl.Lechuga;
import co.com.analisispatronesdiseno.decorator.impl.Queso;
import co.com.analisispatronesdiseno.decorator.impl.Tomate;

public class Main {

	private static Scanner scanner;

	/**
	 * Prueba patron decorator
	 */
	public static void testingDecorator() {
		Hamburguesa hamburguesa = new Hamburguesa();
		scanner = new Scanner(System.in);
		int opcion = 0;
		
		do {
			System.out.println("Con su hamburguesa seleccione una adición:");
			System.out.println("1. Lechuga, 2. Queso, 3. Tomate, 0. Terminar Pedido");
			opcion = scanner.nextInt();
			
			switch (opcion) {
			case 0:
				break;
			case 1:
				hamburguesa = new Lechuga(hamburguesa);
				break;
			case 2: 
				hamburguesa = new Queso(hamburguesa);
				break;
			case 3:
				hamburguesa = new Tomate(hamburguesa);
				break;
			default:
				System.out.println("Opción no válida!!!");
			}
		} while (opcion != 0);
		
		System.out.println();
		System.out.println("Entregando...");	
		System.out.println(hamburguesa.getDescripcion());
		System.out.println("Disfrute su pedido");
	}
	
	public static void main(String[] args) {
		
		/**
		 * Testing decorator
		 */
		testingDecorator();

	}

}
