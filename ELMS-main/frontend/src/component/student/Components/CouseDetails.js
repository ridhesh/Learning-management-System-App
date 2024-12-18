import React, { useEffect, useState } from "react";
import CourseHeading from "./CourseHeading";
import CourseNavBar from "./CourseNavbar";
import WhatLearn from "./WhatLearn";
import CourseContent from "./CourseContent";
import Requirement from "./Requirement";
import Description from "./Description";
import Instructor from "./Instructor";
import ShowFeedback from "./ShowFeedback";
import Cards from "./Cards";
import { useNavigate, useParams } from "react-router-dom";
import { ClipLoader } from "react-spinners";
import { LOADING_COLOR } from "../../../utils/constants.js";
import { getCourseDetails } from "../../courses/courses-util.js";
import "../Styles/Course_details.css";
import { calculateDiscountedPrice } from "../../../utils/util.js";
import { makePayment } from "../../payment/payment-utils.js";
import "../Styles/Course_details.css";

export default function CouseDetails({ userData }) {
  const navigate = useNavigate();
  const params = useParams();
  const courseId = params.courseId;
  const [isLoading, setIsLoading] = useState(true);
  const [courseDetails, setCourseDetails] = useState({});
  const fetchCourseDetails = async () => {
    setIsLoading(true);
    console.log("Course id:", courseId, "Stu Id:", userData.studentId);
    const courseData = await getCourseDetails(courseId, userData.studentId);
    console.log(courseData);
    setCourseDetails(courseData);
    setIsLoading(false);
  };

  const handleEnrollStudent = () => {
    // console.log(first)
    const discountAmount = calculateDiscountedPrice(
      courseDetails.course.coursePrice,
      courseDetails.course.courseDiscount
    );
    const response = makePayment(userData, courseId, discountAmount);
    console.log(response);
  };

  useEffect(() => {
    fetchCourseDetails();
  }, [userData]);

  return (
    <div className="course-view-container">
      {isLoading ? (
        <ClipLoader color={LOADING_COLOR} size="50px" />
      ) : (
        <>
          <CourseNavBar
            course={courseDetails.course}
            handleEnrollStudent={handleEnrollStudent}
          />
          <Cards
            course={courseDetails.course}
            userData={userData}
            handleEnrollStudent={handleEnrollStudent}
          />
          <CourseHeading course={courseDetails.course} />
          <div className="main-content">
            <WhatLearn></WhatLearn>
            <CourseContent course={courseDetails.course} />
            <Requirement />
            <Description desc={courseDetails.course.courseDescription} />
            <Instructor instructorName={courseDetails.course.instructorName} />
            <ShowFeedback feedbacks={courseDetails.feedbacks} />
          </div>
        </>
      )}
    </div>
  );
}
