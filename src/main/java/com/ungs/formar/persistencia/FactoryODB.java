package com.ungs.formar.persistencia;

import com.ungs.formar.persistencia.interfacesOBD.UsuarioODB;
import com.ungs.formar.persistencia.mysqlOBD.UsuarioODBMySQL;

public class FactoryODB {
	
	public static UsuarioODB getUsuarioODB() {
		return new UsuarioODBMySQL();
	}

}