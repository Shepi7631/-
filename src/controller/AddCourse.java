package controller;

// �����Ҫ����Ͱ�
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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Course;

// SuppressWarnings�������ơ�serial�����棨JFrame��ʵ����Serializable�ӿڣ�
@SuppressWarnings("serial")
public class AddCourse extends JFrame implements ActionListener {
  /*
   * ��ʦ���ӿγ̹���
   */
  /*
�ܽ�
����������
�ô���ʵ���˽�ʦ���ӿγ̵Ĺ��ܣ�ͨ��ͼ�ν�������γ���Ϣ��������Ϣ���浽�ļ��С�
����У�飺
����������Ϊ�ա�
���γ��Ƿ��Ѵ��ڡ�
�ļ�������
��ȡ��д��γ���Ϣ���ļ���
Ϊ�����γ̴�����صĳɼ��ļ���ѧ���ļ���
���潻����
ʹ��JOptionPane��ʾ�û����������
ʹ��JTextField��ȡ�û����롣
*/


  // ����������
  JPanel contain;
  JButton submit;
  JLabel id, name, gredit, classH, teacherId, teacherName;
  JTextField idt, namet, greditt, classHt, teacherIdt, teacherNamet;

  // ���췽������ʼ�����ں����
  public AddCourse() {
    super("���ӿγ�"); // ���ô��ڱ���
    setSize(400, 400); // ���ô��ڴ�С
    setLocation(600, 400); // ���ô���λ��
    contain = new JPanel(); // ��ʼ�����
    contain.setLayout(null); // ���ò���Ϊ�ղ���

    // ��ʼ����ǩ
    id = new JLabel("�γ̺�");
    name = new JLabel("�γ���");
    gredit = new JLabel("ѧ��");
    classH = new JLabel("ѧʱ");
    teacherId = new JLabel("��ʦ");
    teacherName = new JLabel("��ʦ��");

    // ��ʼ����ť���ı���
    submit = new JButton("�ύ");
    idt = new JTextField();
    namet = new JTextField();
    greditt = new JTextField();
    classHt = new JTextField();
    teacherIdt = new JTextField();
    teacherNamet = new JTextField();

    // �������λ��
    id.setBounds(42, 35, 75, 35);
    idt.setBounds(80, 35, 150, 35);
    name.setBounds(40, 90, 75, 35);
    namet.setBounds(80, 90, 150, 35);
    gredit.setBounds(45, 145, 75, 35);
    greditt.setBounds(80, 145, 150, 35);
    classH.setBounds(45, 200, 75, 35);
    classHt.setBounds(80, 200, 150, 35);
    teacherId.setBounds(45, 245, 75, 35);
    teacherIdt.setBounds(85, 245, 150, 35);
    teacherName.setBounds(45, 280, 75, 35);
    teacherNamet.setBounds(80, 280, 75, 35);
    submit.setBounds(102, 320, 70, 30);

    // �����������
    contain.add(id);
    contain.add(idt);
    contain.add(name);
    contain.add(namet);
    contain.add(gredit);
    contain.add(greditt);
    contain.add(classH);
    contain.add(classHt);
    contain.add(teacherId);
    contain.add(teacherIdt);
    contain.add(teacherName);
    contain.add(teacherNamet);
    contain.add(submit);

    // ��Ӱ�ť������
    submit.addActionListener(this);
    add(contain); // �������ӵ�����
    setVisible(true); // ���ô��ڿɼ�
    enableEvents(AWTEvent.WINDOW_EVENT_MASK); // ���ô����¼�����
  }

  // ���γ��Ƿ��Ѿ�����
  public int hasCourse(String id) {
    String file = System.getProperty("user.dir") + "/data/course.txt"; // ��ȡ�γ��ļ�·��
    try {
      BufferedReader br = new BufferedReader(new FileReader(file)); // ����BufferedReader��ȡ�ļ�
      String s = null;
      while ((s = br.readLine()) != null) { // ���ж�ȡ�ļ�
        String[] result = s.split(" "); // ���ո�ָ��ֶ�
        if (result[0].equals(id)) { // ���γ̺��Ƿ�ƥ��
          br.close();
          return 1; // ����γ��Ѵ��ڣ�����1
        }
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace(); // �쳣����
    }
    return 0; // ����γ̲����ڣ�����0
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submit) { // ���������ύ��ť
      // ���������Ƿ�Ϊ��
      if (idt.getText().equals("") || namet.getText().equals("") || greditt.getText().equals("")
              || classHt.getText().equals("") || teacherIdt.getText().equals("") || teacherNamet.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
      } else {
        // ���γ��Ƿ����
        if (hasCourse(idt.getText()) == 1) {
          JOptionPane.showMessageDialog(null, "�˿γ��Ѿ����ڣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
        } else {
          String file = System.getProperty("user.dir") + "/data/course.txt"; // ��ȡ�γ��ļ�·��
          ArrayList<String> modifiedContent = new ArrayList<>(); // �洢�ļ��ĸ�������
          try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) { // ��ȡ�����ļ�����
              modifiedContent.add(s); // ��ӵ������б�
            }
            br.close();
          } catch (Exception e1) {
            e1.printStackTrace();
          }

          // �����µĿγ̶���
          Course course = new Course(idt.getText(), namet.getText(), greditt.getText(), classHt.getText(),
                  teacherIdt.getText(), teacherNamet.getText());

          // ����¿γ̵���Ϣ
          modifiedContent.add(course.getCourseId() + " " + course.getCourseName() + " " + course.getCredit() + " "
                  + course.getHour() + " " + course.getTeacherId() + " " + course.getTeacherName());

          // �����º������д���ļ�
          try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String element : modifiedContent) {
              bw.write(element);
              bw.newLine();
            }
            bw.close();
            fw.close();
          } catch (IOException e1) {
            e1.printStackTrace();
          }

          // ������γ���ص��ļ�
          File gradeFile = new File(System.getProperty("user.dir") + "/data/grade" + course.getCourseName() + ".txt");
          File studentFile = new File(System.getProperty("user.dir") + "/data/course_student" + course.getCourseName()
                  + "_student.txt");

          // ��ʾ��ӳɹ�
          JOptionPane.showMessageDialog(null, "��ӳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    }
  }

  @Override
  public void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) { // ����ǹرմ����¼�
      this.dispose(); // ���ٴ���
      setVisible(false); // ���ô��ڲ��ɼ�
    }
  }
}