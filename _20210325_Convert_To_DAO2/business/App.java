package _20210325_Convert_To_DAO2.business;

import _20210325_Convert_To_DAO2.persistence.UserDao;
import _20210325_Convert_To_DAO2.persistence.models.User;
import _20210325_Convert_To_DAO2.persistence.models.Mail;
import _20210325_Convert_To_DAO2.persistence.MailDao;

public class App {    

   
    public static void main(String[] args) {
        UserDao uDao = new UserDao();
        MailDao mDao = new MailDao();

        uDao.save(new User("Stanislaus"));
        mDao.save(new Mail("s.stanislaus@tsn.at", 1));
        mDao.save(new Mail("s.stanislaus@gmail.com", 1));
    }

}