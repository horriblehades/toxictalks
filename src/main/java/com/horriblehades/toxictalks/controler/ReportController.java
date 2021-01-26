package com.horriblehades.toxictalks.controler;


import com.horriblehades.toxictalks.domain.Report;
import com.horriblehades.toxictalks.domain.User;
import com.horriblehades.toxictalks.repos.ReportRepo;
import com.horriblehades.toxictalks.repos.UserRepo;
import com.horriblehades.toxictalks.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasAuthority('ADMIN')")
@Controller
public class ReportController {

    @Autowired
    private ReportRepo reportRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports")
    public String chats(Model model, @AuthenticationPrincipal User user) {
        if (!userRepo.findById(user.getId()).get().isActive()) {
            return "redirect:/";
        }
        Iterable<Report> reports = reportRepo.findAll();
        model.addAttribute("reports", reports);
        return "reportsList";
    }

    @PostMapping("/reports/block")
    public String blockUser(@RequestParam Long reportId,
                            @AuthenticationPrincipal User user) {
        if (!userRepo.findById(user.getId()).get().isActive()) {
            return "redirect:/";
        }
        reportService.blockUser(reportId);
        return "redirect:/reports";
    }

    @GetMapping("/reports/delete")
    public String deleteReport(@RequestParam Long reportId,
                               @AuthenticationPrincipal User user) {
        if (!userRepo.findById(user.getId()).get().isActive()) {
            return "redirect:/";
        }
        reportService.deleteReport(reportId);
        return "redirect:/reports";
    }
}
