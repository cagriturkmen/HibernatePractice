package com.bilgeadam.entity;

import java.security.Timestamp;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="user_table")
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@Temporal(value= TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name="created_on")
	private Date createdOn;
	
	@ManyToOne
	@JoinColumn(name="role_id",nullable=false,referencedColumnName = "id" )
	private Role role;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_detail_id", referencedColumnName = "id" )
	private UserDetail userDetail;
	
}
