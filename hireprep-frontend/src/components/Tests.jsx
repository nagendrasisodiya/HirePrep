import React from 'react';

const Tests = () => {
    return (
        <div className="page-container">
            <h1 className="page-title">MCQ Tests</h1>
            <div className="test-stats">
                <div className="test-stat-card completed">
                    <div className="test-stat-value completed">8</div>
                    <div className="test-stat-label completed">Completed</div>
                </div>
                <div className="test-stat-card ongoing">
                    <div className="test-stat-value ongoing">5</div>
                    <div className="test-stat-label ongoing">Ongoing</div>
                </div>
                <div className="test-stat-card upcoming">
                    <div className="test-stat-value upcoming">7</div>
                    <div className="test-stat-label upcoming">Upcoming</div>
                </div>
            </div>
            <div className="card">
                <h3 className="test-name">Aptitude Mock Test - 1</h3>
                <p className="test-details">50 questions • 60 min • Due: Nov 2, 2025</p>
                <button className="btn-primary btn-full">
                    Start Test
                </button>
            </div>
        </div>
    );
};

export default Tests;