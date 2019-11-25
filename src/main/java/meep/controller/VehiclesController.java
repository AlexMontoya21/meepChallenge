package meep.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import io.swagger.annotations.ApiOperation;
import meep.configuration.SwaggerConfiguration;

import meep.service.VehiclesServiceImpl;
@Controller
@RequestMapping(value = SwaggerConfiguration.API_BASE_PATH + "vehicles")
public class VehiclesController {
	@Autowired
	VehiclesServiceImpl service;

	@GetMapping
	@ApiOperation(value = "get vehicles available in Lisboa", tags = { "" })
	public ResponseEntity<SseEmitter> getVehicles() throws InterruptedException, IOException {
		final SseEmitter emitter = new SseEmitter();
		service.addEmitter(emitter);
		service.getVehicles();
		emitter.onCompletion(() -> service.removeEmitter(emitter));
		emitter.onTimeout(() -> service.removeEmitter(emitter));
		return new ResponseEntity<>(emitter, HttpStatus.OK);
	}
}
