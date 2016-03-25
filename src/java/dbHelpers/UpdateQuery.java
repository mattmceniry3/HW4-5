package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MVP;

public class UpdateQuery {

    private Connection conn;
    
    public UpdateQuery() {
    
    Properties props = new Properties(); //MWC    
    InputStream instr = getClass() .getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    String driver = props.getProperty("driver.name");
    String url = props.getProperty("server.name");
    String username = props.getProperty("user.name");
    String passwd = props.getProperty("user.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    
    public void doUpdate (MVP mvp){
        
        try {
            String query = "UPDATE mvp SET PLAYER_NAME = ?, PLAYER_TEAM = ?, PLAYER_POSITION =?, AGE_AT_MVP =? WHERE ID =?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, mvp.getPLAYER_NAME());
            ps.setString(2, mvp.getPLAYER_TEAM());
            ps.setString(3, mvp.getPLAYER_POSITION());
            ps.setInt(4, mvp.getAGE_AT_MVP());
            ps.setInt(5, mvp.getID());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      
    }
    
    
    
}
