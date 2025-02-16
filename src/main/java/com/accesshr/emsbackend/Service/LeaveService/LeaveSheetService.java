package com.accesshr.emsbackend.Service.LeaveService;

import com.accesshr.emsbackend.Entity.LeaveSheet;
import com.accesshr.emsbackend.Repo.LeaveRepo.LeaveSheetRepository;
import com.accesshr.emsbackend.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveSheetService {

    @Autowired
    private LeaveSheetRepository leaveSheetRepository;

    public LeaveSheet createLeaveSheet(LeaveSheet leaveSheet) {
        return leaveSheetRepository.save(leaveSheet);
    }

    public LeaveSheet updateLeaveSheet(int id,LeaveSheet leaveSheet) {
        LeaveSheet updateSheet = leaveSheetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave Sheet Not Found"));
        updateSheet.setSICK(leaveSheet.getSICK());
        updateSheet.setVACATION(leaveSheet.getVACATION());
        updateSheet.setCASUAL(leaveSheet.getCASUAL());
        updateSheet.setMATERNITY(leaveSheet.getMATERNITY());
        updateSheet.setMARRIAGE(leaveSheet.getMARRIAGE());
        updateSheet.setPATERNITY(leaveSheet.getPATERNITY());
        updateSheet.setOTHERS(leaveSheet.getOTHERS());
        return leaveSheetRepository.save(updateSheet);
    }

    public List<LeaveSheet> getAllLeaveSheets() {
        return leaveSheetRepository.findAll();
    }
}
