package meep.repository;

import java.util.Map;

import meep.model.Vehicle;

public interface IVehiclesRepository {
	public void save(Vehicle vehicle);

	public void saveAll(Map<String, Vehicle> vehicles);

	public Vehicle findOne(String pk);

	public Map<String, Vehicle> findAll();
}
