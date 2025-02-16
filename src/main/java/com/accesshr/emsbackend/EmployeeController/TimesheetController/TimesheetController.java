package com.accesshr.emsbackend.EmployeeController.TimesheetController;


import com.accesshr.emsbackend.Dto.TimesheetDTO.TimesheetDTO;
import com.accesshr.emsbackend.Entity.Timesheet;

import com.accesshr.emsbackend.Service.TimesheetService.TimesheetEmailService;
import com.accesshr.emsbackend.Service.TimesheetService.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/timesheets")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @Autowired
    private TimesheetEmailService timesheetEmailService;




    // Endpoint to create a new timesheet
    @PostMapping
    public ResponseEntity<TimesheetDTO> createTimesheets(@RequestBody TimesheetDTO timesheetDTO) {
        // Call service to create a new timesheet and get the created TimesheetDTO
        TimesheetDTO createdTimesheet = timesheetService.createTimesheet(timesheetDTO);

        // Return a ResponseEntity with the created TimesheetDTO and HTTP status 201 (Created)
        return new ResponseEntity<>(createdTimesheet, HttpStatus.CREATED);
    }

    @GetMapping("/employeeId/{employeeId}/total/timesheets")
    public LinkedHashMap<String,Long> getTotalTimesheetCount(@PathVariable String employeeId){
        return timesheetService.getTimesheetDataById(employeeId);
 
    }


    // API endpoint to retrieve all timesheets
    @GetMapping("/all")
    public ResponseEntity<List<TimesheetDTO>> getAllTimesheets() {
        // Call the service to get a list of all timesheets
        List<TimesheetDTO> timesheets = timesheetService.getAllTimesheets();

        // Return a ResponseEntity with the list of TimesheetDTOs and HTTP status 200 (OK)
        return new ResponseEntity<>(timesheets, HttpStatus.OK);
    }


    // API endpoint to retrieve timesheet details for a particular employee
    @GetMapping("/list/{employeeId}")
    public List<TimesheetDTO> getTimesheetDetails(@PathVariable String employeeId) {
        // Call the service to get timesheet details for the given employee ID
        return timesheetService.getTimesheetDetailsParticularEmployeeId(employeeId);
    }




    // API endpoint to retrieve timesheet details for a particular manager
    @GetMapping("/list/manager/{managerId}")
    public List<TimesheetDTO> getTimesheetDetailsByManagerId(@PathVariable String managerId) {
        // Call the service to get timesheet details for the given manager ID
        return timesheetService.getDetailsFromManagerId(managerId);
    }



    // Endpoint to update a timesheet by its ID
    @PutMapping("/update/{id}")
    public ResponseEntity<TimesheetDTO> updateTimesheet(
            @PathVariable Long id,  // Path variable for the timesheet ID to be updated
            @RequestBody TimesheetDTO timesheetDTO) {  // Request body containing the updated timesheet details

        // Call the service to update the timesheet by ID and get the updated TimesheetDTO
        TimesheetDTO updatedTimesheetDTO = timesheetService.updateTimesheetById(id, timesheetDTO);

        // Return the updated TimesheetDTO wrapped in a ResponseEntity with HTTP status 200 (OK)
        return ResponseEntity.ok(updatedTimesheetDTO);
    }

    // Endpoint to approve and update the status of a timesheet by its ID and status
    @PutMapping(value="/Approve/{id}/status/{status}", produces="application/json")
    public ResponseEntity<TimesheetDTO> approveUpdateStatus(
            @PathVariable Long id,  // Path variable for the timesheet ID to be updated
            @PathVariable Timesheet.Status status) {  // Path variable for the new status to set

        // Call the service to update the timesheet status and get the updated TimesheetDTO
        TimesheetDTO updatedTimesheets = timesheetService.updateApproveStatus(id, status);

        // If the timesheet is updated successfully
        if (updatedTimesheets != null ) {
            // If the status is APPROVED, send an approval notification email
            if(updatedTimesheets.getStatus() == Timesheet.Status.APPROVED) {
                timesheetEmailService.sendApprovalNotification(updatedTimesheets);
            }

            // Return the updated TimesheetDTO with HTTP status 200 (OK)
            return ResponseEntity.ok(updatedTimesheets);
        }
        else {
            // Return 404 Not Found if no timesheet was found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    // Endpoint to reject and update the status of a timesheet by its ID, status, and comments
    @PutMapping("/reject/{id}/status/{status}/comments/{comments}")
    public ResponseEntity<TimesheetDTO> rejectUpdateStatus(
            @PathVariable Long id,  // Path variable for the timesheet ID to be updated
            @PathVariable Timesheet.Status status,  // Path variable for the new status (REJECTED)
            @PathVariable String comments) {  // Path variable for rejection comments

        // Call the service to update the timesheet status to REJECTED and get the updated TimesheetDTO
        TimesheetDTO updatedTimesheets = timesheetService.updateRejectStatus(id, status, comments);

        // If the timesheet is updated successfully
        if (updatedTimesheets != null ) {

            // If the status is REJECTED, send a rejection notification email with the comments
            if(updatedTimesheets.getStatus() == Timesheet.Status.REJECTED) {
                timesheetEmailService.sendRejectionNotification(updatedTimesheets, comments);
            }

            // Return the updated TimesheetDTO with HTTP status 200 (OK)
            return ResponseEntity.ok(updatedTimesheets);
        }
        else {
            // Return 404 Not Found if no timesheet was found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint to delete a timesheet by its ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTimesheet(@PathVariable Long id) {  // Path variable for the timesheet ID to be deleted

        // Call the service to delete the timesheet and get the result (success or failure)
        boolean isDeleted = timesheetService.deleteTimesheet(id);

        // If the timesheet is deleted successfully, return HTTP status 200 (OK) with a success message
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Timesheet deleted successfully.");
        } else {
            // If the timesheet is not found, return HTTP status 404 (NOT_FOUND) with an error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Timesheet not found.");
        }
    }




    //Delete an entire timesheet particular employee based on employeeId
    @DeleteMapping("/delete/employeeId/{employeeId}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable String employeeId) {

        boolean isDeleted = timesheetService.deleteTimesheetByEmployeeId(employeeId);

        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    @GetMapping("/totalList/employeeId/{employeeId}/startDate/{startDate}/endDate/{endDate}")
    public List<TimesheetDTO> getTotalTimesheetsByEmployeeId(@PathVariable String employeeId,
                                                             @PathVariable LocalDate startDate,
                                                             @PathVariable LocalDate endDate){
        return timesheetService.getTotalTimesheets(employeeId,startDate,endDate);
    }



}
