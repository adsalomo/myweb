package co.com.analisispatronesdiseno.decorator.iface;

import co.com.analisispatronesdiseno.decorator.Hamburguesa;

public abstract class DecoratorHamburguesa extends Hamburguesa {
	
	/**
	 * Obtiene la descripcion de la hamburguesa
	 */
	public abstract String getDescripcion();
}
