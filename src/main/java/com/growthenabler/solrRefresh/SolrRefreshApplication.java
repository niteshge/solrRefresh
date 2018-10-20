package com.growthenabler.solrRefresh;

import com.growthenabler.solrRefresh.home.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class SolrRefreshApplication implements CommandLineRunner {

	@Autowired
	HomeController homeController;

	public static void main(String[] args) {

		SpringApplication.run(SolrRefreshApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		homeController.home();
	}
}
