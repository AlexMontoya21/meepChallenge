package meep.model;

public class VehiclesDTO {
	String id;
    String name;
	String model;
    String engineType;
    boolean available;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public VehiclesDTO(String id, String name, String model, String engineType, boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.model = model;
		this.engineType = engineType;
		this.available = available;
	};
}
