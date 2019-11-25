package meep.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import meep.client.IMeepDataClient;
import meep.model.Vehicle;

@Repository
public class VehiclesRepository implements IVehiclesRepository {

	@Autowired
	IMeepDataClient meepDataClient;

	Map<String, Vehicle> vehicles = null;

	@Bean(name = "vehiclesAvailableList")
	public Map<String, Vehicle> loadAvailableVehicles() {
		List<Vehicle> vehicles = meepDataClient.getVehiclesMeepInformation();
		this.vehicles = vehicles.stream().collect(Collectors.toMap(Vehicle::getId, Function.identity()));
		;
		return this.vehicles;
	}

	private VehiclesRepository() {
		this.vehicles = (vehicles == null) ? new HashMap<String, Vehicle>() : this.vehicles;
	}

	public void save(Vehicle vehicle) {
		this.vehicles.put(vehicle.getId(), vehicle);
	}

	public void saveAll(Map<String, Vehicle> vehicles) {
		this.vehicles.putAll(vehicles);
	}

	public Vehicle findOne(String pk) {
		return this.vehicles.get(pk);

	}

	public Map<String, Vehicle> findAll() {
		return this.vehicles;

	}

}
