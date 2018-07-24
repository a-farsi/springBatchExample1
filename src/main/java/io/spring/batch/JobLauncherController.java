package io.spring.batch;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@RequestMapping("/launchjob")
	public String launchJob() throws Exception {

		try {
			JobParametersBuilder jobBuilder = new JobParametersBuilder();
			jobBuilder.addString("tableName", "POLICES");
			jobBuilder.addString("columnName", "PNOTES");
			jobBuilder.addLong("time", System.currentTimeMillis()).toJobParameters();
			// JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
			JobParameters jobParameters = jobBuilder.toJobParameters();

			jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return "Done ! ";
	}
}
