/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplerowstodb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maina
 */
public class DatabaseConnection {
    
    private static Connection getconnection(){
        //String dbURL = "jdbc:sqlserver://localhost\\JOHN-PC";
        String dbURL = "jdbc:sqlserver://JOHN-PC;databaseName=personDB";
        Properties properties = new Properties();
        properties.put("user", "sa");
        properties.put("password", "yourpasswordhere");
        Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection(dbURL, properties);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    return dbConnection;
    }
    
    public void insertToDb(List<Person> people){
        Connection dbConnection=null;
        try {
             dbConnection=DatabaseConnection.getconnection();
            if(!dbConnection.isClosed()){
                long timeNow=System.currentTimeMillis();
                for(Person person: people){
                    String sql="INSERT INTO TBPERSON (fname,surname,mobileNumber) VALUES(?,?,?)";
                    PreparedStatement stmt=dbConnection.prepareStatement(sql);
                    stmt.setString(1, person.getFname());
                    stmt.setString(2, person.getSurname());
                    stmt.setString(3, person.getMobileNumber());
                    stmt.execute();
                }
                long timeElapsed=(System.currentTimeMillis())-timeNow;
                 System.out.println("Insert using prepared statement= "+timeElapsed);
            }
           
                
                } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(!dbConnection.isClosed()){
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
     public void insertToDbUsingSP(List<Person> people){
        Connection dbConnection=null;
        try {
             dbConnection=DatabaseConnection.getconnection();
            if(!dbConnection.isClosed()){
                //insert something
                long timeNow=System.currentTimeMillis();
                for(Person person: people){
                    String sql="{call saveMultipleRows(?,?,?)}";
                  CallableStatement stmt=dbConnection.prepareCall(sql);
                    stmt.setString(1, person.getFname());
                    stmt.setString(2, person.getSurname());
                    stmt.setString(3, person.getMobileNumber());
                    stmt.execute();
                }
                long timeElapsed=(System.currentTimeMillis())-timeNow;
                 System.out.println("Insert prepared statement with SP= "+timeElapsed);
            }
           
                
                } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(!dbConnection.isClosed()){
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
      public void insertToDbUsingBatch(List<Person> people){
        Connection dbConnection=null;
        try {
             dbConnection=DatabaseConnection.getconnection();
            if(!dbConnection.isClosed()){
                //insert something
                long timeNow=System.currentTimeMillis();
                int i=0;
                for(Person person: people){
                     String sql="INSERT INTO TBPERSON (fname,surname,mobileNumber) VALUES(?,?,?)";
                  PreparedStatement stmt=dbConnection.prepareStatement(sql);
                    stmt.setString(1, person.getFname());
                    stmt.setString(2, person.getSurname());
                    stmt.setString(3, person.getMobileNumber());
                    stmt.addBatch();
                     i++;
                    if(i%1000==0 || i==people.size()){
                    stmt.executeBatch();
                    }
                }
                long timeElapsed=(System.currentTimeMillis())-timeNow;
                 System.out.println("Insert prepared statement with BATCH= "+timeElapsed);
            }
           
                
                } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(!dbConnection.isClosed()){
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
      public void insertToDbUsingBatchTXN(List<Person> people){
        Connection dbConnection=null;
        try {
             dbConnection=DatabaseConnection.getconnection();
            if(!dbConnection.isClosed()){
                //set auto commit off
                dbConnection.setAutoCommit(false);
                //insert something
                long timeNow=System.currentTimeMillis();
                int i=0;
                for(Person person: people){
                     String sql="INSERT INTO TBPERSON (fname,surname,mobileNumber) VALUES(?,?,?)";
                  PreparedStatement stmt=dbConnection.prepareStatement(sql);
                    stmt.setString(1, person.getFname());
                    stmt.setString(2, person.getSurname());
                    stmt.setString(3, person.getMobileNumber());
                    stmt.addBatch();
                     i++;
                    if(i%1000==0 || i==people.size()){
                    stmt.executeBatch();
                    }
                }
                dbConnection.commit();
                long timeElapsed=(System.currentTimeMillis())-timeNow;
                 System.out.println("Insert using prepared statement with batch and transaction processing= "+timeElapsed);
            }
           
                
                } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(!dbConnection.isClosed()){
                     dbConnection.setAutoCommit(true);
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
      
       public void insertToDbUsingBatchSP(List<Person> people){
        Connection dbConnection=null;
        try {
             dbConnection=DatabaseConnection.getconnection();
            if(!dbConnection.isClosed()){
                //insert something
                long timeNow=System.currentTimeMillis();
                int i=0;
                for(Person person: people){
                     String sql="{call saveMultipleRows(?,?,?)}";
                  CallableStatement stmt=dbConnection.prepareCall(sql);
                    stmt.setString(1, person.getFname());
                    stmt.setString(2, person.getSurname());
                    stmt.setString(3, person.getMobileNumber());
                    stmt.addBatch();
                     i++;
                    if(i%1000==0 || i==people.size()){
                    stmt.executeBatch();
                    }
                }
                long timeElapsed=(System.currentTimeMillis())-timeNow;
                 System.out.println("Insert using prepared statement with batch and stored procedure= "+timeElapsed);
            }
           
                
                } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(!dbConnection.isClosed()){
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
}
