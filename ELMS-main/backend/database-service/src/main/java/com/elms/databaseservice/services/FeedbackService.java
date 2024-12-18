package com.elms.databaseservice.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elms.databaseservice.models.Feedback;
import com.elms.databaseservice.models.StudentCourseFeedbackId;
import com.elms.databaseservice.models.StudentCourseId;
import com.elms.databaseservice.repos.FeedbackRepo;

@Service
public class FeedbackService {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(FeedbackService.class);
	@Autowired
	FeedbackRepo repo;

	@Transactional
	public ResponseEntity<List<Feedback>> getAllFeedbacks() {
		logger.info("Inside Feedback Service");
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<List<Feedback>> getAllFeedbacksByCourseId(int cid) {
//		List<Feedback> sc = getAllFeedbacks().getBody();
//		List<Feedback> fc = new ArrayList<>();
//		for (Feedback f : sc) {
//			if (f.getStudentCourseId().getCourseId().getCourseId() == id)
//				fc.add(f);
//		}
		List<Feedback> feedbacks=repo.findByCourseId(cid);
		logger.info(feedbacks.get(0).getStudentCourseId().getStudentId().getStudentName());


		return new ResponseEntity<List<Feedback>>(feedbacks, HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<String> storeFeedback(Feedback feedback,int studentId,int courseId) {
//		List<Feedback> sc = getAllFeedbacks().getBody();
//		logger.info(feedback.toString());
//		Feedback feed = new Feedback();
//		for (Feedback f : sc) {
//			if (f.getStudentCourseId().getCourseId().getCourseId() == feedback.getStudentCourseId().getCourseId()
//					.getCourseId()
//					&& feedback.getStudentCourseId().getStudentId().getStudentId() == f.getStudentCourseId()
//							.getStudentId().getStudentId()) {
//				feed = (f);
//				break;
//			}
//		}
		Feedback feed = repo.findByStudentCourseId(studentId,courseId);
        if(feed!=null)
        {
            feed.setContent(feedback.getContent());
            feed.setRatings(feedback.getRatings());
            repo.save(feed);
            logger.debug("Updated Feedback");
            return new ResponseEntity<>("Updated Feedback " + feedback.getContent() + " successfully!", HttpStatus.CREATED);
        }
        else
        {
            logger.info(feedback.getStudentCourseId().getCourseStatus());
            repo.save(feedback);

            logger.debug("Stored Feedback");
            return new ResponseEntity<>("Stored Feedback " + feedback.getContent() + " successfully!", HttpStatus.CREATED);
        }
	}

	@Transactional
	public ResponseEntity<String> deleteFeedback(int studentId, int courseId) {
		Feedback feedback = repo.findByStudentCourseId(studentId, courseId);
		if (feedback == null) {
			logger.info("No such feedback exits");
			return new ResponseEntity<>("No such feedback exits", HttpStatus.NOT_FOUND);
		}
		repo.delete(feedback);
		logger.info("deleted");
		return new ResponseEntity<>("Deleted Feedback Successfully", HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<Feedback> existFeedbackById(int studentId, int courseId) {
		Feedback feedback = repo.findByStudentCourseId(studentId, courseId);
		if (feedback == null)
		{
			logger.info("Feedback does not exist");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}else
			return new ResponseEntity<>(feedback, HttpStatus.OK);
	}
}
