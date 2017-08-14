/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplerowstodb;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maina
 */
public class Person {
    private String fname;
     private String surname;

    public String getFname() {
        return fname;
    }

    public String getSurname() {
        return surname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    private String mobileNumber;
    
    protected List<Person> createAListOfPeple(int numberOfRows){
        List<Person> listOfPeople=null;
        try{
             listOfPeople=new ArrayList();
        for(int i=0;i<numberOfRows; i++){
           
            Person person=new Person();
             person.fname="personfname"+i+(1);   
             person.surname="personsurname"+i+(1);
             person.mobileNumber="111111111111"+i;
             listOfPeople.add(person);
        }
        }catch(Exception e){
        System.out.println("");
    }
        return listOfPeople;
        
    }
   
    
}
