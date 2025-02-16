package com.accesshr.emsbackend.Service.TimesheetService;

import com.accesshr.emsbackend.Dto.TimesheetDTO.TimesheetDTO;
import com.accesshr.emsbackend.Entity.Timesheet;
import com.accesshr.emsbackend.Repo.TimesheetRepo.TimesheetRepository;
import com.accesshr.emsbackend.exceptions.TimesheetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimesheetService {
    @Autowired
    private TimesheetRepository timesheetRepository;

    // Create a helper method to convert Timesheet to TimesheetDTO
    private TimesheetDTO mapToDTO(Timesheet timesheet) {
        // Check if the timesheet is null
        if (timesheet == null) {
            throw new TimesheetNotFoundException("Timesheet data is empty or not found.");
        }
        TimesheetDTO dto = new TimesheetDTO();
        dto.setEmployeeId(timesheet.getEmployeeId());
        dto.setNumberOfHours(timesheet.getNumberOfHours());
        dto.setExtraHours(timesheet.getExtraHours());
        dto.setReportingManager(timesheet.getReportingManager());
        dto.setClientName(timesheet.getClientName());
        dto.setProjectName(timesheet.getProjectName());
        dto.setTaskType(timesheet.getTaskType());
        dto.setWorkLocation(timesheet.getWorkLocation());
        dto.setTaskDescription(timesheet.getTaskDescription());
        dto.setEmailId(timesheet.getEmailId());
        dto.setStartDate(timesheet.getStartDate());
        dto.setEndDate(timesheet.getEndDate());
        dto.setOnCallSupport(timesheet.isOnCallSupport());
        dto.setId(timesheet.getId());
        dto.setEmployeeName(timesheet.getEmployeeName());
        dto.setManager(timesheet.getManager());
        dto.setManagerId(timesheet.getManagerId());
        dto.setStatus(timesheet.getStatus());
        dto.setComments(timesheet.getComments());

        dto.setTotalNumberOfHours(timesheet.getNumberOfHours()+timesheet.getExtraHours());
        return dto;
    }

    // Method to create a Timesheet object from a TimesheetDTO
    private static Timesheet getTimesheet(TimesheetDTO timesheetDTO) {
        // Create a new Timesheet object
        Timesheet timesheet = new Timesheet();
        timesheet.setEmployeeId(timesheetDTO.getEmployeeId());
        timesheet.setNumberOfHours(timesheetDTO.getNumberOfHours());
        timesheet.setExtraHours(timesheetDTO.getExtraHours());
        timesheet.setReportingManager(timesheetDTO.getReportingManager());
        timesheet.setClientName(timesheetDTO.getClientName());
        timesheet.setProjectName(timesheetDTO.getProjectName());
        timesheet.setTaskType(timesheetDTO.getTaskType());
        timesheet.setWorkLocation(timesheetDTO.getWorkLocation());
        timesheet.setTaskDescription(timesheetDTO.getTaskDescription());
        timesheet.setEmailId(timesheetDTO.getEmailId());
        timesheet.setStartDate(timesheetDTO.getStartDate());
        timesheet.setEndDate(timesheetDTO.getEndDate());
        timesheet.setOnCallSupport(timesheetDTO.isOnCallSupport());
        timesheet.setId(timesheetDTO.getId());
        timesheet.setEmployeeName(timesheetDTO.getEmployeeName());
        timesheet.setStatus(timesheetDTO.getStatus());
        timesheet.setManager(timesheetDTO.getManager());
        timesheet.setManagerId(timesheetDTO.getManagerId());
        timesheet.setComments(timesheetDTO.getComments());

        // Automatically set status to PENDING if not REJECTED or APPROVED
        if(timesheetDTO.getStatus()!= Timesheet.Status.REJECTED && timesheetDTO.getStatus()!= Timesheet.Status.APPROVED ){
            timesheet.setStatus(Timesheet.Status.PENDING);
        }
        // Return the constructed Timesheet object
        return timesheet;
    }


    public TimesheetDTO createTimesheet(TimesheetDTO timesheetDTO) {
        // Validate essential fields
        // validateTimesheetDTO(timesheetDTO);

        // Check for existing timesheets with the same start and end dates
        // List<Timesheet> existingTimesheets = timesheetRepository.findByEmployeeIdAndStartDateAndEndDate(
        //         timesheetDTO.getEmployeeId(), timesheetDTO.getStartDate(), timesheetDTO.getEndDate());

        // Timesheet timesheet;

        // if (!existingTimesheets.isEmpty()) {
        //     Timesheet existingTimesheet = existingTimesheets.get(0);

        //     // Handle based on the status of the existing timesheet
        //     switch (existingTimesheet.getStatus()) {
        //         case APPROVED -> throw new TimesheetNotFoundException(
        //                 "Timesheet has already been approved and cannot be modified for dates: " +
        //                         existingTimesheet.getStartDate() + " to " + existingTimesheet.getEndDate());
        //         case REJECTED -> timesheet = getTimesheet(timesheetDTO); // Create new timesheet for rejected case
        //         default -> {
        //             updateTimesheetFields(existingTimesheet, timesheetDTO); // Modify the existing timesheet
        //             timesheet = existingTimesheet;
        //         }
        //     }
        // } else {
        //     // Create a new timesheet if none exists
        //     timesheet = getTimesheet(timesheetDTO);
        // }

        // // Check if a pending timesheet exists for the same dates
        // if (isPendingTimesheetExists(timesheetDTO) && timesheetDTO.getId() == null) {
        //     throw new TimesheetNotFoundException("A timesheet for these dates already exists and is awaiting manager approval.");
        // }

        Timesheet timesheet = getTimesheet(timesheetDTO);

        // Calculate total hours
        timesheetDTO.setTotalNumberOfHours(timesheet.getNumberOfHours() + timesheet.getExtraHours());


        // Save and return the timesheet
        Timesheet savedTimesheet = timesheetRepository.save(timesheet);
        return mapToDTO(savedTimesheet);
    }

    // Helper method to validate timesheet data
    // private void validateTimesheetDTO(TimesheetDTO timesheetDTO) {
    //     if (timesheetDTO == null || timesheetDTO.getEmployeeId() == null) {
    //         throw new TimesheetNotFoundException("Timesheet data is invalid or missing. Employee ID is required.");
    //     }
    //     if (timesheetDTO.getNumberOfHours() < 0) {
    //         throw new TimesheetNotFoundException("Number of hours cannot be negative.");
    //     }
    //     if (timesheetDTO.getStartDate() == null || timesheetDTO.getEndDate() == null) {
    //         throw new TimesheetNotFoundException("Start date and end date are required.");
    //     }
    // }

    // Helper method to check if a pending timesheet exists
    // private boolean isPendingTimesheetExists(TimesheetDTO timesheetDTO) {
    //     return timesheetRepository.existsByEmployeeIdAndStartDateAndEndDateAndStatus(
    //             timesheetDTO.getEmployeeId(), timesheetDTO.getStartDate(), timesheetDTO.getEndDate(), Timesheet.Status.PENDING);
    // }


    // Method to retrieve all timesheets
    public List<TimesheetDTO> getAllTimesheets() {
        List<Timesheet> timesheets = timesheetRepository.findAll();
        if (timesheets.isEmpty()) {
            throw new TimesheetNotFoundException(timesheets);
        }
        // Convert each Timesheet entity to TimesheetDTO
        return timesheets.stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    //getting the particular employee total details
    public List<TimesheetDTO> getTimesheetDetailsParticularEmployeeId(String employeeId) {
        // Retrieve the list of timesheets for the given employee ID
        List<Timesheet> timesheets = timesheetRepository.findByEmployeeId(employeeId);
        List<TimesheetDTO> timesheetDTOs = new ArrayList<>();
        // Check if the list of timesheets is not empty
        if (!timesheets.isEmpty()) {
            // Convert each Timesheet entity to TimesheetDTO
            for (Timesheet timesheet : timesheets) {
                TimesheetDTO timesheetDTO = mapToDTO(timesheet);
                timesheetDTOs.add(timesheetDTO); // Add the mapped DTO to the list
            }
            return timesheetDTOs;
        }
        // Handle no data found case
        throw new TimesheetNotFoundException(employeeId);
    }

    // Method to update the fields of an existing timesheet from a DTO
    private void updateTimesheetFields(Timesheet existingTimesheet, TimesheetDTO timesheetDTO) {

        existingTimesheet.setNumberOfHours(timesheetDTO.getNumberOfHours());
        existingTimesheet.setExtraHours(timesheetDTO.getExtraHours());
        existingTimesheet.setReportingManager(timesheetDTO.getReportingManager());
        existingTimesheet.setClientName(timesheetDTO.getClientName());
        existingTimesheet.setProjectName(timesheetDTO.getProjectName());
        existingTimesheet.setTaskType(timesheetDTO.getTaskType());
        existingTimesheet.setWorkLocation(timesheetDTO.getWorkLocation());
        existingTimesheet.setTaskDescription(timesheetDTO.getTaskDescription());
        existingTimesheet.setEmailId(timesheetDTO.getEmailId());
        existingTimesheet.setStartDate(timesheetDTO.getStartDate());
        existingTimesheet.setEndDate(timesheetDTO.getEndDate());
        existingTimesheet.setOnCallSupport(timesheetDTO.isOnCallSupport());
        existingTimesheet.setComments(timesheetDTO.getComments());


    }


    //Update Method Based on ID
    public TimesheetDTO updateTimesheetById(Long id, TimesheetDTO timesheetDTO) {
        // Find the Timesheet by ID
        Timesheet existingTimesheet = timesheetRepository.findById(id).orElseThrow(() -> new TimesheetNotFoundException(id));
        updateTimesheetFields(existingTimesheet, timesheetDTO);

        // Save the updated entity
        Timesheet updatedTimesheet = timesheetRepository.save(existingTimesheet);

        // Map the updated entity back to the DTO and return it
        return mapToDTO(updatedTimesheet);
    }


    // Method to get timesheet details using manager ID
    public List<TimesheetDTO> getDetailsFromManagerId(String managerId) {

        // Fetch timesheets for the given manager ID
        List<Timesheet> timesheets = timesheetRepository.findByManagerId(managerId);

        // If no timesheets are found, throw an exception
        if (timesheets.isEmpty()) {
            throw new TimesheetNotFoundException(managerId);
        }

        // Convert the list of timesheets to DTOs and return
        return convertToTimesheetDTOs(timesheets);
    }


    // Method to update the status of a timesheet to REJECTED
    public TimesheetDTO updateRejectStatus(Long id, Timesheet.Status status, String comments) {
        // Find the timesheet by ID or throw an exception if not found
        Timesheet timesheet = timesheetRepository.findById(id).orElseThrow(() -> new TimesheetNotFoundException(id));

        // Update status only if the current status is PENDING, APPROVED, or REJECTED
        Optional.of(timesheet)
                .filter(t -> t.getStatus() == Timesheet.Status.PENDING || t.getStatus() == Timesheet.Status.APPROVED || t.getStatus() == Timesheet.Status.REJECTED)
                .ifPresent(t -> t.setStatus(status));

        // Update comments for the timesheet
        timesheet.setComments(comments);

        // Save the updated timesheet to the repository
        timesheetRepository.save(timesheet);

        // Map the updated timesheet to a DTO and return it
        return mapToDTO(timesheet);
    }



    // Method to update the status of a timesheet to APPROVED
    public TimesheetDTO updateApproveStatus(Long id, Timesheet.Status status) {
        // Find the timesheet by ID or throw an exception if not found
        Timesheet timesheet = timesheetRepository.findById(id)
                .orElseThrow(() -> new TimesheetNotFoundException(id));

        // Update the status only if the current status is PENDING, APPROVED, or REJECTED
        Optional.of(timesheet)
                .filter(t -> t.getStatus() == Timesheet.Status.PENDING ||
                        t.getStatus() == Timesheet.Status.APPROVED ||
                        t.getStatus() == Timesheet.Status.REJECTED)
                .ifPresent(t -> t.setStatus(status));

        // Save the updated timesheet to the repository
        timesheetRepository.save(timesheet);

        // Map the updated timesheet to a DTO and return it
        return mapToDTO(timesheet);
    }

    //convert List of Timesheet to List of TimesheetDTO
    public List<TimesheetDTO> convertToTimesheetDTOs(List<Timesheet> timesheets) {
        return timesheets.stream().map(this::mapToDTO).collect(Collectors.toList());
    }



    // Method to delete a timesheet by its ID
    public boolean deleteTimesheet(Long id) {

        // Check if the timesheet with the given ID exists in the repository
        if (timesheetRepository.existsById(id)) {
            // If it exists, delete the timesheet by its ID
            timesheetRepository.deleteById(id);
            return true; // Return true to indicate the timesheet was successfully deleted
        }
        // If no timesheet is found with the given ID, return false
        return false; // No Timesheet found to delete
    }



    // Method to delete the entire timesheet of a particular employee based on employeeId
    public boolean deleteTimesheetByEmployeeId(String employeeId) {
        // Retrieve all timesheets for the given employeeId
        List<Timesheet> timesheets = timesheetRepository.findByEmployeeId(employeeId);
        if (!timesheets.isEmpty()) {
            // Delete all timesheets found for the employee
            timesheetRepository.deleteAll(timesheets);
            return true;
        } else {
            // No timesheets found for the employee, return false or handle accordingly
            return false;
        }
    }

    public List<TimesheetDTO> getTotalTimesheets(String employeeId, LocalDate startDate, LocalDate endDate) {
        // Validate the input dates
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date must not be null.");
        }

        // Ensure startDate is before or equal to endDate
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must not be after end date.");
        }

        // Log the date range for debugging
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);

        // Fetch timesheets for the specified date range
        List<Timesheet> existingTimesheets = timesheetRepository.findByEmployeeIdAndDateRange(employeeId, startDate, endDate);
        System.out.println("Fetched Timesheets: " + existingTimesheets);

        // Convert the fetched timesheets to DTOs and return
        return convertToTimesheetDTOs(existingTimesheets);
    }

    public LinkedHashMap<String,Long> getTimesheetDataById(String employeeId){
        List<Timesheet>list=timesheetRepository.findByEmployeeId(employeeId);
        Long totalCount= (long) list.size();
       Long pendingData=  list.stream().filter(ele->ele.getStatus()==Timesheet.Status.PENDING).count();
       Long approvedData=list.stream().filter(ele->ele.getStatus()==Timesheet.Status.APPROVED).count();
       Long rejectedData=list.stream().filter(ele->ele.getStatus()==Timesheet.Status.REJECTED).count();
       LinkedHashMap<String,Long>map=new LinkedHashMap<>();
       map.put("pending",pendingData);
       map.put("approve",approvedData);
       map.put("reject",rejectedData);
       map.put("total",totalCount);
       return map;
    }

}