package com.accesshr.emsbackend.Repo.LeaveRepo;

import com.accesshr.emsbackend.Entity.LeaveSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveSheetRepository extends JpaRepository<LeaveSheet, Integer> {
}
