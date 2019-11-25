package meep.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import meep.client.MeepDataClient;
import meep.model.Vehicle;
import meep.model.VehiclesDTO;
import meep.repository.IVehiclesRepository;

@Service
@EnableScheduling
public class VehiclesServiceImpl {

	@Autowired
	MeepDataClient meepClient;

	@Autowired
	IVehiclesRepository vehiclesRepository;

	final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	public void addEmitter(final SseEmitter emitter) {
		emitters.add(emitter);
	}

	public void removeEmitter(final SseEmitter emitter) {
		emitters.remove(emitter);
	}

	@Async
	@Scheduled(fixedRate = 60000)
	public void getVehicles() throws IOException {
		List<SseEmitter> deadEmitters = new ArrayList<>();
		emitters.forEach(emitter -> {
			try {
				Object data = "There are not available vehicles";
				List<Vehicle> vehiclesAvailables = meepClient.getVehiclesMeepInformation();
				if (vehiclesAvailables != null) {
					List<VehiclesDTO> vehiclesAvailablesDTO = getVehiclesAvailableDTO(vehiclesAvailables);
					Map<String, Vehicle> allVehicles = vehiclesRepository.findAll();
					vehiclesAvailablesDTO.addAll(getVehicleUnavailable(allVehicles, vehiclesAvailables));
					vehiclesRepository.saveAll(
							vehiclesAvailables.stream().collect(Collectors.toMap(Vehicle::getId, Function.identity())));
					data = vehiclesAvailablesDTO;
				}
				emitter.send(SseEmitter.event().data(data));
			} catch (Exception e) {
				deadEmitters.add(emitter);
			}
		});
		emitters.removeAll(deadEmitters);
	}

	private List<VehiclesDTO> getVehicleUnavailable(Map<String, Vehicle> allVehicles,
			List<Vehicle> vehiclesAvailables) {

		List<Vehicle> v = new ArrayList<Vehicle>(allVehicles.values());

		v.removeAll(vehiclesAvailables);
		return v.stream().map(new Function<Vehicle, VehiclesDTO>() {
			@Override
			public VehiclesDTO apply(Vehicle v) {
				return new VehiclesDTO(v.getId(), v.getName(), v.getModel(), v.getEngineType(), false);
			}
		}).collect(Collectors.toList());
	}

	private List<VehiclesDTO> getVehiclesAvailableDTO(List<Vehicle> vehiclesAvailables) {
		return vehiclesAvailables.stream().map(new Function<Vehicle, VehiclesDTO>() {
			@Override
			public VehiclesDTO apply(Vehicle v) {
				return new VehiclesDTO(v.getId(), v.getName(), v.getModel(), v.getEngineType(), true);
			}
		}).collect(Collectors.toList());
	}

}
