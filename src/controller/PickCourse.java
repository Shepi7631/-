package controller;

// 导入必要的类和包

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@SuppressWarnings("serial")
public class PickCourse extends JFrame implements ActionListener {
    // 定义界面组件
    JPanel contain;
    JButton submit;
    JLabel courseId, stuId;
    JTextField courseIdT, stuIdt;

    public PickCourse() {
        super("选课");
        setSize(400, 400); // 设置窗口大小
        setLocation(600, 400); // 设置窗口位置
        contain = new JPanel(); // 初始化面板
        contain.setLayout(null); // 设置布局为空布局

        // 初始化标签
        courseId = new JLabel("课程号");
        stuId = new JLabel("学号");

        // 初始化按钮和文本框
        submit = new JButton("提交");
        courseIdT = new JTextField();
        stuIdt = new JTextField();

        // 设置组件位置
        courseId.setBounds(42, 35, 75, 35);
        stuId.setBounds(42, 70, 75, 35);
        courseIdT.setBounds(80, 35, 150, 35);
        stuIdt.setBounds(80, 70, 150, 35);
        submit.setBounds(102, 320, 70, 30);

        // 添加组件到面板
        contain.add(courseId);
        contain.add(stuId);
        contain.add(courseIdT);
        contain.add(stuIdt);
        contain.add(submit);

        // 添加按钮监听器
        submit.addActionListener(this);
        add(contain); // 将面板添加到窗口
        setVisible(true); // 设置窗口可见
        enableEvents(AWTEvent.WINDOW_EVENT_MASK); // 启用窗口事件处理
    }

    // 检查课程是否已经存在
    public String hasCourse(String id) {
        String file = System.getProperty("user.dir") + "/data/course.txt"; // 获取课程文件路径
        try {
            BufferedReader br = new BufferedReader(new FileReader(file)); // 创建BufferedReader读取文件
            String s = null;
            while ((s = br.readLine()) != null) { // 按行读取文件
                String[] result = s.split(" "); // 按空格分割字段
                if (result[0].equals(id)) { // 检查课程号是否匹配
                    br.close();
                    return result[1]; // 如果课程已存在，返回1
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace(); // 异常处理
        }
        return ""; // 如果课程不存在，返回0
    }

    //检查学生是否以及选课
    int hasThisStu() {

        @SuppressWarnings("unused")
        String stuId = stuIdt.getText();

        String path = System.getProperty("user.dir") + "/data/course_student";
        // String path = "D://test//course_student";

        List<String> files = new ArrayList<>(); // 目录下所有文件
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (File element : tempList) {
            if (element.isFile()) {
                files.add(element.toString());
                // 文件名，不包含路径
                // String fileName = tempList[i].getName();
            }
            if (element.isDirectory()) {
                // 这里就不递归了，
            }
        }

        try {
            for (String file2 : files) {
                BufferedReader br = new BufferedReader(new FileReader(file2));
                String s = null;
                while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                    String[] result = s.split(" ");
                    if (result[0].equals(courseIdT.getText()) && result[2].equals(stuIdt.getText())) {
                        br.close();
                        return 1;
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    String getStuInfo() throws IOException {
        String path = System.getProperty("user.dir") + "/data/student.txt";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
            String[] result = s.split(" ");
            if (result[0].equals(stuIdt.getText())) {
                br.close();
                return s;
            }
        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) { // 如果点击了提交按钮
            // 检查输入框是否为空
            if (courseIdT.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String courseName = hasCourse(courseIdT.getText());
                String stuInfo = null;
                try {
                    stuInfo = getStuInfo();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (stuInfo == "") {
                    JOptionPane.showMessageDialog(null, "不存在该学生！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else if (courseName == "") {
                    JOptionPane.showMessageDialog(null, "不存在这门课！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else if (hasThisStu() == 1) {
                    JOptionPane.showMessageDialog(null, "学生已选该门课！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String path = System.getProperty("user.dir") + "/data/course_student/" + courseName + "_student.txt"; // 获取课程文件路径
                    String[] result = stuInfo.split(" ");
                    String content = courseIdT.getText() + " " + courseName + " " + stuIdt.getText() + " " + result[2] + " "
                            + result[3] + " " + result[4] + " " + result[5] + " " + result[6] + "\n";
                    File file=new File(path);
                    try {
                        Files.write(file.toPath(),content.getBytes(),StandardOpenOption.APPEND);
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
