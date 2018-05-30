package com.janani.soap.webservices.soapcoursemanagement.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.janani.courses.CourseDetails;
import com.janani.courses.GetCourseDetailsRequest;
import com.janani.courses.GetCourseDetailsResponse;
import com.janani.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.janani.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;

@Endpoint
public class CourseDetailsEndpoint {
	
	
	@Autowired
	CourseDetailsService service;
	//method
	//input - GetCourseDetailsRequest
	//output - GetCourseDetailsResponse
	
	//namespace - http://janani.com/courses
	//name - GetCourseDetailsRequest
	@PayloadRoot(namespace="http://janani.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processGetCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		
		Course course = service.findById(request.getId());
		
		return mapCourse(course);
	}
	private GetCourseDetailsResponse mapCourse(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();	
		
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		
		response.setCourseDetails(courseDetails);
		return response;
	}

}
