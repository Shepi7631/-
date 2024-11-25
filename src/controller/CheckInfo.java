package controller;

import model.Student;
import model.User;
import mysql_imp.DBUtil;
import mysql_imp.dao.Info_Dao;
import mysql_imp.dao.StudentDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;


public class CheckInfo {
    /*
     * 登陆时检查用户信息
     */
    private DBUtil dbUtil = new DBUtil();
    private StudentDao stu = new StudentDao();

    public int isMember(String table, String id, String passwd) {

        String file = "D://test//".concat(table.concat(".txt"));
        //String file = System.getProperty("user.dir") + "/data".concat("/").concat(table).concat(".txt");
        // StringBuilder result = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                String[] result = s.split(" ");
                if (result[0].equals(id) && result[1].equals(passwd)) {
                    br.close();
                    return 1;// 判断登录信息是否正确
                }
                if (result[0].equals(id)) {
                    br.close();
                    return 2;// 判断该用户是否存在
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

//    public UserType CheckMember(String id, String passwd) {
//        String file = System.getProperty("user.dir") + "/data".concat("/user.txt");
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
//            String s = null;
//            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
//                String[] result = s.split(" ");
//                if (result[0].equals(id) && result[1].equals(passwd)) {
//                    br.close();
//                    switch (result[7]) {
//                        case "student":
//                            return UserType.Student;
//                        case "teacher":
//                            return UserType.Teacher;
//                        case "admin":
//                            return UserType.Administrator;
//                        default:
//                            return UserType.Error;
//                    }
//                }
//            }
//            br.close();
//            return UserType.Error;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return UserType.Error;
//    }
    public UserType Checke_member( String id, String passwd) {

        UserType us=null;
        Student stu = new Student();
        stu.setId(id);
        stu.setPwd(passwd);
        Connection conn = null;
        Student current_stu = null;
        try {
            conn = dbUtil.getConnection();
            current_stu = StudentDao.login(conn, stu);
            if (current_stu == null) {
                us=UserType.Error;
            } else {
                us= UserType.Student;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                dbUtil.close_Con(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return us;


    }

}
