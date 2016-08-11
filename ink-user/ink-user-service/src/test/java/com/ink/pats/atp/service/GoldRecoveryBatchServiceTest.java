//package com.ink.pats.atp.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
//public class GoldRecoveryBatchServiceTest  {
//	private Logger logger = LoggerFactory.getLogger("SpringBatchTest");
//	@Autowired
//	private ACC30410FileBatchService acc30410FileBatchService;
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
//			acc30410FileBatchService.exec("g-test-batch.txt", "41234124");
//			Thread.sleep(30000L);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
