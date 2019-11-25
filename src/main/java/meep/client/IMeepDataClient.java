package meep.client;

import java.util.List;

import org.springframework.stereotype.Component;

import meep.model.Vehicle;
@Component
public interface IMeepDataClient {
	public List<Vehicle> getVehiclesMeepInformation();
}
