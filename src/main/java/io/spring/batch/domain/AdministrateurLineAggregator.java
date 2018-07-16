package io.spring.batch.domain;

import org.springframework.batch.item.file.transform.LineAggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Michael Minella
 */
public class AdministrateurLineAggregator implements LineAggregator<Administrateur> {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String aggregate(Administrateur item) {
		try {
			return objectMapper.writeValueAsString(item);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Unable to serialize Administrateur", e);
		}
	}
}
