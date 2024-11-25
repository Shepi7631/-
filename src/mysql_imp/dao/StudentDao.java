package mysql_imp.dao;
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
//�����ѯ���˸�ѧ���ļ�¼���ͻ�ʵ�������ѧ��
        if (rs.next()) {
            resultUser = new Student();
            resultUser.setId(rs.getString("id"));
            resultUser.setName(rs.getString("name"));
            resultUser.setPwd(rs.getString("pwd"));
            resultUser.setSex(rs.getString("sex"));
        }
        return resultUser;
    }
}
