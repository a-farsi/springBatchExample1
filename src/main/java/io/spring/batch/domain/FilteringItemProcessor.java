package io.spring.batch.domain;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author
 */
public class FilteringItemProcessor implements ItemProcessor<Police, Police> {
	private int compteurOK = 0;

	@Override
	public Police process(Police item) throws Exception {

		if (item.getpNotes() != null && item.getpNotes().toUpperCase().contains("SINISTRE")) {
			compteurOK++;
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$ compteurOK = " + compteurOK + "$$$$$$$$$$$$$$$$$$$$$$$$$$");
			return item;

		} else {
			return null;
		}

		// if (item.getId() % 2 == 0) {
		// return null;
		// } else {
		// return item;
		// }
	}

	public int getCompteurOK() {
		return compteurOK;
	}

}
