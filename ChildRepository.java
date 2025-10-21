package com.donorsignup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donorsignup.model.Child;
import java.util.List;


@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> findByTrustId(Long trustId);
}

