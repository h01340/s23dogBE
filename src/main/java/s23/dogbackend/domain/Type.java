package s23.dogbackend.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "type_name")
	private String typeName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
	@JsonIgnore
	private List<Product> products;

	public Type() {
	}

	public Type(String typeName) {
		super();
		this.typeName = typeName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}



	@Override
	public String toString() {
		return "Type [id=" + id + ", typeName=" + typeName + ", products=" + products + "]";
	}

}
