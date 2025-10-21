package com.donorsignup.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sponsorship")
public class Sponsorship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @ManyToOne
    @JoinColumn(name = "trust_id", nullable = false)
    private Trust trust;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
	public Donor getDonor() {
		return donor;
	}
	public void setDonor(Donor donor) {
		this.donor = donor;
	}
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	public Trust getTrust() {
		return trust;
	}
	public void setTrust(Trust trust) {
		this.trust = trust;
	}
}

