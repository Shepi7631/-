package DBUtil.Dao;

import model.Administrator;
import model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDao {
    public static Student login(Connection con, Student stu)throws Exception
    {
        Student resultUser = null;
        String sql = "select * from student where id = ? and pwd = ?";
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
    public static void update(Connection con, Student stu)throws Exception
    {
        String sqls=" insert into administrator values(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqls);
        ps.setString(1,stu.getId());
        ps.setString(2,stu.getName());
        ps.setString(3,stu.getSex());
        ps.setString(4, stu.getBirthday());
        ps.setString(5,stu.getPwd());
        ps.setString(6,stu.getMajor());
        ps.setString(7,stu.getInstitute());
        ps.executeUpdate();

    }
}
