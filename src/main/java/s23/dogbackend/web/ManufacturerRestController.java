package s23.dogbackend.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import s23.dogbackend.domain.Manufacturer;
import s23.dogbackend.domain.ManufacturerRepository;

@RestController
public class ManufacturerRestController {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	// Return all manufacturers
	@GetMapping("/rest/manus")
	public List<Manufacturer> getAllManufacturers() {
		return (List<Manufacturer>) manufacturerRepository.findAll();
	}

	// Return manufacturer by id
	// Returns 200 OK if found. Otherwise returns 404 NOT FOUND
	@GetMapping("/rest/manus/{id}")
	public ResponseEntity<Optional<Manufacturer>> getManufacturerById(@PathVariable("id") Long manufacturerId) {
		Optional<Manufacturer> manufacturer = manufacturerRepository.findById(manufacturerId);
		HttpStatusCode status = HttpStatus.NOT_FOUND;

		if (manufacturer.isPresent()) {
			status = HttpStatus.OK;
		}

		return new ResponseEntity<>(manufacturer, status);
	}

}
