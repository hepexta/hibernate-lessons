package com.hepexta.lessons.hibernatelessons.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_ID")
	private Long transactionId;

	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.ALL)
    /** Since we now have an inverse relationship put the join column on the owning side of the relationship. */
    @JoinColumn(name="ACCOUNT_ID", nullable = false)
    private Account account;

    @Column(name = "INITIAL_BALANCE")
	private BigDecimal initialBalance;

	@Column(name = "CLOSING_BALANCE")
	private BigDecimal closingBalance;

	@Column(name = "NOTES")
	private String notes;

	@Column(name = "LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

}
