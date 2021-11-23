package app.core.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Generate a customer object to send to the data storage.
 */
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
//	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
//	@JoinTable(name = "customer_coupon", joinColumns = @JoinColumn(name = "id_customer", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_coupon", referencedColumnName = "id"))
	@Transient
	private Set<Coupon> coupons = new HashSet<>();

	public Customer(int id) {
		super();
		this.id = id;
	}

	public Customer(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Customer(int id, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

}
