package com.hepexta.lessons.hibernatelessons.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BUDGET")
public class Budget {

	@Id
	@GeneratedValue
	@Column(name = "BUDGET_ID")
	private Long budgetId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "GOAL_AMOUNT")
	private BigDecimal goalAmount;

	@Column(name = "PERIOD")
	private String period;

    @OneToMany(cascade=CascadeType.ALL)
    /** @JoinTable is a JPA annotation
     *  Start with join column of the owning entity(Budget). You can choose which side you want to place the
     *  @JoinTable annotation. Then specify the inverse join column of TransactionHibernateAPI entity. */
    @JoinTable(name="BUDGET_TRANSACTION", joinColumns=@JoinColumn(name="BUDGET_ID"),
            inverseJoinColumns=@JoinColumn(name="TRANSACTION_ID"))
    private List<Transaction> transactions = new ArrayList<>();
}
