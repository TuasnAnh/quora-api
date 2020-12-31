/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Answer {

    private int answerId;
    private int userId;
    private int questionId;
    private String content;
    private String time;
    private int upvote;
    private int downvote;
    private String authorCredential;
    private String question;
    private boolean isUpvote;
    private boolean isDownvote;

    public Answer(int answerId, int userId, String time, String authorCredential, String question, int questionId, String content, int upvote, int downvote, boolean isUpvote, boolean isDownvote) {
        this.answerId = answerId;
        this.userId = userId;
        this.time = time;
        this.authorCredential = authorCredential;
        this.question = question;
        this.questionId = questionId;
        this.content = content;
        this.upvote = upvote;
        this.downvote = downvote;
        this.isUpvote = isUpvote;
        this.isDownvote = isDownvote;
    }

    public Answer(int answerId, int userId, int questionId, String content, String time, int upvote, int downvote) {
        this.answerId = answerId;
        this.userId = userId;
        this.questionId = questionId;
        this.content = content;
        this.time = time;
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthorCredential() {
        return authorCredential;
    }

    public void setAuthorCredential(String authorCredential) {
        this.authorCredential = authorCredential;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote(int downvote) {
        this.downvote = downvote;
    }

    public boolean isIsUpvote() {
        return isUpvote;
    }

    public void setIsUpvote(boolean isUpvote) {
        this.isUpvote = isUpvote;
    }

    public boolean isIsDownvote() {
        return isDownvote;
    }

    public void setIsDownvote(boolean isDownvote) {
        this.isDownvote = isDownvote;
    }

}
