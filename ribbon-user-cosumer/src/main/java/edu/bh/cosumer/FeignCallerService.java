package edu.bh.cosumer;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="user-service",configuration = FeignConfigurer.class)
public interface FeignCallerService {
	
	@RequestMapping(method = RequestMethod.GET,value = "/v2/users")
	public List<Object> getUsers();
	
	@RequestMapping(method = RequestMethod.POST, value="/v2/adduser")
	public Object addUser(Object body);

}
