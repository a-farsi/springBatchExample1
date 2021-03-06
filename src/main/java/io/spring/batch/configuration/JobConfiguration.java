/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.batch.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.context.WebApplicationContext;

import io.spring.batch.domain.CnilTableRowMapper;
import io.spring.batch.domain.Customer;
import io.spring.batch.domain.CustomerRowMapper;
import io.spring.batch.parametrage.CnilParametrageTable;

/**
 * @author Michael Minella
 */
@Configuration
public class JobConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Bean
	public JdbcCursorItemReader<Customer> cursorItemReader() {
		JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();

		// reader.setSql("select id, firstName, lastName, birthdate from customer order
		// by lastName, firstName");
		reader.setSql("SELECT TOP 10 id, firstName, lastName FROM customer ORDER BY id ASC");
		reader.setDataSource(this.dataSource);
		reader.setRowMapper(new CustomerRowMapper());

		return reader;
	}

	
	@Bean
	public JdbcCursorItemReader<CnilParametrageTable> cursorItemReaderForTable() {
		JdbcCursorItemReader<CnilParametrageTable> reader2 = new JdbcCursorItemReader<>();

		// reader.setSql("select id, firstName, lastName, birthdate from customer order
		// by lastName, firstName");
		reader2.setSql("SELECT * FROM table_parametrage_cnil");
		reader2.setDataSource(this.dataSource);
		reader2.setRowMapper(new CnilTableRowMapper());

		return reader2;
	}

	
	// @Bean
	// public JdbcPagingItemReader<Customer> pagingItemReader() {
	// JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();
	//
	// reader.setDataSource(this.dataSource);
	// reader.setFetchSize(10);
	// reader.setRowMapper(new CustomerRowMapper());
	//
	// MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
	// queryProvider.setSelectClause("id, firstName, lastName, birthdate");
	// queryProvider.setFromClause("from customer");
	//
	// Map<String, Order> sortKeys = new HashMap<>(1);
	//
	// sortKeys.put("id", Order.ASCENDING);
	//
	// queryProvider.setSortKeys(sortKeys);
	//
	// reader.setQueryProvider(queryProvider);
	//
	// return reader;
	// }

	@Bean
	public ItemWriter<Customer> customerItemWriter() {
		return items -> {
			for (Customer item : items) {
				System.out.println(item.toString());
			}
		};
	}

	@Bean
	public ItemWriter<CnilParametrageTable> CnilParametrageTableItemWriter() {
		return items -> {
			for (CnilParametrageTable item : items) {
				System.out.println(item.toString());
			}
		};
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Customer, Customer>chunk(10)
				.reader(cursorItemReader())
				.writer(customerItemWriter())
				.build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<CnilParametrageTable, CnilParametrageTable>chunk(10)
				.reader(cursorItemReaderForTable())
				.writer(CnilParametrageTableItemWriter())
				.build();
	}


	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").start(step1()).next(step2()).build();
	}

	private final String SAMPLE_DATA = "classpath:data_2.sql";
	@Autowired
	private DataSource datasource;

	@PostConstruct
	public void loadIfInMemory() throws Exception {
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		org.springframework.core.io.Resource resource = resourceLoader.getResource(SAMPLE_DATA);
		ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
	}
}
