import React, { useEffect, useState } from "react";
import { Outlet, useParams, useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
// import "../../../styles/manage-course/ManageCourse.css";
import "../../../styles/Navigationbar.css";
import ManageCourseNavbar from "./ManageCourseNavbar";
import SideNavBar from "./SideNavBar";
import DeleteCourse from "./DeleteCourse";

export default function ManageCourse() {
  const params = useParams();
  const navigate = useNavigate();

  const courseId = params.courseId;

  const [course, setCourse] = useState({
    courseName: "Course name",
    courseDuration: "0",
  });
  const [shouldStateChange, setShouldStateChange] = useState(true);
  const [showDelete, setShowDelete] = useState(false);

  useEffect(() => {
    fetchCourseDuration();
  }, []);
  useEffect(() => {
    fetchCourseDuration();
  }, [shouldStateChange]);

  const fetchCourseDuration = async () => {
    //fetchcourse name and course duration, and set details in course state
  };
  const handleDeleteCourse = () => {
    console.log("deleted");
    //delete the course using courseId
    // navigate("/instructor");
  };
  const manageDeleteCourse = () => {
    setShowDelete(true);
  };

  return (
    <div>
      <ManageCourseNavbar
        CourseName={course.courseName}
        CourseDuration={course.courseDuration}
      />
      <div className="manage-course-container">
        <div className="side-navbar">
          <SideNavBar
            manageDeleteCourse={manageDeleteCourse}
            courseId={courseId}
          />
        </div>
        {showDelete && (
          <DeleteCourse
            handleDeleteCourse={handleDeleteCourse}
            showDelete={showDelete}
            setShowDelete={setShowDelete}
          />
        )}
        <div className="manage-course-content">
          <Outlet />
        </div>
      </div>
    </div>
  );
}
