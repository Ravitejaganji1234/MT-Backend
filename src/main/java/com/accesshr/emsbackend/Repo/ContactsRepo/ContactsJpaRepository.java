package com.accesshr.emsbackend.Repo.ContactsRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.accesshr.emsbackend.Entity.*;
import java.util.*;
import java.util.List;

@Repository

public interface ContactsJpaRepository extends JpaRepository<Contacts, Integer>{
     List<Contacts> findByContactCreatedEmployeeOrderByPersonNameAsc(String contactCreatedEmployee);
     List<Contacts> findByEmployeeIdOrderByPersonNameAsc(String employeeId);
     List<Contacts> findAllByOrderByPersonNameAsc();
}

