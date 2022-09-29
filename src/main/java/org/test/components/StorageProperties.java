package org.test.components;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StorageProperties {
	@Value("${app.upload.temp}")
	private String location;

	public String getLocation() {
		return location;
	}

}
