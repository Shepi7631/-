package DBUtil.Dao;

import model.Administrator;
import model.Student;
import model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDao {
    public static Teacher login(Connection con, Teacher teacher)throws Exception
    {
        Teacher resultUser = null;
        String sql = "select * from teacher where id = ? and pwd = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,teacher.getId());
        ps.setString(2,teacher.getPwd());
        ResultSet rs = ps.executeQuery();
//如果查询到了该学生的记录，就会实例化这个学生
        if (rs.next()) {
            resultUser = new Teacher();
            resultUser.setId(rs.getString("id"));
            resultUser.setName(rs.getString("name"));
            resultUser.setPwd(rs.getString("pwd"));
            resultUser.setSex(rs.getString("sex"));
        }
        return resultUser;
    }
    public static void update(Connection con, Teacher stu)throws Exception
    {
        String sqls=" insert into administrator(id,name,sex,birthday,pwd,Major,institute) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqls);
        ps.setString(1,stu.getId());
        ps.setString(2,stu.getName());
        ps.setString(3,stu.getSex());
        ps.setString(5,stu.getPwd());
        ps.setString(4, stu.getBirthday());
        ps.setString(6,stu.getMajor());
        ps.setString(7,stu.getInstitute());
        ps.executeUpdate();

    }
}
