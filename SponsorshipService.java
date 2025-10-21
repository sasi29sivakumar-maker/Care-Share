package com.donorsignup.service;

import com.donorsignup.model.Sponsorship;
import com.donorsignup.model.Child;
import com.donorsignup.model.Donor;
import com.donorsignup.model.Trust;
import com.donorsignup.repository.ChildRepository;
import com.donorsignup.repository.SponsorshipRepository;
import com.donorsignup.repository.DonorRepository;
import com.donorsignup.repository.TrustRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorshipService {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private SponsorshipRepository sponsorshipRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private TrustRepository trustRepository;

    public Child addChild(Child child, Long trustId) {
        Trust trust = trustRepository.findById(trustId)
                .orElseThrow(() -> new RuntimeException("Trust not found"));
        child.setTrust(trust);
        return childRepository.save(child);
    }

    public List<Child> getAllChildrenForSponsorship() {
        return childRepository.findAll();
    }

    public Sponsorship sponsorChild(Long donorId, Long childId, Long trustId) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found"));
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Child not found"));
        Trust trust = trustRepository.findById(trustId)
                .orElseThrow(() -> new RuntimeException("Trust not found"));

        Sponsorship sponsorship = new Sponsorship();
        sponsorship.setDonor(donor);
        sponsorship.setChild(child);
        sponsorship.setTrust(trust);
        return sponsorshipRepository.save(sponsorship);
    }

    public List<Sponsorship> getSponsoredChildren(Long donorId) {
        return sponsorshipRepository.findByDonorId(donorId);
    }

    public List<Sponsorship> getSponsorshipRequestsForTrust(Long trustId) {
        return sponsorshipRepository.findByTrustId(trustId);
    }
}


