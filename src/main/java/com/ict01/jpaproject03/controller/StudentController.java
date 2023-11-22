package com.ict01.jpaproject03.controller;

import com.ict01.jpaproject03.dao.StudentDAO;
import com.ict01.jpaproject03.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

//    // Constructor Dependency Injection
//    // [1] Spring에서 제공되는 타입
//    private final StudentDAO studentDAO;
//
//    public StudentController(StudentDAO studentDAO) {
//        this.studentDAO = studentDAO;
//    }

    // [2] Lombok에서 제공되는 타입
    private final StudentDAO studentDAO;


    // [1] 전체 학생정보 목록 출력
    @GetMapping("/lists")
    public String listStudents(Model model){
        List<StudentDTO> students = studentDAO.listStudents();
        model.addAttribute("students",students);

        return "listStudents";
    }


    // [2] 학생정보 등록
    // [2-1] 학생정보 등록 Form
    @GetMapping("showForm")
    public String showFormAdd(Model model){
        StudentDTO student = new StudentDTO();
        model.addAttribute("student",student);
        return "studentForm";
    }

    // [2-2] 힉생정보 Action
    @PostMapping("saveStudent")
    @ResponseBody
    public String saveStudent(@RequestParam("name") String name,
                              @RequestParam("email") String email,
                              @RequestParam("address") String address
                              ) {
        int result = studentDAO.saveStudent(name, email, address);
        if(result == 1){
            System.out.println("학생등록 성공!!!");
            return "<script>alert('학생등록 성공!!!'); location.href='/student/lists';</script>";
        } else{
            System.out.println("학생등록 실패...");
            return "<script>alert('학생등록 실패...'); location.href='/student/lists';</script>";
        }
    }

    // [4] 학생정보 수정
    // (4-1) 학생정보 수정 Form
    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("studentId") int id, Model model){
        StudentDTO studentDTO = studentDAO.getStudent(id);
        model.addAttribute("student",studentDTO);

        return "updateForm";
    }

    // (4-2) 학생정보 Action
    @PostMapping("updateStudent")
    @ResponseBody
    public String updateStudent(@RequestParam("id") int id,
                                @RequestParam("name") String name,
                                @RequestParam("email") String email,
                                @RequestParam("address") String address
                                ) {
        int result = studentDAO.updateStudent(id, name, email, address);
        if(result == 1){
            System.out.println("학생정보 수정 성공!!!");
            return "<script>alert('학생정보 수정 성공!!!'); location.href='/student/lists'; </script>";
        } else {
            System.out.println("학생정보 수정 실패...");
            return "<script>alert('학생정보 수정 실패...'); location.href='/student/lists'; </script>";
        }

    }


    // [5] 학생정보 삭제
    @GetMapping("/delete")
    @ResponseBody
    public String deleteStudent(@RequestParam("studentId") String id){
        int result = studentDAO.deleteStudent(id);
        if(result == 1){
            System.out.println("학생정보 삭제 성공!!!");
            return "<script>alert('학생정보 삭제 성공!!!'); location.href='/student/lists'; </script>";
        }else{
            System.out.println("학생정보 삭제 실패...");
            return "<script>alert('학생정보 삭제 실패...'); location.href='/student/lists'; </script>";
        }
    }

}
