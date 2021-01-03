package com.drew.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
public class drewController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	//안지현 업무일지 리스트 호출하기
	@RequestMapping(value="/getAhnSchedule", method = RequestMethod.GET)
	public ResponseEntity getAhnSchedule() {
		ResponseEntity response = null;
		try {
			String keyByPerson = "716439560";
			String url = "https://millionwaredevcenter.atlassian.net/wiki/rest/api/content/"+keyByPerson+"/child?expand=page.body.VIEW";		
			RestTemplate restTemplate = new RestTemplate();		
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders(){
				{
					String authHeader = "Basic "+"작업 전 수정하기";
					set( "Authorization" , authHeader );
				}
			};
			headers.add("Content-Type", "application/json");		
			response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);
			log.info(response.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
