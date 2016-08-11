//package com.ink.pats.atp.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.pats.atp.batch.api.ACC30420FileBatchService;
//import com.ink.pats.atp.batch.impl.InterestBatchService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
//public class InterestBatchServiceTest  {
//	private Logger logger = LoggerFactory.getLogger("SpringBatchTest");
//	@Autowired
//	private ACC30420FileBatchService acc30420FileBatchService;
//	@Test
//	public void interestBatchServiceTest() {
////		SimpleJobLauncher launcher = new SimpleJobLauncher();
////		launcher.setJobRepository((JobRepository) getBean("jobRepository"));
////		launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
////		try {
////			Job job = (Job) getBean("metaDataJobName");
////			launcher.run(job,  new JobParametersBuilder()
////					.addString("inputFilePath",
////                    "test-batch.txt").toJobParameters());
////			Thread.sleep(10000L);
////		} catch (Exception e) {
////			e.printStackTrace();
////			logger.error("", e);
////		}
//		
//		try {
//			acc30420FileBatchService.exec("test-batch.txt", "41234124");
//			Thread.sleep(30000L);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
