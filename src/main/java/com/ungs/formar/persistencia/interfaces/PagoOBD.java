package com.ungs.formar.persistencia.interfaces;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Pago;

public interface PagoOBD {
	
	public void insert(Pago pago);

	public void update(Pago pago);
	
	public void delete(Pago pago);

	public List<Pago> select();
	
}