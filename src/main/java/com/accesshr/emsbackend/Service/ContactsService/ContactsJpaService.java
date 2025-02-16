package com.accesshr.emsbackend.Service.ContactsService;

import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
  import org.springframework.web.server.ResponseStatusException;
  import java.util.*;
  import com.accesshr.emsbackend.Entity.*;
  import com.accesshr.emsbackend.Repo.ContactsRepo.*;

@Service
public class ContactsJpaService implements ContactsRepository{
    @Autowired 
    public ContactsJpaRepository contactsRepository;

    @Override 

    public void deleteContacts(int contactId){
      try{
        contactsRepository.deleteById(contactId);
      }catch (Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    @Override 
    public Contacts addContacts(Contacts contacts){
      contactsRepository.save(contacts);

      return contacts;
    }

    @Override 
    public Contacts getContactsById(int contactId){
      try{
        return contactsRepository.findById(contactId).get();
      }
      catch (Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    @Override 
    public ArrayList <Contacts> getContacts(){
        return (ArrayList<Contacts>) contactsRepository.findAllByOrderByPersonNameAsc();
    }

    @Override 
    public List<Contacts> getContactsByCreatedBy(String contactCreatedEmployee){
      return (List<Contacts>) contactsRepository.findByContactCreatedEmployeeOrderByPersonNameAsc(contactCreatedEmployee);
    }

    @Override
    public  List<Contacts> getContactsByEmployeeId(String employeeId){
        return  (List<Contacts>) contactsRepository.findByEmployeeIdOrderByPersonNameAsc(employeeId);

    }
    

    
    @Override 

    public Contacts updateContacts(int contactId, Contacts contacts){
      try{
        Contacts newContacts=contactsRepository.findById(contactId).get();
        if(contacts.getContactCreatedEmployee()!=null){
          newContacts.setContactCreatedEmployee(contacts.getContactCreatedEmployee());
        }
        if(contacts.getPersonName()!=null){
            newContacts.setPersonName(contacts.getPersonName());
          }
          if(contacts.getRelation()!=null){
            newContacts.setRelation(contacts.getRelation());
          }
          if (contacts.getCountryCode()!=null){
            newContacts.setCountryCode(contacts.getCountryCode());
          }
          if(contacts.getPersonMobile()!=null){
            newContacts.setPersonMobile(contacts.getPersonMobile());
          }
          if(contacts.getPersonEmail()!=null){
            newContacts.setPersonEmail(contacts.getPersonEmail());
          }
          if(contacts.getCompany()!=null){
            newContacts.setCompany(contacts.getCompany());
          }

          if(contacts.getCountry()!=null){
            newContacts.setCountry(contacts.getCountry());
          }
          if(contacts.getState()!=null){
            newContacts.setState(contacts.getState());
          }
          if(contacts.getAddress()!=null){
            newContacts.setAddress(contacts.getAddress());
          }
          if(contacts.getPincode()!=null){
            newContacts.setPincode(contacts.getPincode());
          }
          if(contacts.getPincode()!=null){
            newContacts.setPincode(contacts.getPincode());
          }
          if(contacts.getBankName()!=null){
            newContacts.setBankName(contacts.getBankName());
          }
          if(contacts.getBankCode()!=null){
            newContacts.setBankCode(contacts.getBankCode());
          }
          if(contacts.getAccountNumber()!=null){
            newContacts.setAccountNumber(contacts.getAccountNumber());
          }
          if(contacts.getAccountName()!=null){
            newContacts.setAccountName(contacts.getAccountName());
          }
          if(contacts.getOtherAccountInformation()!=null){
            newContacts.setOtherAccountInformation(contacts.getOtherAccountInformation());
          }
          if(contacts.getBankAddress()!=null){
            newContacts.setBankAdress(contacts.getBankAddress());
          }
          
        contactsRepository.save(newContacts);
        return newContacts;
      }catch (Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

}

