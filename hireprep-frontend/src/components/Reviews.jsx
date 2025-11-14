import React from 'react';
import { Plus, Star } from 'lucide-react';

const Reviews = () => {
    return (
        <div className="page-container">
            <div className="page-header">
                <h1 className="page-title">Company Reviews</h1>
                <button className="btn-primary">
                    <Plus className="w-5 h-5" /> Share Experience
                </button>
            </div>
            <div className="card">
                <div className="review-card-header">
                    <div>
                        <h3 className="review-company">Google</h3>
                        <p className="review-position">SDE Intern</p>
                    </div>
                    <div className="review-rating">
                        <Star className="w-5 h-5 fill-yellow-400 text-yellow-400" />
                        <span>4.5</span>
                    </div>
                </div>
                <p className="review-content">The interview process was very structured. Started with an online coding test, followed by 2 technical rounds focusing on DSA.</p>
                <div className="review-tips">
                    <p className="review-tips-title">Tips:</p>
                    <p className="review-tips-content">Practice medium-hard LeetCode problems, focus on time complexity optimization</p>
                </div>
            </div>
        </div>
    );
};

export default Reviews;