package com.elms.databaseservice.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_course_lesson")
@IdClass(StudentCourseLessonId.class)
public class StudentCourseLesson {
	@JsonIgnore
	@Id
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "stu_id")
	//@Setter(value = AccessLevel.NONE)
	private Student studentId;
	@JsonIgnore
	@Id
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "course_id")
	//@Setter(value = AccessLevel.NONE)
	private Course courseId;
	@JsonIgnore
	@Id
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "lesson_id")
	//@Setter(value = AccessLevel.NONE)
	private Lesson lessonId;

}
