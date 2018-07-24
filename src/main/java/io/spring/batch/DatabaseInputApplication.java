package io.spring.batch;

import java.lang.invoke.MethodHandles;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
// @ComponentScan(basePackages = { "io.spring.controller" })
public class DatabaseInputApplication {

	public static void main(String[] args) {
		SpringApplication.run(MethodHandles.lookup().lookupClass(), args);
	}

	/*public static void main(String... args) throws Exception {
	
		// ConfigurableApplicationContext context = SpringApplication.run(DatabaseInputApplication.class, args);
		System.out.println("Test to start! ");
		JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
		Job job = (Job) context.getBean("job");
	
		try {
			JobParametersBuilder jobBuilder = new JobParametersBuilder();
			jobBuilder.addString("tableName", "POLICES");
			jobBuilder.addString("columnName", "PNOTES");
			// JobParameters jobParameters = jobBuilder.toJobParameters();
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
			JobExecution execution = jobLauncher.run(job, jobParameters);
			System.out.println("Completion Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done!");
	
	}*/
}
