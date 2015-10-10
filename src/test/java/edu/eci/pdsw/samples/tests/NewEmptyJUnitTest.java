/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Consulta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.eci.pdsw.samples.entities.Paciente;
/**
 *
 * @author hcadavid
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void registroPacienteTest(){
        Date d = new Date();
        java.sql.Date temp = new java.sql.Date(d.getTime());
       
        java.sql.Date temp2 = java.sql.Date.valueOf("500-05-03");
        Paciente p= new Paciente(1234567, "juan", "juan", temp2);
        assertTrue("La fecha y hora de nacimiento del nuevo paciente no es correcta", p.getFechaNacimiento().after(temp));
    }
    
    @Test
    public void registroConsultaTest() throws SQLException{
        Date d = new Date();
        java.sql.Date temp = new java.sql.Date(d.getTime());
        
      
        java.sql.Date temp2 = java.sql.Date.valueOf("3015-05-03");
        
        Consulta c = new Consulta(temp2, "Consulta general sobre dolor de cabeza prolongado");
        
        assertTrue("La fecha y hora de la consulta sobrepasan la fecha actual del sistema", c.getFechayHora().before(temp));
        
                
        
    }
    
    @After
    public void clearDB() throws SQLException{
        try (
                Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=MYSQL", "sa", "")) {
                Statement stmt = conn.createStatement();
                stmt.execute("delete from CONSULTAS");
                stmt.execute("delete from PACIENTES");
                conn.commit();
        }
    }
    
}
