import React from 'react';

const NoticeBoard = () => {
    return (
        <div className="page-container">
            <h1 className="page-title">Notice Board</h1>
            <div className="card">
                <div className="notice-header">
                    <h3 className="notice-title">Google Recruitment Drive - Registration Open</h3>
                    <span className="notice-priority">HIGH</span>
                </div>
                <p className="notice-content">Google is visiting campus on November 5, 2025 for SDE Intern positions. Eligibility: CGPA ≥ 7.5, No backlogs. Registration deadline: Nov 1, 2025.</p>
            </div>
        </div>
    );
};

export default NoticeBoard;