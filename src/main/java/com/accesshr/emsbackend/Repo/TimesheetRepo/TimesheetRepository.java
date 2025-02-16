package com.accesshr.emsbackend.Repo.TimesheetRepo;


import com.accesshr.emsbackend.Entity.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    // Custom query to find timesheets by employee ID
    List<Timesheet> findByEmployeeId(String employeeId);

    List<Timesheet> findByManagerId(String managerId);


    List<Timesheet> findByEmployeeIdAndStartDateAndEndDate(String employeeId, LocalDate startDate, LocalDate endDate);
    boolean existsByEmployeeIdAndStartDateAndEndDateAndStatus(String employeeId, LocalDate startDate, LocalDate endDate,Timesheet.Status status);

    @Query("SELECT t FROM Timesheet t WHERE t.employeeId = :employeeId AND " +
            "t.startDate BETWEEN :startDate AND :endDate")
    List<Timesheet> findByEmployeeIdAndDateRange(@Param("employeeId") String employeeId,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);


}
