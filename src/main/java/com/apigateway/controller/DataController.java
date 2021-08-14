package com.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/covid-19/api/v1")
public class DataController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	@GetMapping("/get_date_info")
	public ResponseEntity<String> getDateInfo(@RequestParam("date") String date){
		
		ServiceInstance serviceInstance = discoveryClient.getInstances("DATA-SERVICE").get(0);
		System.out.println("Communicating to "+serviceInstance.getInstanceId()+"...");
		String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/get_date_info?date="+date;
		
		return restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<String>() {});
	}
	
	@GetMapping("/get_state_info")
	public ResponseEntity<String> getStateInfo(@RequestParam("state") String state){
		
		ServiceInstance serviceInstance = discoveryClient.getInstances("DATA-SERVICE").get(0);
		System.out.println("Communicating to "+serviceInstance.getInstanceId()+"...");
		String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/get_state_info?state="+state;
		
		return restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<String>() {});
	}
	
	@GetMapping("/pinpoint_state")
	public ResponseEntity<String> getDateInfo(@RequestParam("date") String date, @RequestParam("state") String state){
		
		ServiceInstance serviceInstance = discoveryClient.getInstances("DATA-SERVICE").get(0);
		System.out.println("Communicating to "+serviceInstance.getInstanceId()+"...");
		String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/pinpoint_state?date="+date+"&state="+state;
		
		return restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<String>() {});
	}
}
