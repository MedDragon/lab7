/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.mavenproject2.Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sumdu.edu.ua.mavenproject2.Content;
import sumdu.edu.ua.mavenproject2.Student;

/**
 *
 * @author artem
 */

@WebServlet(name = "ControllContent", urlPatterns = {"/ControllContent"})
public class ControllContent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter pw=null;
            try{ 
                    pw =response.getWriter(); 
                    Class.forName("com.mysql.jdbc.Driver"); 
                } 
                catch(ClassNotFoundException ex){ 
                    ex.printStackTrace(pw); 
                    pw.print(ex.getMessage()); 
             } 
            int studentId = Integer.parseInt(request.getParameter("id"));
            List<Student> students = getStudentFromDatabase(studentId);
            List<Content> marks = getstudentMarkFromDatabase(studentId);
            request.setAttribute("students", students);
            request.setAttribute("marks", marks);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/mark.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ControllContent.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    private List<Content> getstudentMarkFromDatabase(int studentId) throws SQLException, IOException {
        List<Content> marks = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3311/university?useUnicode=true&characterEncoding=UTF-8","root","root")) {
            
            String query = "SELECT * FROM `disciplines` WHERE student_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) { 

                        Content mark = new Content(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5)); 
                        marks.add(mark);

                    } 
        }
        return marks;
    }
    
    private List<Student> getStudentFromDatabase(int studentId) throws SQLException, IOException {
        List<Student> students = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3311/university?useUnicode=true&characterEncoding=UTF-8","root","root")) {
            
            String query = "SELECT * FROM `student` WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) { 

                        Student student = new Student(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7)); 
                        students.add(student);
                    } 
        }
        return students;
    }
}