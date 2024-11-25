package controller;

// �����Ҫ����Ͱ�

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
    // ����������
    JPanel contain;
    JButton submit;
    JLabel courseId, stuId;
    JTextField courseIdT, stuIdt;

    public PickCourse() {
        super("ѡ��");
        setSize(400, 400); // ���ô��ڴ�С
        setLocation(600, 400); // ���ô���λ��
        contain = new JPanel(); // ��ʼ�����
        contain.setLayout(null); // ���ò���Ϊ�ղ���

        // ��ʼ����ǩ
        courseId = new JLabel("�γ̺�");
        stuId = new JLabel("ѧ��");

        // ��ʼ����ť���ı���
        submit = new JButton("�ύ");
        courseIdT = new JTextField();
        stuIdt = new JTextField();

        // �������λ��
        courseId.setBounds(42, 35, 75, 35);
        stuId.setBounds(42, 70, 75, 35);
        courseIdT.setBounds(80, 35, 150, 35);
        stuIdt.setBounds(80, 70, 150, 35);
        submit.setBounds(102, 320, 70, 30);

        // �����������
        contain.add(courseId);
        contain.add(stuId);
        contain.add(courseIdT);
        contain.add(stuIdt);
        contain.add(submit);

        // ��Ӱ�ť������
        submit.addActionListener(this);
        add(contain); // �������ӵ�����
        setVisible(true); // ���ô��ڿɼ�
        enableEvents(AWTEvent.WINDOW_EVENT_MASK); // ���ô����¼�����
    }

    // ���γ��Ƿ��Ѿ�����
    public String hasCourse(String id) {
        String file = System.getProperty("user.dir") + "/data/course.txt"; // ��ȡ�γ��ļ�·��
        try {
            BufferedReader br = new BufferedReader(new FileReader(file)); // ����BufferedReader��ȡ�ļ�
            String s = null;
            while ((s = br.readLine()) != null) { // ���ж�ȡ�ļ�
                String[] result = s.split(" "); // ���ո�ָ��ֶ�
                if (result[0].equals(id)) { // ���γ̺��Ƿ�ƥ��
                    br.close();
                    return result[1]; // ����γ��Ѵ��ڣ�����1
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace(); // �쳣����
        }
        return ""; // ����γ̲����ڣ�����0
    }

    //���ѧ���Ƿ��Լ�ѡ��
    int hasThisStu() {

        @SuppressWarnings("unused")
        String stuId = stuIdt.getText();

        String path = System.getProperty("user.dir") + "/data/course_student";
        // String path = "D://test//course_student";

        List<String> files = new ArrayList<>(); // Ŀ¼�������ļ�
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (File element : tempList) {
            if (element.isFile()) {
                files.add(element.toString());
                // �ļ�����������·��
                // String fileName = tempList[i].getName();
            }
            if (element.isDirectory()) {
                // ����Ͳ��ݹ��ˣ�
            }
        }

        try {
            for (String file2 : files) {
                BufferedReader br = new BufferedReader(new FileReader(file2));
                String s = null;
                while ((s = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
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
        while ((s = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
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
        if (e.getSource() == submit) { // ���������ύ��ť
            // ���������Ƿ�Ϊ��
            if (courseIdT.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String courseName = hasCourse(courseIdT.getText());
                String stuInfo = null;
                try {
                    stuInfo = getStuInfo();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (stuInfo == "") {
                    JOptionPane.showMessageDialog(null, "�����ڸ�ѧ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                } else if (courseName == "") {
                    JOptionPane.showMessageDialog(null, "���������ſΣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                } else if (hasThisStu() == 1) {
                    JOptionPane.showMessageDialog(null, "ѧ����ѡ���ſΣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String path = System.getProperty("user.dir") + "/data/course_student/" + courseName + "_student.txt"; // ��ȡ�γ��ļ�·��
                    String[] result = stuInfo.split(" ");
                    String content = courseIdT.getText() + " " + courseName + " " + stuIdt.getText() + " " + result[2] + " "
                            + result[3] + " " + result[4] + " " + result[5] + " " + result[6] + "\n";
                    File file=new File(path);
                    try {
                        Files.write(file.toPath(),content.getBytes(),StandardOpenOption.APPEND);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

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
