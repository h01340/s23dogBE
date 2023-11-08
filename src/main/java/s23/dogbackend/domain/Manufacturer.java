package s23.dogbackend.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Manufacturer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Name cannot be empty.")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
	@JsonIgnore
	private List<Product> products;

	// koodi restin helpottamiseksi
	public Manufacturer() {
	}

	public Manufacturer(@NotEmpty(message = "Name cannot be empty.") String name) {
		super();
		this.name = name;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}



	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", name=" + name + ", products=" + products + "]";
	}

}
