package edu.bh.cosumer.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LBUserGenerator {

	@Autowired
	private LoadBalancerClient balancerClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${userid}")
	private String user;
	
	@Value("${pwd}")
	private String pwd;

	
	@GetMapping("/lbusers")
	public List<Object> genearateUsers(){
		ServiceInstance instance = balancerClient.choose("user-service");
		System.out.println("user service uri "+instance.getUri());
		String url = instance.getUri()+"/v2/users";
		
		HttpHeaders headers = createHttpHeaders(user,pwd);
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		RestTemplate restTemplate1 = new RestTemplate();
		final ResponseEntity<Object[]> responseEntity = restTemplate1.exchange(url, HttpMethod.GET, entity, Object[].class);
		
		
		System.out.println("LB Client response "+responseEntity.getStatusCode());
		return Arrays.asList(responseEntity.getBody());
		
	}
	
	
	private HttpHeaders createHttpHeaders(String user, String password)
	{
	    String notEncoded = user + ":" + password;
	    String encodedAuth = Base64.getEncoder().encodeToString(notEncoded.getBytes());
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Authorization", "Basic " + encodedAuth);
	    return headers;
	}
	
}
