package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBUtil {
    //注册驱动
    //获取连接
    String url ="jdbc:mysql://127.0.0.1:3306/student_management" ;
    String username="test";
    String password="041832";
    String jdbc_Name="com.mysql.cj.jdbc.Driver";
//获取数据库连接


    public Connection getConnection()throws Exception
    {
        Class.forName(jdbc_Name);
        Connection Con=DriverManager.getConnection(url,username,password);
        return Con;
    }

    public void close_Con(Connection Con)throws Exception
    {
        if (Con!=null)
        {
            Con.close();
        }
    }

    public static void main(String[] args)
    {
        DBUtil db=new DBUtil();
    }

}
