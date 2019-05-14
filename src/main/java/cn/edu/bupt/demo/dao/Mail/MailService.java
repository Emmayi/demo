package cn.edu.bupt.demo.dao.Mail;

public interface MailService {
    public void sendMessageMail(Object params, String to,String title, String templateName);
}
