package com.accesshr.emsbackend.EmployeeController.ContactsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
  import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.accesshr.emsbackend.Entity.*;
import com.accesshr.emsbackend.Service.ContactsService.*;



  @RestController

  @CrossOrigin
  @RequestMapping("apis/employees/contacts")

public class ContactsController {
    @Autowired 
    public ContactsJpaService contactsService;

    @GetMapping(value="/contacts",produces = "application/json")
    public ArrayList<Contacts> getContacts(){
        return contactsService.getContacts();
    }

    @GetMapping(value="/contacts/{contactsId}",produces = "application/json")

    public Contacts getContactsById(@PathVariable int contactsId){
      return contactsService.getContactsById(contactsId);
    }

    @GetMapping(value="/contactsCreated/{contactCreatedEmployee}",produces = "application/json")
    public List<Contacts> getContactsByCreatedBy(@PathVariable String contactCreatedEmployee) {
        return  contactsService.getContactsByCreatedBy(contactCreatedEmployee);
    }

    @GetMapping(value="/contactsBy/{employeeId}",produces = "application/json")

    public List<Contacts> getContactsByEmployeeId(@PathVariable String employeeId){
        return  contactsService.getContactsByEmployeeId(employeeId);
    }
    
    
    
    @PostMapping(value="/contacts",produces = "application/json")

    public Contacts addContacts(@RequestBody Contacts contacts){
      return contactsService.addContacts(contacts);
    }

    @PutMapping(value="/contacts/{contactsId}",produces = "application/json")

    public Contacts updateContacts(@PathVariable int contactsId,@RequestBody Contacts contacts){
      return contactsService.updateContacts(contactsId,contacts);
    }

    @DeleteMapping(value="/contacts/{contactsId}",produces = "application/json")

    public void deleteContacts(@PathVariable int contactsId){
      contactsService.deleteContacts(contactsId);
    }
}

