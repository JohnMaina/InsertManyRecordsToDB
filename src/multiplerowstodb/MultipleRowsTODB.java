/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplerowstodb;

import java.util.List;

/**
 *
 * @author Maina
 */
public class MultipleRowsTODB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseConnection dbOperations=new DatabaseConnection();
        
        //Create a person object, which we will be inserting to the db
        Person person=new Person();
        List<Person> peopleList;
        
        //Create a list of 50,000 people. You can change this value.
        peopleList=person.createAListOfPeple(50000);
        System.out.println("Time taken in Milliseconds for: ");
        //insert the 50,000 records using normal insert stament
        dbOperations.insertToDb(peopleList);
         //insert the 50,000 records using stored procedure
         dbOperations.insertToDbUsingSP(peopleList);
          //insert the 50,000 records using batch
         dbOperations.insertToDbUsingBatch(peopleList);
          //insert the 50,000 records using batch with transaction processing
           dbOperations.insertToDbUsingBatchTXN(peopleList);
            //insert the 50,000 records using batch with stored procedure
        dbOperations.insertToDbUsingBatchSP(peopleList);
       
        
         
    }
    
}
