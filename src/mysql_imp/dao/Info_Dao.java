package mysql_imp.dao;

import model.Administrator;
import model.Student;
import model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Info_Dao {
    public Student login_stu(Connection con, Student stu)throws Exception
    {
        Student resultUser = null;
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,stu.getId());
        ps.setString(2,stu.getPwd());
        ResultSet rs = ps.executeQuery();
//如果查询到了该学生的记录，就会实例化这个学生
        if (rs.next()) {
            resultUser = new Student();
            resultUser.setId(rs.getString("id"));
            resultUser.setName(rs.getString("name"));
            resultUser.setPwd(rs.getString("pwd"));
            resultUser.setSex(rs.getString("sex"));
        }
        return resultUser;
    }

    public Teacher login_teacher(Connection con, Teacher teacher)throws Exception
    {
        Teacher resultUser = null;
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,Teacher.getId());
        ps.setString(2,Teacher.getPwd());
        ResultSet rs = ps.executeQuery();
//如果查询到了该老师的记录，就会实例化这个老师
        if (rs.next()) {
            resultUser = new Teacher();
            resultUser.setId(rs.getString("id"));
            resultUser.setName(rs.getString("name"));
            resultUser.setPwd(rs.getString("pwd"));
            resultUser.setSex(rs.getString("sex"));
        }
        return resultUser;
    }
    public Administrator login_adm(Connection con, Administrator adm )throws Exception
    {
        Administrator resultUser = null;
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,adm.getId());
        ps.setString(2,adm.getPwd());
        ResultSet rs = ps.executeQuery();
//如果查询到了该学生的记录，就会实例化这个学生
        if (rs.next()) {
            resultUser = new Administrator();
            resultUser.setId(rs.getString("id"));
            resultUser.setName(rs.getString("name"));
            resultUser.setPwd(rs.getString("pwd"));
            resultUser.setSex(rs.getString("sex"));
        }
        return resultUser;
    }


}
