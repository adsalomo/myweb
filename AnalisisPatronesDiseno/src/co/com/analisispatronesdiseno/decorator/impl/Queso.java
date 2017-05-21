package co.com.analisispatronesdiseno.decorator.impl;

import co.com.analisispatronesdiseno.decorator.Hamburguesa;
import co.com.analisispatronesdiseno.decorator.iface.DecoratorHamburguesa;

public class Queso extends DecoratorHamburguesa {

	private Hamburguesa hamburguesa;
	
	public Queso(Hamburguesa hamburguesa) {
		this.hamburguesa = hamburguesa;
	}
	
	@Override
	public String getDescripcion() {
		return hamburguesa.getDescripcion() + " + Queso";
	}

}
