package com.accesshr.emsbackend.Service.TimesheetService;

import com.accesshr.emsbackend.Dto.TimesheetDTO.TimesheetDTO;
import com.accesshr.emsbackend.Repo.TimesheetRepo.TimesheetRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
public class TimesheetEmailService {



    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private JavaMailSender javaMailSender;


    // Send email method
    private void sendEmail(String recipient, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(fromEmail);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(content, true);  // HTML content enabled
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendApprovalNotification(TimesheetDTO timesheetDto) {
        // Set the subject
        String subject = "Timesheet Approval Notification";

        // Start the email content in HTML format
        String emailContent = "<html><body>";
        emailContent += "<p>Dear <strong>" + timesheetDto.getEmployeeName() + "</strong>,</p>";
        emailContent += "<p>Your timesheet for the period <strong>"
                + timesheetDto.getStartDate().toString() + "</strong> to <strong>"
                + timesheetDto.getEndDate().toString() + "</strong> has been approved.</p>";

//        // Optionally include the reason for approval if provided
//        if (reason != null && !reason.isEmpty()) {
//            emailContent += "<p>Reason: " + reason + "</p>";
//        }

        // Add the HTML table for timesheet details
        emailContent += "<h3>Timesheet Details:</h3>";
        emailContent += "<table style='width:100%; border: 1px solid #ddd; border-collapse: collapse;'>";

        // Table header with improved styling
        emailContent += "<tr style='background-color: #f2f2f2;'>";
        emailContent += "<th style='text-align:left; padding: 8px; border: 1px solid #ddd;'>Field</th>";
        emailContent += "<th style='text-align:left; padding: 8px; border: 1px solid #ddd;'>Details</th>";
        emailContent += "</tr>";

        // Table rows with styling for better readability
        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Project</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getProjectName() + "</td></tr>";

        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Client</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getClientName() + "</td></tr>";

        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Number of Hours</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getNumberOfHours() + "</td></tr>";

        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Extra Hours</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getExtraHours() + "</td></tr>";

        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Reporting Manager</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getReportingManager() + "</td></tr>";

        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Total Number of Hours</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getTotalNumberOfHours() + "</td></tr>";

        emailContent += "</table>";

        emailContent += "<br><p>Thank you,<br><strong>Middleware Talents</strong></p>";
        emailContent += "</body></html>";


        // Get the recipient email from the DTO
        String recipientEmail = timesheetDto.getEmailId();


        // Send the email as HTML content
        sendEmail(recipientEmail, subject, emailContent);
    }

    public void sendRejectionNotification(TimesheetDTO timesheetDto, String reason) {

        String subject = "Timesheet Rejection Notification";
        String emailContent = "<html><body>";
//        emailContent += "<p>Dear " + timesheetDto.getEmployeeName() + ",</p>";
//        emailContent += "<p>Your timesheet for the period "
//                + timesheetDto.getStartDate().toString() + " to "
//                + timesheetDto.getEndDate().toString() + " has been rejected.</p>";

        emailContent += "<p>Dear <strong>" + timesheetDto.getEmployeeName() + "</strong>,</p>";
        emailContent += "<p>Your timesheet for the period <strong>"
                + timesheetDto.getStartDate().toString() + "</strong> to <strong>"
                + timesheetDto.getEndDate().toString() + "</strong> has been rejected.</p>";


        // Optionally include the reason for rejection, or a generic message if no reason is provided
        if (reason != null && !reason.isEmpty()) {
            emailContent += "<p>Reason for Rejection: " + reason + "</p>";
        } else {
            emailContent += "<p>Please review your timesheet and make necessary corrections.</p>";
        }

        // Add the HTML table for timesheet details
        emailContent += "<h3>Timesheet Details:</h3>";
        emailContent += "<table style='width:100%; border: 1px solid #ddd; border-collapse: collapse;'>";

        // Table header with improved styling
        emailContent += "<tr style='background-color: #f2f2f2;'>";
        emailContent += "<th style='text-align:left; padding: 8px; border: 1px solid #ddd;'>Field</th>";
        emailContent += "<th style='text-align:left; padding: 8px; border: 1px solid #ddd;'>Details</th>";
        emailContent += "</tr>";

        // Table rows with styling for better readability
        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Project</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getProjectName() + "</td></tr>";

        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Client</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getClientName() + "</td></tr>";

        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Number of Hours</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getNumberOfHours() + "</td></tr>";

        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Extra Hours</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getExtraHours() + "</td></tr>";

        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Reporting Manager</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getReportingManager() + "</td></tr>";

        emailContent += "<tr><td style='padding: 8px; border: 1px solid #ddd;'>Total Number of Hours</td>";
        emailContent += "<td style='padding: 8px; border: 1px solid #ddd;'>" + timesheetDto.getTotalNumberOfHours() + "</td></tr>";


        emailContent += "</table>";

        emailContent += "<br><p>Thank you,<br>Middleware Talents</p>";
        emailContent += "</body></html>";

        // Get the recipient email from the DTO
        String recipientEmail = timesheetDto.getEmailId();

        // Send the email as HTML content
        sendEmail(recipientEmail, subject, emailContent);
    }




}

