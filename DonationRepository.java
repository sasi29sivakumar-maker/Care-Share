package com.donorsignup.repository;

import com.donorsignup.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonorId(Long donorId);
    
    List<Donation> findByDonorIdOrderByIdDesc(Long donorId);
    
    List<Donation> findByTrustIdAndStatus(Long trustId, boolean status);
}


