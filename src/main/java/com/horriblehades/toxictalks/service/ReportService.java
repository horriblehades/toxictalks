package com.horriblehades.toxictalks.service;

import com.horriblehades.toxictalks.domain.ChatMessage;
import com.horriblehades.toxictalks.domain.Report;
import com.horriblehades.toxictalks.domain.User;
import com.horriblehades.toxictalks.repos.ChatMessageRepo;
import com.horriblehades.toxictalks.repos.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ChatMessageRepo chatMessageRepo;

    @Autowired
    private ReportRepo reportRepo;


    public Iterable<Report> findAllReports() { return reportRepo.findAll(); }

    public void createReport(Long messageId, String exampleRadios) {
        ChatMessage message = chatMessageRepo.findById(messageId).get();
        Report report = new Report();
        report.setMessage(message);
        switch (exampleRadios) {
            case "option1":
                report.setName("Причина жалобы: Порнография");
                break;
            case "option2":
                report.setName("Причина жалобы: Рассылка спама");
                break;
            case "option3":
                report.setName("Причина жалобы: Реклама");
                break;
            case "option4":
                report.setName("Причина жалобы: Другая причина");
                break;
        }
        reportRepo.save(report);
    }

    public void blockUser(Long reportId) {
        Report report = reportRepo.findById(reportId).get();
        ChatMessage message = report.getMessage();
        User user = message.getAuthor();
        user.setActive(false);
        reportRepo.delete(report);
        chatMessageRepo.delete(message);

    }

    public void deleteReport(Long reportId) {
        reportRepo.delete(reportRepo.findById(reportId).get());
    }

}
