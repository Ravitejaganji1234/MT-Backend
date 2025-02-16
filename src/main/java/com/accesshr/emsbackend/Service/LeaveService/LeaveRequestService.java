package com.accesshr.emsbackend.Service.LeaveService;

import com.accesshr.emsbackend.Entity.LeaveRequest;

import java.util.LinkedHashMap;
import java.util.List;

public interface LeaveRequestService {

    LeaveRequest submitLeaveRequest(LeaveRequest leaveRequest);
    LeaveRequest approveLeaveRequest(Long id);
    LeaveRequest rejectLeaveRequest(Long id, String leaveReason);
    List<LeaveRequest> getAllLeaveRequests();
    List<LeaveRequest> getLeaveRequestsByStatus(String managerId,LeaveRequest.LeaveStatus leaveStatus);
    List<LeaveRequest> getLeaveRequestByEmployeeStatus(String employeeId,LeaveRequest.LeaveStatus leaveStatus);
    List<LeaveRequest> getAllManagerId(String managerId);
    List<LeaveRequest> getAllEmployeeId(String employeeId);
    LeaveRequest updateLeaveRequest(Long id, LeaveRequest leaveRequest);
    String deleteLeaveRequest(Long id);
    double getRemainingLeaveDays(String employeeId, LeaveRequest.LeaveType leaveType);
    LinkedHashMap<String, Long> getEmpAndLeaveStatus(String employeeId);
}
