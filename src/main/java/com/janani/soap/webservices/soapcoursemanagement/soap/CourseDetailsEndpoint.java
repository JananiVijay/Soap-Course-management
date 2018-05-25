package com.janani.soap.webservices.soapcoursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.janani.courses.CourseDetails;
import com.janani.courses.GetCourseDetailsRequest;
import com.janani.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {
	
	//method
	//input - GetCourseDetailsRequest
	//output - GetCourseDetailsResponse
	
	//namespace - http://janani.com/courses
	//name - GetCourseDetailsRequest
	@PayloadRoot(namespace="http://janani.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processGetCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(request.getId());
		courseDetails.setName("Java Beginners Course");
		courseDetails.setDescription("Best course to learn Java!");
		
		
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(courseDetails);
		return response;
	}

}
