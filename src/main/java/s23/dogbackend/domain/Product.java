package s23.dogbackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Name should be an word and cannot be empty.")
	@Size(min = 2, max = 50)
	private String name;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private Type type;

	private String color;

	private String size;

	// Tuotteen määrä varastossa
	private int unitsInStock;

	@NotNull(message = "The price should be a number and cannot be empty.")
	@DecimalMin(value = "0.0", inclusive = true, message = "The price should be greater than or equal to 0")
	private double price;

	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	// koodi restin helpottamiseksi
	public Product() {
	}

	public Product(
			@NotEmpty(message = "Name should be an word and cannot be empty.") @Size(min = 2, max = 50) String name,
			Type type, String color, String size, int unitsInStock,
			@NotNull(message = "The price should be a number and cannot be empty.") @DecimalMin(value = "0.0", inclusive = true, message = "The price should be greater than or equal to 0") double price,
			Manufacturer manufacturer) {
		super();
		this.name = name;
		this.type = type;
		this.color = color;
		this.size = size;
		this.unitsInStock = unitsInStock;
		this.price = price;
		this.manufacturer = manufacturer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", color=" + color + ", size=" + size + ", unitsInStock="
				+ unitsInStock + ", price=" + price + "]";
	}

}
