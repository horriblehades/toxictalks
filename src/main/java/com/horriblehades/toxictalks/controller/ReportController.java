package com.horriblehades.toxictalks.controller;


import com.horriblehades.toxictalks.domain.Report;
import com.horriblehades.toxictalks.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasAuthority('ADMIN')")
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports")
    public String chats(Model model) {

        Iterable<Report> reports = reportService.findAllReports();

        model.addAttribute("reports", reports);

        return "reportsList";
    }

    @PostMapping("/reports/block")
    public String blockUser(@RequestParam Long reportId) {

        reportService.blockUser(reportId);

        return "redirect:/reports";
    }

    @GetMapping("/reports/delete")
    public String deleteReport(@RequestParam Long reportId) {

        reportService.deleteReport(reportId);

        return "redirect:/reports";
    }
}
