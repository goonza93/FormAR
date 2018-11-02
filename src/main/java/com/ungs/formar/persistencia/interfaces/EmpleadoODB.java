package com.ungs.formar.persistencia.interfaces;

import java.util.List;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;

public interface EmpleadoODB {
	
	public void insert (Empleado empleado);

	public void update (Empleado empleado);
	
	public void delete (Empleado empleado);
	
	public List<Empleado> select();
	
	public Empleado selectByNombre(String nombre);
	
	public Empleado selectByID(Integer id);
	
	public Empleado selectByDNI(String dni);
	
	public List<Empleado> selectByRol(Rol rol);
	
	public Empleado selectByUsuario(String usuario);

	public Empleado selectByEmail(String email);
	
	public List<Empleado> selectByActivo(boolean activo);
	
}