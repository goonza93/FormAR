package com.ungs.formar.persistencia.interfaces;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;

public interface RecadoOBD {

	public void insert (Recado recado);
	
	public void update (Recado recado);
	
	public void delete (Recado recado);
	
	public List<Recado> select();
	
	public List<Recado> selectByReceptor(Empleado receptor);
	
	public List<Recado> selectByEmpleadoReceptorSinLeer(Empleado receptor);
	
	public List<Recado> selectByEmpleadoReceptorArchivado(Empleado empleado, Empleado receptor, boolean archivado);
	
	public List<Recado> selectByEmpleadoEmisorArchivado(Empleado empleado, Empleado receptor, boolean archivado);
	
}