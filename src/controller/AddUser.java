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

// SuppressWarnings�������ơ�serial�����棨JFrame��ʵ����Serializable�ӿڣ�
@SuppressWarnings("serial")
public class AddUser extends JFrame implements ActionListener {
  /*
   * �������Ա����û����ܣ��������ѧ������ʦ������Ա
   */

  // ����������
  JPanel contain;
  JLabel id, name, birthday, institute, major;
  JTextField idt, namet, birthdayt, institutet, majort;
  Checkbox check1, check2; // ����ѡ���Ա�
  CheckboxGroup group; // �����Ա�ѡ��
  JButton submit;
  Choice chooice; // ����ѡ���û����ͣ�ѧ������ʦ������Ա��
  String file = System.getProperty("user.dir") + "/data/"; // ���ݴ洢·��

  // ���췽������ʼ�����ں����
  public AddUser() {
    super("����û�"); // ���ô��ڱ���
    setSize(300, 350); // ���ô��ڴ�С
    setLocation(600, 400); // ���ô���λ��
    contain = new JPanel();
    contain.setLayout(null);

    // ��ʼ�����
    id = new JLabel("�ʺ�");
    name = new JLabel("����");
    group = new CheckboxGroup();
    check1 = new Checkbox("��", group, true);
    check2 = new Checkbox("Ů", group, false);
    birthday = new JLabel("����");
    institute = new JLabel("ѧԺ");
    major = new JLabel("רҵ");
    submit = new JButton("�ύ");
    chooice = new Choice(); // �����б�
    chooice.addItem("ѧ��");
    chooice.addItem("��ʦ");
    chooice.addItem("����Ա");

    // ��ʼ�������
    idt = new JTextField();
    namet = new JTextField();
    birthdayt = new JTextField();
    institutet = new JTextField();
    majort = new JTextField();

    // �������λ��
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

    // �����������
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

    // ��Ӱ�ť������
    submit.addActionListener(this);
    add(contain); // �������ӵ�����
    setVisible(true); // ���ô��ڿɼ�
    enableEvents(AWTEvent.WINDOW_EVENT_MASK); // ���ô����¼�����
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submit) { // �ύ��ť����¼�
      // ���������Ƿ�Ϊ��
      if (idt.getText().equals("") || namet.getText().equals("") || birthdayt.getText().equals("")
              || institutet.getText().equals("") || majort.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
      } else {
        String ch = chooice.getSelectedItem(); // ��ȡѡ����û�����
        if (ch.equals("ѧ��")) { // ѧ���û�
          handleUser("student", "ѧ��");
        } else if (ch.equals("��ʦ")) { // ��ʦ�û�
          handleUser("teacher", "��ʦ");
        } else { // ����Ա�û�
          handleUser("administrator", "����Ա");
        }
      }
    }
  }

  // ͨ�÷�������������û��߼�
  private void handleUser(String userType, String userName) {
    if (new CheckInfo().isMember(userType, idt.getText(), namet.getText()) == 2) {
      // ����û��Ƿ��Ѵ���
      JOptionPane.showMessageDialog(null, "��" + userName + "�Ѿ����ڣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
    } else {
      file = file.concat(userType + ".txt"); // ȷ����Ӧ���ļ�·��
      ArrayList<String> modifiedContent = new ArrayList<>();

      // ��ȡ�ļ�����
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String s;
        while ((s = br.readLine()) != null) {
          modifiedContent.add(s);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      // ��ȡ�Ա�
      String m = check1.getState() ? "male" : "female";

      // ����û���Ϣ
      String user = idt.getText() + " " + "123456" + " " + namet.getText() + " " + m + " " + birthdayt.getText() + " "
              + institutet.getText() + " " + majort.getText();
      modifiedContent.add(user);

      // д���ļ�
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
        for (String element : modifiedContent) {
          bw.write(element);
          bw.newLine();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      // ��ʾ��ӳɹ�
      JOptionPane.showMessageDialog(null, "�ɹ����һ��" + userName + "��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  @Override
  public void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) { // �رմ����¼�
      this.dispose(); // ���ٴ���
      setVisible(false); // ���ô��ڲ��ɼ�
    }
  }
}
