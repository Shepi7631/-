package controller;

// 导入必要的类和包

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Course;

import static DBUtil.Dao.CourseDao.CheckCourse;

// SuppressWarnings用于抑制“serial”警告（JFrame类实现了Serializable接口）
@SuppressWarnings("serial")
public class AddCourse extends JFrame implements ActionListener {
    /*
     * 教师增加课程功能
     */
  /*
总结
功能描述：
该代码实现了教师增加课程的功能，通过图形界面输入课程信息，并将信息保存到文件中。
输入校验：
检查输入框不能为空。
检查课程是否已存在。
文件操作：
读取和写入课程信息到文件。
为新增课程创建相关的成绩文件和学生文件。
界面交互：
使用JOptionPane提示用户操作结果。
使用JTextField获取用户输入。
*/


    // 定义界面组件
    JPanel contain;
    JButton submit;
    JLabel id, name, gredit, classH, teacherId, teacherName;
    JTextField idt, namet, creditt, classHt, teacherIdt, teacherNamet;

    // 构造方法，初始化窗口和组件
    public AddCourse() {
        super("增加课程"); // 设置窗口标题
        setSize(400, 400); // 设置窗口大小
        setLocation(600, 400); // 设置窗口位置
        contain = new JPanel(); // 初始化面板
        contain.setLayout(null); // 设置布局为空布局

        // 初始化标签
        id = new JLabel("课程号");
        name = new JLabel("课程名");
        gredit = new JLabel("学分");
        classH = new JLabel("学时");
        teacherId = new JLabel("教师编号");
        teacherName = new JLabel("教师姓名");

        // 初始化按钮和文本框
        submit = new JButton("提交");
        idt = new JTextField();
        namet = new JTextField();
        creditt = new JTextField();
        classHt = new JTextField();
        teacherIdt = new JTextField();
        teacherNamet = new JTextField();

        // 设置组件位置
        id.setBounds(42, 35, 75, 35);
        idt.setBounds(80, 35, 150, 35);
        name.setBounds(40, 90, 75, 35);
        namet.setBounds(80, 90, 150, 35);
        gredit.setBounds(45, 145, 75, 35);
        creditt.setBounds(80, 145, 150, 35);
        classH.setBounds(45, 200, 75, 35);
        classHt.setBounds(80, 200, 150, 35);
        teacherId.setBounds(20, 245, 75, 35);
        teacherIdt.setBounds(80, 245, 150, 35);
        teacherName.setBounds(20, 280, 75, 35);
        teacherNamet.setBounds(80, 280, 150, 35);
        submit.setBounds(102, 320, 70, 30);

        // 添加组件到面板
        contain.add(id);
        contain.add(idt);
        contain.add(name);
        contain.add(namet);
        contain.add(gredit);
        contain.add(creditt);
        contain.add(classH);
        contain.add(classHt);
        contain.add(teacherId);
        contain.add(teacherIdt);
        contain.add(teacherName);
        contain.add(teacherNamet);
        contain.add(submit);

        // 添加按钮监听器
        submit.addActionListener(this);
        add(contain); // 将面板添加到窗口
        setVisible(true); // 设置窗口可见
        enableEvents(AWTEvent.WINDOW_EVENT_MASK); // 启用窗口事件处理
    }

    // 检查课程是否已经存在
//    public int hasCourse(String id) {
//        String file = System.getProperty("user.dir") + "/data/course.txt"; // 获取课程文件路径
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file)); // 创建BufferedReader读取文件
//            String s = null;
//            while ((s = br.readLine()) != null) { // 按行读取文件
//                String[] result = s.split(" "); // 按空格分割字段
//                if (result[0].equals(id)) { // 检查课程号是否匹配
//                    br.close();
//                    return 1; // 如果课程已存在，返回1
//                }
//            }
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace(); // 异常处理
//        }
//        return 0; // 如果课程不存在，返回0
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) { // 如果点击了提交按钮
            // 检查输入框是否为空
            if (idt.getText().equals("") || namet.getText().equals("") || creditt.getText().equals("")
                    || classHt.getText().equals("") || teacherIdt.getText().equals("") || teacherNamet.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // 检查课程是否存在
                if(new CheckInfo().Check_Course(idt.getText()) == 1) {
                    JOptionPane.showMessageDialog(null, "此课程已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
//                    String file = System.getProperty("user.dir") + "/data/course.txt"; // 获取课程文件路径
//                    ArrayList<String> modifiedContent = new ArrayList<>(); // 存储文件的更新内容
//                    try {
//                        BufferedReader br = new BufferedReader(new FileReader(file));
//                        String s = null;
//                        while ((s = br.readLine()) != null) { // 读取已有文件内容
//                            modifiedContent.add(s); // 添加到数组列表
//                        }
//                        br.close();
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }

                    // 创建新的课程对象
                    Course course = new Course(idt.getText(), namet.getText(), teacherIdt.getText(), teacherNamet.getText(),
                            creditt.getText(), classHt.getText());

                    // 添加新课程的信息
//                    modifiedContent.add(course.getCourseId() + " " + course.getCourseName() + " " + course.getCredit() + " "
//                            + course.getHour() + " " + course.getTeacherId() + " " + course.getTeacherName());

                    // 将更新后的内容写回文件
                    try {
//                        FileWriter fw = new FileWriter(file);
//                        BufferedWriter bw = new BufferedWriter(fw);
//                        for (String element : modifiedContent) {
//                            bw.write(element);
//                            bw.newLine();
//                        }
//                        bw.close();
//                        fw.close();
                        new CheckInfo().Insert_Course(course);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    // 创建与课程相关的文件
                    File gradeFile = new File(System.getProperty("user.dir") + "/data/grade/" + course.getCourseName() + ".txt");
                    File studentFile = new File(System.getProperty("user.dir") + "/data/course_student/" + course.getCourseName()
                            + "_student.txt");
                    try {
                        Files.write(gradeFile.toPath(), (course.getCourseId() + " " + course.getCourseName() + " " + course.getTeacherId() + " " + course.getTeacherName()
                                + " " + course.getTeacherId() + " " + course.getTeacherName() + " -1\n").getBytes());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        studentFile.createNewFile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    // 提示添加成功
                    JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    @Override
    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) { // 如果是关闭窗口事件
            this.dispose(); // 销毁窗口
            setVisible(false); // 设置窗口不可见
        }
    }
}