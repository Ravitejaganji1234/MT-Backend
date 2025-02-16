package com.accesshr.emsbackend.Repo.ContactsRepo;

import java.util.*;
import com.accesshr.emsbackend.Entity.*;

public interface ContactsRepository {
    ArrayList<Contacts> getContacts();
    Contacts getContactsById(int contactId);
    Contacts addContacts(Contacts contacts);
    Contacts updateContacts(int contactId, Contacts contacts);
    void deleteContacts(int contactId); 
    List<Contacts> getContactsByCreatedBy(String contactCreatedEmployee);
    List<Contacts> getContactsByEmployeeId(String employeeId);
}