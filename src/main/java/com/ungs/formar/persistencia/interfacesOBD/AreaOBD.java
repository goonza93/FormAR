package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Area;

public interface AreaOBD {

	public List<Area> select();
	
	public Area selectByID(Integer ID);

	public void insert(Area area);

	public void update(Area area);

	public void delete(Area area);
		
}