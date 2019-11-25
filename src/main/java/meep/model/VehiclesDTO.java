package meep.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiclesDTO {
	String id;
    String name;
    String model;
    String engineType;
    boolean available;
    
	public VehiclesDTO(String id, String name, String model, String engineType, boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.model = model;
		this.engineType = engineType;
		this.available = available;
	};
}
