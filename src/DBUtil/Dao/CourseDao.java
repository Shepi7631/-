package DBUtil.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Course;
import model.Teacher;

public class CourseDao {
    public static int CheckCourse(Connection con,String courseid) throws SQLException //检查课程是否存在
    {
        Course course = null;
        String sql = "select * from course where courseid = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,courseid);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return 1;
        }
        return 0;
    }
    public static void insert(Connection coon,Course course) throws SQLException {
        String sql = "insert into course (courseId,courseName,teacherId,teacherName,credit,hour)values(?,?,?,?,?,?)";
        PreparedStatement ps = coon.prepareStatement(sql);
        ps.setString(1,course.getCourseId());
        ps.setString(2,course.getCourseName());
        ps.setString(3,course.getTeacherId());
        ps.setString(4,course.getTeacherName());
        ps.setString(5,course.getCredit());
        ps.setString(6,course.getHour());
        ps.executeUpdate();
    }
    public static void Delete(Connection conn,String courseid) throws SQLException {
        String sql = "delete from course where courseid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,courseid);
        ps.executeUpdate();
    }
}
