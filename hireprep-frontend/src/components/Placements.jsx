import React from 'react';

const Placements = () => {
    return (
        <div className="page-container">
            <h1 className="page-title">Placement Opportunities</h1>
            <div className="placements-grid">
                <div className="card">
                    <div className="placement-card-header">
                        <div className="placement-emoji">🔵</div>
                        <div>
                            <h3 className="placement-card-title">Google</h3>
                            <p className="placement-card-role">SDE Intern</p>
                        </div>
                    </div>
                    <div className="placement-info">
                        <div className="placement-info-row">
                            <span className="placement-info-label">CTC</span>
                            <span className="placement-info-value">12 LPA</span>
                        </div>
                        <div className="placement-info-row">
                            <span className="placement-info-label">Location</span>
                            <span className="placement-info-value">Bangalore</span>
                        </div>
                    </div>
                    <button className="btn-primary btn-full">
                        Apply Now
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Placements;