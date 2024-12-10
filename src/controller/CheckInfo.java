package controller;

import DBUtil.DBUtil;
import DBUtil.Dao.AdministratorDao;
import DBUtil.Dao.StudentDao;
import DBUtil.Dao.TeacherDao;
import model.Administrator;
import model.Student;
import model.Teacher;
import model.User;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;


public class CheckInfo {
    /*
     * 登陆时检查用户信息
     */
    private DBUtil dbUtil = new DBUtil();


    public int isMember(String table, String id, String passwd) {

        // String file = "D://test//".concat(table.concat(".txt"));
        String file = System.getProperty("user.dir") + "/data".concat("/").concat(table).concat(".txt");
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

    public int CheckMember(UserType us,String id, String passwd)
    {
        Connection conn = null;
        int judge=1;
        if (us==UserType.Administrator)
        {
            Administrator adm=new Administrator();
            adm.setId(id);
            adm.setPwd(passwd);
            Administrator current=null;
            try {
                conn = dbUtil.getConnection();
                current = AdministratorDao.login(conn, adm);
                if (current == null) {
                    judge=0;
                } else {
                    judge=1;
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
        } else if (us==UserType.Teacher)
        {
            Teacher tea=new Teacher();
            tea.setId(id);
            tea.setPwd(passwd);
            Teacher current=null;
            try {
                conn = dbUtil.getConnection();
                current = TeacherDao.login(conn, tea);
                if (current == null) {
                    judge=0;
                } else {
                    judge=1;
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
        }else if (us==UserType.Student)
        {
            Student stu = new Student();
            stu.setId(id);
            stu.setPwd(passwd);
            Student current=null;
            try {
                conn = dbUtil.getConnection();
                current = StudentDao.login(conn, stu);
                if (current == null) {
                    judge=0;
                } else {
                    judge=1;
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
        }
    return judge;
    }
    public int FindMember(UserType us,String id) {
        Connection conn = null;
        int judge=1;
        if (us==UserType.Administrator)
        {
            Administrator adm=new Administrator();
            adm.setId(id);
            Administrator current=null;
            try {
                conn = dbUtil.getConnection();
                judge = AdministratorDao.find(conn, adm);

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
        } else if (us==UserType.Teacher)
        {
            Teacher tea=new Teacher();
            tea.setId(id);
            Teacher current=null;
            try {
                conn = dbUtil.getConnection();
                judge = TeacherDao.find(conn, tea);

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
        }else if (us==UserType.Student)
        {
            Student stu = new Student();
            stu.setId(id);
            Student current=null;
            try {
                conn = dbUtil.getConnection();
                judge = StudentDao.find(conn, stu);

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
        }
        return judge;
    }




    public void AddMmber_Administrator(Administrator adm) {
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            AdministratorDao.update(conn, adm);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.close_Con(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    public void AddMmber_Teacher(Teacher adm)
    {
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            TeacherDao.update(conn,adm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                dbUtil.close_Con(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void AddMmber_Student(Student adm)
    {
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            StudentDao.update(conn,adm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                dbUtil.close_Con(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void DeleteMember_Administrator(Administrator adm) {
        Connection conn = null;
        try {
            conn=dbUtil.getConnection();
            String id=adm.getId();
            AdministratorDao.delete(conn,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DeleteMember_Student(Student stu) {
        Connection conn = null;
        try {
            conn=dbUtil.getConnection();
            String id=stu.getId();
            StudentDao.delete(conn,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DeleteMember_Teacher(Teacher teacher) {
        Connection conn = null;
        try {
            conn=dbUtil.getConnection();
            String id=teacher.getId();
            TeacherDao.delete(conn,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


