package com.ungs.formar.persistencia.mysqlOBD;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Pdf;
import com.ungs.formar.persistencia.interfacesOBD.PdfOBD;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PdfODBMySQL  extends ODB implements PdfOBD{

	public Pdf traerPdf(Integer id) {
		Pdf ret = new Pdf();
		String sql = "SELECT * FROM for_pdf WHERE (contenido_ID = "+id+");";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = getConexion().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ret.setContenidoID(rs.getInt(1));
				ret.setNombrepdf(rs.getString(2));
				ret.setArchivopdf(rs.getBytes(3));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				ps.close();
				rs.close();
				desconectar();
			} catch (Exception ex) {
			}
		}
		return ret;
	}
	
    /*Metodo listar*/
    public ArrayList<Pdf> Listar_Pdf() {
        ArrayList<Pdf> list = new ArrayList<Pdf>();
        
        String sql = "SELECT * FROM for_pdf";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pdf pdf = new Pdf();
                pdf.setContenidoID(rs.getInt(1));
                pdf.setNombrepdf(rs.getString(2));
                pdf.setArchivopdf(rs.getBytes(3));
                list.add(pdf);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                desconectar();
            } catch (Exception ex) {
            }
        }
        return list;
    }


    /*Metodo agregar*/
    public void insert(Pdf vo) {
        String sql = "INSERT INTO for_pdf (contenido_ID, nombrepdf, archivopdf) VALUES(?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setInt(1, vo.getContenidoID());
            ps.setString(2, vo.getNombrepdf());
            ps.setBytes(3, vo.getArchivopdf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                desconectar();
            } catch (Exception ex) {
            }
        }
    }


    /*Metodo Modificar*/
    public void update(Pdf vo) {
    	
        String sql = "UPDATE for_pdf SET nombrepdf = ?, archivopdf = ? WHERE contenido_ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setString(1, vo.getNombrepdf());
            ps.setBytes(2, vo.getArchivopdf());
            ps.setInt(3, vo.getContenidoID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                desconectar();
            } catch (Exception ex) {
            }
        }
    }
    /*
    public void Modificar_Pdf2(Pdf vo) {
    	 ODB conec = new ODB();
        String sql = "UPDATE for_pdf SET nombrepdf = ? WHERE contenido_ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getNombrepdf());
            ps.setInt(2, vo.getContenidoID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }*/

    /*Metodo Eliminar*/
    public void delete(Pdf vo) {
    	
        String sql = "DELETE FROM for_pdf WHERE contenido_ID = ?;";
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setInt(1, vo.getContenidoID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                desconectar();
            } catch (Exception ex) {
            }
        }
    }

    //Permite mostrar PDF contenido en la base de datos
    public String abrir(Integer id) {
    	PreparedStatement ps = null;
        ResultSet rs = null;
        byte[] b = null;
        String nombre = "";

        try {
            ps = getConexion().prepareStatement("SELECT archivopdf,nombrepdf FROM for_pdf WHERE contenido_ID = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
                nombre = rs.getString(2);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream(nombre);
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();
            ps.close();
            rs.close();
            desconectar();

        } catch (IOException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        } catch (NumberFormatException ex) {
        	System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        } catch (SQLException ex){
        	System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        }
        return nombre;
    }
    
}