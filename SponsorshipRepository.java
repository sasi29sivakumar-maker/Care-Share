package com.donorsignup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donorsignup.model.Sponsorship;
import java.util.List;

@Repository
public interface SponsorshipRepository extends JpaRepository<Sponsorship, Long> {
    List<Sponsorship> findByDonorId(Long donorId);
    List<Sponsorship> findByTrustId(Long trustId);
}

