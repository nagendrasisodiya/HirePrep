import React from 'react';
import { FileText, Download } from 'lucide-react';

const Resources = () => {
    return (
        <div className="page-container">
            <h1 className="page-title">Study Resources</h1>
            <div className="resources-grid">
                <div className="card">
                    <div className="resource-icon">
                        <FileText className="w-6 h-6" />
                    </div>
                    <h3 className="resource-title">Data Structures & Algorithms</h3>
                    <p className="resource-info">250 pages • 1.2k downloads</p>
                    <button className="resource-button">
                        <Download className="w-4 h-4" /> Download
                    </button>
                </div>
                <div className="card">
                    <div className="resource-icon">
                        <FileText className="w-6 h-6" />
                    </div>
                    <h3 className="resource-title">Aptitude Questions Bank</h3>
                    <p className="resource-info">180 pages • 890 downloads</p>
                    <button className="resource-button">
                        <Download className="w-4 h-4" /> Download
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Resources;