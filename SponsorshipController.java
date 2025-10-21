package com.donorsignup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.donorsignup.model.Child;
import com.donorsignup.model.Sponsorship;
import com.donorsignup.service.SponsorshipService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SponsorshipController {

    @Autowired
    private SponsorshipService sponsorshipService;

    @PostMapping("/trust/addChild")
    public ResponseEntity<Child> addChild(@RequestBody Child child, @RequestParam Long trustId) {
        Child savedChild = sponsorshipService.addChild(child, trustId);
        return ResponseEntity.ok(savedChild);
    }

    @GetMapping("/donor/getChildren")
    public ResponseEntity<List<Child>> getAllChildren() {
        return ResponseEntity.ok(sponsorshipService.getAllChildrenForSponsorship());
    }

    @PostMapping("/donor/sponsorChild")
    public ResponseEntity<Sponsorship> sponsorChild(
            @RequestParam Long donorId,
            @RequestParam Long childId,
            @RequestParam Long trustId) {
        Sponsorship sponsorship = sponsorshipService.sponsorChild(donorId, childId, trustId);
        return ResponseEntity.ok(sponsorship);
    }

    @GetMapping("/donor/getSponsored/{donorId}")
    public ResponseEntity<List<Sponsorship>> getSponsoredChildren(@PathVariable Long donorId) {
        return ResponseEntity.ok(sponsorshipService.getSponsoredChildren(donorId));
    }

    @GetMapping("/trust/sponsoredRequests/{trustId}")
    public ResponseEntity<List<Sponsorship>> getSponsorshipRequests(@PathVariable Long trustId) {
        return ResponseEntity.ok(sponsorshipService.getSponsorshipRequestsForTrust(trustId));
    }
}


