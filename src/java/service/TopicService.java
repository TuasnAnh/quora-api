/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Topic;

/**
 *
 * @author ADMIN
 */
public interface TopicService {
    
    public boolean checkExistedTopic(String topicname);
    
    public int insertTopic(String topicname, String imageurl);
    
    public boolean editTopic(int id, String topicname, String imageurl);
    
    public boolean deleteTopic(int id);
    
    public List <Topic> getAllTopic();
    
    public Topic getSelectedTopic(int id);
}
