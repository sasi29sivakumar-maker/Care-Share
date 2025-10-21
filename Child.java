package com.donorsignup.model;


import jakarta.persistence.*;

@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(name = "physically_challenged")
    private String physicallyChallenged;

    @ManyToOne
    @JoinColumn(name = "trust_id", nullable = false)
    private Trust trust;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public String getPhysicallyChallenged() { return physicallyChallenged; }
    public void setPhysicallyChallenged(String physicallyChallenged) { this.physicallyChallenged = physicallyChallenged; }
	
    public Trust getTrust() {
		return trust;
	}
	public void setTrust(Trust trust) {
		this.trust = trust;
	}
    
    
}

