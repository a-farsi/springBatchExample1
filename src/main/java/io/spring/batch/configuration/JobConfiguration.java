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

import java.util.List;

//import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.foncia.assurance.metier.Police;

import io.spring.batch.domain.Administrateur;
import io.spring.batch.domain.AdministrateurRowMapper;
import io.spring.batch.domain.CnilTableRowMapper;
import io.spring.batch.domain.Customer;
import io.spring.batch.domain.CustomerRowMapper;
import io.spring.batch.domain.FilteringItemProcessor;
import io.spring.batch.domain.Police;
import io.spring.batch.domain.PoliceRowMapper;
import io.spring.batch.parametrage.CnilParametrageTable;

/**
 * @author 
 */
@Configuration
public class JobConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	private static final String WILL_BE_INJECTED = null;

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
	public JdbcCursorItemReader<CnilParametrageTable> cursorItemReaderForCnilParametrageTable() {
		JdbcCursorItemReader<CnilParametrageTable> reader2 = new JdbcCursorItemReader<>();

		// reader.setSql("select id, firstName, lastName, birthdate from customer order
		// by lastName, firstName");
		reader2.setSql("SELECT * FROM table_parametrage_cnil");
		reader2.setDataSource(this.dataSource);
		reader2.setRowMapper(new CnilTableRowMapper());

		return reader2;
	}

	@Bean
	public JdbcCursorItemReader<Administrateur> cursorItemReaderForAdministrateur() {
		JdbcCursorItemReader<Administrateur> reader = new JdbcCursorItemReader<>();

		// reader.setSql("select id, firstName, lastName, birthdate from customer order
		// by lastName, firstName");
		String columnName = "Administrateur";
		String tableName = "password";

		// String query = "SELECT " + columnName + " FROM " + tableName + ";";// + " ORDER BY numeroEmetteur"
		reader.setSql("SELECT * FROM Administrateur ORDER BY numeroEmetteur");// + " ORDER BY numeroEmetteur");
		reader.setDataSource(this.dataSource);
		reader.setRowMapper(new AdministrateurRowMapper());

		return reader;
	}

	@Bean
	public JdbcCursorItemReader<Administrateur> cursorItemReaderForTable() {
		JdbcCursorItemReader<Administrateur> reader = new JdbcCursorItemReader<>();

		// reader.setSql("select id, firstName, lastName, birthdate from customer order
		// by lastName, firstName");
		reader.setSql("SELECT * FROM Administrateur ORDER BY numeroEmetteur");
		reader.setDataSource(this.dataSource);
		reader.setRowMapper(new AdministrateurRowMapper());

		return reader;
	}

	@Bean
	@StepScope
	public JdbcCursorItemReader<Police> cursorItemReaderForPolice(@Value("#{jobParameters['tableName']}") String tableName, @Value("#{jobParameters['columnName']}") String columnName) {
		JdbcCursorItemReader<Police> reader = new JdbcCursorItemReader<>();
		String query = "SELECT * FROM " + tableName + " WHERE ROWNUM <= 1000 ORDER BY ID";
		reader.setSql(query);
		reader.setDataSource(this.dataSource);
		reader.setRowMapper(new PoliceRowMapper(columnName));

		return reader;
	}

	/*
	 @Bean
	 public JdbcPagingItemReader<Customer> pagingItemReader() {
	 JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();
	
	 reader.setDataSource(this.dataSource);
	 reader.setFetchSize(10);
	 reader.setRowMapper(new CustomerRowMapper());
	
	 MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
	 queryProvider.setSelectClause("id, firstName, lastName, birthdate");
	 queryProvider.setFromClause("from customer");
	
	 Map<String, Order> sortKeys = new HashMap<>(1);
	
	 sortKeys.put("id", Order.ASCENDING);
	
	 queryProvider.setSortKeys(sortKeys);
	
	 reader.setQueryProvider(queryProvider);
	
	 return reader;
	 }
	 */

	/*	@Bean
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
		}*/

	@Bean
	public ItemWriter<Police> policeItemWriter() {
		return new ItemWriter<Police>() {

			@Override
			public void write(List<? extends Police> items) throws Exception {
				System.out.println("writer..." + items.size());
				for (Police item : items) {
					System.out.println(item);
				}
			}

		};

		/*return items -> {
			for (Administrateur item : items) {
				System.out.println(item.toString());
			}
		};*/

	}

	@Bean
	public FilteringItemProcessor itemProcessor() {
		return new FilteringItemProcessor();
	}

	/*	@Bean
		public FlatFileItemWriter<Administrateur> administrateurItemWriter() throws Exception {
			FlatFileItemWriter<Administrateur> itemWriter = new FlatFileItemWriter<>();
			// itemWriter.setLineAggregator(new PassThroughLineAggregator<>());
			itemWriter.setLineAggregator(new AdministrateurLineAggregator());
			String customerOutputPath = File.createTempFile("customerOutput", ".out").getAbsolutePath();
			System.out.println(">> Output Path: " + customerOutputPath);
			itemWriter.setResource(new FileSystemResource(customerOutputPath));
			itemWriter.afterPropertiesSet();
	
			return itemWriter;
		}*/

	/*	@Bean
		public Step step1() {
			return stepBuilderFactory.get("step1")
					.<Customer, Customer>chunk(10)
					.reader(cursorItemReader())
					.writer(customerItemWriter())
					.build();
		}
	*/

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<Police, Police> chunk(10)
				.reader(cursorItemReaderForPolice(WILL_BE_INJECTED, WILL_BE_INJECTED))
				.processor(itemProcessor())
				// .writer(policeItemWriter())
				.build();
	}

	/*	@Bean
		public Step step3() throws Exception {
			return stepBuilderFactory.get("step3")
					.<Administrateur, Administrateur> chunk(10)
					.reader(cursorItemReaderForAdministrateur())
					.writer(administrateurItemWriter())
					.build();
		}*/

	public Step step4() throws Exception {
		return stepBuilderFactory.get("step4").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				JobParameters parameters = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobParameters();
				System.out.println("Step4:" + parameters.getString("tableName"));
				System.out.println("Step4:" + parameters.getString("columnName"));
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Job jobPolice() throws Exception {
		// return jobBuilderFactory.get("job").start(step1()).next(step2()).build();
		return jobBuilderFactory.get("jobPolice")
				.incrementer(new RunIdIncrementer())// AFA added to generate different ids
				.start(step2())
				.build();
	}

	/*
	private final String SAMPLE_DATA = "classpath:data_2.sql";
	@Autowired
	private DataSource datasource;
	
	@PostConstruct
	public void loadIfInMemory() throws Exception {
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		org.springframework.core.io.Resource resource = resourceLoader.getResource(SAMPLE_DATA);
		ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
	}
	*/

}
