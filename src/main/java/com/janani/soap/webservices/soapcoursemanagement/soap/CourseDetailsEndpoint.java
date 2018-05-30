package com.janani.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.janani.courses.CourseDetails;
import com.janani.courses.DeleteCourseDetailsRequest;
import com.janani.courses.DeleteCourseDetailsResponse;
import com.janani.courses.GetAllCourseDetailsRequest;
import com.janani.courses.GetAllCourseDetailsResponse;
import com.janani.courses.GetCourseDetailsRequest;
import com.janani.courses.GetCourseDetailsResponse;
import com.janani.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.janani.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import com.janani.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService.Status;

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
		return mapCourseDetails(course);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();		
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();	
		for(Course course: courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}
	
	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	@PayloadRoot(namespace="http://janani.com/courses", localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllGetCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		
		List<Course> courses = service.findAll();
		
		return mapAllCourseDetails(courses);
	}
	
	@PayloadRoot(namespace="http://janani.com/courses", localPart="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {

		Status status = service.deleteById(request.getId());

		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));

		return response;
	}

	private com.janani.courses.Status mapStatus(Status status) {
		if(status == Status.FAILURE)
			return com.janani.courses.Status.FAILURE;
		return com.janani.courses.Status.SUCCESS;
	}

}
