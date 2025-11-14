import React, { useState } from 'react';
import Sidebar from './Sidebar';
import AIBot from './AIBot';
import Dashboard from './Dashboard';
import Profile from './Profile';
import Resources from './Resources';
import Tests from './Tests';
import Placements from './Placements';
import Reviews from './Reviews';
import NoticeBoard from './NoticeBoard';
import Attendance from './Attendance';

const HirePrep = () => {
    const [activeSection, setActiveSection] = useState('dashboard');
    const [showAIBot, setShowAIBot] = useState(false);

    const renderContent = () => {
        switch (activeSection) {
            case 'dashboard':
                return <Dashboard />;
            case 'profile':
                return <Profile />;
            case 'resources':
                return <Resources />;
            case 'tests':
                return <Tests />;
            case 'placements':
                return <Placements />;
            case 'reviews':
                return <Reviews />;
            case 'noticeboard':
                return <NoticeBoard />;
            case 'attendance':
                return <Attendance />;
            default:
                return <Dashboard />;
        }
    };

    return (
        <div className="hireprep-container">
            <Sidebar activeSection={activeSection} setActiveSection={setActiveSection} />

            <div className="main-content">
                {renderContent()}
            </div>

            <AIBot showAIBot={showAIBot} setShowAIBot={setShowAIBot} />
        </div>
    );
};

export default HirePrep;