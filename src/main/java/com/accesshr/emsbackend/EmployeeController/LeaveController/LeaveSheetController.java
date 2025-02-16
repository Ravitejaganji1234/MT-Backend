package com.accesshr.emsbackend.EmployeeController.LeaveController;

import com.accesshr.emsbackend.Entity.LeaveSheet;
import com.accesshr.emsbackend.Service.LeaveService.LeaveSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LeaveSheetController {

    @Autowired
    private LeaveSheetService leaveSheetService;

    @PostMapping(value = "/submitSheet", produces = "application/json")
    public ResponseEntity<?> submitSheet(@RequestBody LeaveSheet leaveSheet) {
        try {
            LeaveSheet saveSheet = leaveSheetService.createLeaveSheet(leaveSheet);
            return ResponseEntity.ok(saveSheet);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Error Occurred");
        }
    }

    @PutMapping("/updateSheet")
    public ResponseEntity<?> updateLeaveSheet(@PathVariable int id, @RequestBody LeaveSheet leaveSheet) {
        LeaveSheet updateSheet = leaveSheetService.updateLeaveSheet(id, leaveSheet);
        return ResponseEntity.ok(updateSheet);
    }

    @GetMapping("/getSheets")
    public ResponseEntity<List<LeaveSheet>> getAllLeaveSheets() {
        List<LeaveSheet> leaveSheets = leaveSheetService.getAllLeaveSheets();
        return ResponseEntity.ok(leaveSheets);
    }
}
