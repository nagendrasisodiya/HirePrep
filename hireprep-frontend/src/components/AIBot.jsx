import React, { useState } from 'react';
import { Bot, X, Send } from 'lucide-react';

const AIBot = ({ showAIBot, setShowAIBot }) => {
    const [chatMessages, setChatMessages] = useState([
        { type: 'bot', text: 'Hi! I am your HirePrep AI assistant. I can help you with interview prep, resume tips, company insights, and placement guidance. How can I help you today?' }
    ]);
    const [currentMessage, setCurrentMessage] = useState('');

    const handleSendMessage = () => {
        if (currentMessage.trim()) {
            const newMessages = [...chatMessages, { type: 'user', text: currentMessage }];
            setChatMessages(newMessages);

            setTimeout(() => {
                setChatMessages([...newMessages, { type: 'bot', text: getAIResponse(currentMessage) }]);
            }, 500);

            setCurrentMessage('');
        }
    };

    const getAIResponse = (message) => {
        const lowerMsg = message.toLowerCase();

        if (lowerMsg.includes('resume') || lowerMsg.includes('cv')) {
            return 'For a strong resume: 1) Keep it to 1 page, 2) Use action verbs, 3) Quantify achievements, 4) Tailor it to each job, 5) Include relevant projects and skills.';
        } else if (lowerMsg.includes('interview')) {
            return 'Interview tips: 1) Research the company thoroughly, 2) Prepare STAR method examples, 3) Practice common technical questions, 4) Prepare questions for the interviewer, 5) Dress professionally.';
        } else if (lowerMsg.includes('google') || lowerMsg.includes('microsoft') || lowerMsg.includes('amazon')) {
            return 'For FAANG interviews: Focus on Data Structures and Algorithms, practice on LeetCode, understand system design basics, prepare behavioral questions using STAR method.';
        } else {
            return 'I can help you with resume building, interview preparation, company-specific guidance, salary negotiation, test strategies, and career advice. What would you like to know more about?';
        }
    };

    return (
        <>
            <button
                onClick={() => setShowAIBot(!showAIBot)}
                className="ai-bot-trigger"
            >
                <Bot className="w-6 h-6" />
            </button>

            {showAIBot && (
                <div className="ai-bot-container">
                    <div className="ai-bot-header">
                        <span className="ai-bot-title">HirePrep AI Assistant</span>
                        <button onClick={() => setShowAIBot(false)} className="ai-bot-close">
                            <X className="w-4 h-4" />
                        </button>
                    </div>
                    <div className="ai-bot-messages">
                        {chatMessages.map((msg, idx) => (
                            <div key={idx} className={`ai-message ${msg.type}`}>
                                {msg.text}
                            </div>
                        ))}
                    </div>
                    <div className="ai-bot-input-container">
                        <input
                            type="text"
                            placeholder="Type a message..."
                            className="ai-bot-input"
                            value={currentMessage}
                            onChange={(e) => setCurrentMessage(e.target.value)}
                            onKeyDown={(e) => e.key === 'Enter' && handleSendMessage()}
                        />
                        <button
                            onClick={handleSendMessage}
                            className="ai-bot-send"
                        >
                            <Send className="w-4 h-4" />
                        </button>
                    </div>
                </div>
            )}
        </>
    );
};

export default AIBot;