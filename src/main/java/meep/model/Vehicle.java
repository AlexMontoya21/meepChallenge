package meep.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
