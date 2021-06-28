package com.epam.training.microservices.monolithic.model.drug;

import com.epam.training.microservices.monolithic.model.disease.Disease;
import com.epam.training.microservices.monolithic.model.disease.Symptom;
import com.google.common.collect.Sets;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * An information about creation of the drug for the particular disease. 
 */
@Data
@Entity
@Table(name = "drug_creation")
public class DrugCreation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Information about the drug to be created. 
     */
    @JoinColumn(name = "drug_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Drug drug;

    /**
     * Symptoms to be cured by the drug. 
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "drug_creation_symptoms",
        joinColumns = @JoinColumn(name = "drug_creation_id"),
        inverseJoinColumns = @JoinColumn(name = "symptom_id")
    )
    private Set<Symptom> symptoms = Sets.newHashSet();

    /**
     * Diseases to be cured by the drug. 
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "drug_creation_diseases",
        joinColumns = @JoinColumn(name = "drug_creation_id"),
        inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private Set<Disease> diseases = Sets.newHashSet();
}
