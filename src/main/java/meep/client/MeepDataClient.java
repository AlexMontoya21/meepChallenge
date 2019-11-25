package meep.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import meep.model.Vehicle;
import meep.utils.ConstantsURL;

@Component
public class MeepDataClient implements IMeepDataClient {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${meep.url}")
	private String url;

	public List<Vehicle> getVehiclesMeepInformation() {

		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
					.queryParam(ConstantsURL.LOWER_LEFT_LAT_LON, ConstantsURL.LOWER_LEFT_LAT_LON_VALUE)
					.queryParam(ConstantsURL.UPPER_RIGHT_LAT_LON, ConstantsURL.UPPER_RIGHT_LAT_LON_VALUE)
					.queryParam(ConstantsURL.COMPANY_ZONE_IDS, ConstantsURL.COMPANY_ZONE_IDS_VALUE);

			ResponseEntity<Vehicle[]> cars = restTemplate.getForEntity(builder.build().toString(), Vehicle[].class);
			return Arrays.asList(cars.getBody());
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				return null;
			} else {
				throw e;
			}
		}
	}
}
