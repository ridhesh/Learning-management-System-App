import React, { useEffect, useState } from "react";
import { Trophy } from "react-bootstrap-icons";
import { CircularProgressbarWithChildren, buildStyles } from "react-circular-progressbar";
import "./Progressbar.css";

function Progressbar(props) {
  return (
    <div className="app">
      <div className="size-bar">
        <CircularProgressbarWithChildren
          strokeWidth={10}
          value={props.progress}
          styles={buildStyles({
            
            pathColor: "turquoise",
            trailColor: "white",
          })}
        >
          <Trophy className="trophy-sie"></Trophy>
        </CircularProgressbarWithChildren>
      </div>
    </div>
  );
}

export default Progressbar;
