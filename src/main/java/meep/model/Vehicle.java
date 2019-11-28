package meep.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class Vehicle {
	
    public Vehicle() {
		super();
	}

	public Vehicle(String id, String name, int x, int y, String model, String engineType) {
		super();
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.model = model;
		this.engineType = engineType;
	}

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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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

	String id;
    String name;
    int x;
    int y;
    String model;
    String engineType;
    
    @Override
    public String toString() {
    	return "    {\r\n" + 
    			"        \"name\": \""+this.name+"\",\r\n" + 
    			"        \"x\": "+this.x+",\r\n" + 
    			"        \"y\": "+this.y+",\r\n" + 
    			"        \"model\": \""+this.model+"\",\r\n" + 
    			"        \"engineType\": \""+this.engineType+"\",\r\n" + 
    			"    }";
    }
    
    @Override
    public boolean equals(Object anObject) {
        if (!(anObject instanceof Vehicle)) {
            return false;
        }
        Vehicle otherObject = (Vehicle)anObject;
        return otherObject.getId().equals(getId());
    }
}
