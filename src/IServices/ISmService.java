/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Sms;

/**
 *
 * @author BSS
 */
public interface ISmService {
    
    void sendSms(Sms sms);
    
}
