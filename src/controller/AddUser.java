package controller;

import java.awt.AWTEvent;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// SuppressWarnings用于抑制“serial”警告（JFrame类实现了Serializable接口）
@SuppressWarnings("serial")
public class AddUser extends JFrame implements ActionListener {
  /*
   * 教务管理员添加用户功能，可以添加学生、教师、教务员
   */

  // 定义界面组件
  JPanel contain;
  JLabel id, name, birthday, institute, major;
  JTextField idt, namet, birthdayt, institutet, majort;
  Checkbox check1, check2; // 用于选择性别
  CheckboxGroup group; // 分组性别选项
  JButton submit;
  Choice chooice; // 用于选择用户类型（学生、教师、教务员）
  String file = System.getProperty("user.dir") + "/data/"; // 数据存储路径

  // 构造方法：初始化窗口和组件
  public AddUser() {
    super("添加用户"); // 设置窗口标题
    setSize(300, 350); // 设置窗口大小
    setLocation(600, 400); // 设置窗口位置
    contain = new JPanel();
    contain.setLayout(null);

    // 初始化组件
    id = new JLabel("帐号");
    name = new JLabel("姓名");
    group = new CheckboxGroup();
    check1 = new Checkbox("男", group, true);
    check2 = new Checkbox("女", group, false);
    birthday = new JLabel("生日");
    institute = new JLabel("学院");
    major = new JLabel("专业");
    submit = new JButton("提交");
    chooice = new Choice(); // 下拉列表
    chooice.addItem("学生");
    chooice.addItem("教师");
    chooice.addItem("教务员");

    // 初始化输入框
    idt = new JTextField();
    namet = new JTextField();
    birthdayt = new JTextField();
    institutet = new JTextField();
    majort = new JTextField();

    // 设置组件位置
    id.setBounds(42, 45, 75, 35);
    idt.setBounds(80, 45, 150, 35);
    name.setBounds(42, 20, 75, 35);
    namet.setBounds(80, 20, 150, 35);
    check1.setBounds(80, 67, 80, 40);
    check2.setBounds(160, 67, 80, 40);
    birthday.setBounds(42, 100, 75, 35);
    birthdayt.setBounds(80, 100, 150, 35);
    institute.setBounds(40, 145, 75, 35);
    institutet.setBounds(80, 145, 150, 35);
    major.setBounds(40, 220, 75, 35);
    majort.setBounds(80, 220, 150, 35);
    chooice.setBounds(80, 180, 150, 35);
    submit.setBounds(102, 260, 70, 30);

    // 添加组件到面板
    contain.add(id);
    contain.add(idt);
    contain.add(name);
    contain.add(namet);
    contain.add(birthday);
    contain.add(birthdayt);
    contain.add(institute);
    contain.add(institutet);
    contain.add(major);
    contain.add(majort);
    contain.add(check1);
    contain.add(check2);
    contain.add(chooice);
    contain.add(submit);

    // 添加按钮监听器
    submit.addActionListener(this);
    add(contain); // 将面板添加到窗口
    setVisible(true); // 设置窗口可见
    enableEvents(AWTEvent.WINDOW_EVENT_MASK); // 启用窗口事件处理
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submit) { // 提交按钮点击事件
      // 检查输入框是否为空
      if (idt.getText().equals("") || namet.getText().equals("") || birthdayt.getText().equals("")
              || institutet.getText().equals("") || majort.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
      } else {
        String ch = chooice.getSelectedItem(); // 获取选择的用户类型
        if (ch.equals("学生")) { // 学生用户
          handleUser("student", "学生");
        } else if (ch.equals("教师")) { // 教师用户
          handleUser("teacher", "教师");
        } else { // 教务员用户
          handleUser("administrator", "教务员");
        }
      }
    }
  }

  // 通用方法：处理添加用户逻辑
  private void handleUser(String userType, String userName) {
    if (new CheckInfo().isMember(userType, idt.getText(), namet.getText()) == 2) {
      // 检查用户是否已存在
      JOptionPane.showMessageDialog(null, "此" + userName + "已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
    } else {
      file = file.concat(userType + ".txt"); // 确定对应的文件路径
      ArrayList<String> modifiedContent = new ArrayList<>();

      // 读取文件内容
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String s;
        while ((s = br.readLine()) != null) {
          modifiedContent.add(s);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      // 获取性别
      String m = check1.getState() ? "male" : "female";

      // 添加用户信息
      String user = idt.getText() + " " + "123456" + " " + namet.getText() + " " + m + " " + birthdayt.getText() + " "
              + institutet.getText() + " " + majort.getText();
      modifiedContent.add(user);

      // 写入文件
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
        for (String element : modifiedContent) {
          bw.write(element);
          bw.newLine();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      // 提示添加成功
      JOptionPane.showMessageDialog(null, "成功添加一个" + userName + "！", "提示", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  @Override
  public void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) { // 关闭窗口事件
      this.dispose(); // 销毁窗口
      setVisible(false); // 设置窗口不可见
    }
  }
}
