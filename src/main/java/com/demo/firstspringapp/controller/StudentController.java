package com.demo.firstspringapp.controller;

import com.demo.firstspringapp.model.AddressDTO;
import com.demo.firstspringapp.model.SearchModel;
import com.demo.firstspringapp.model.StudentDTO;
import com.demo.firstspringapp.model.UserDTO;
import com.demo.firstspringapp.repository.AddressRepository;
import com.demo.firstspringapp.service.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class.getName());

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    AddressService addressService;

    @Autowired
    AddressClient addressClient;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String showStudent(Model model)
    {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.demo.firstspringapp.model");
        context.refresh();

        StudentDTO studentDTO =  context.getBean(StudentDTO.class);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreetName(addressClient.printAddressOfBean());
        studentDTO.setAddress(addressDTO);
        studentDTO.setFirstName("Igor");
        studentDTO.setLastName("Adulyan");
        studentDTO.setEmail("test@test.com");

        model.addAttribute("student", studentDTO);

        logger.info("Student info is here " + studentDTO.getEmail());

        return "index";

    }


    @GetMapping("/form")
    public String studentForm(Model model)
    {
        model.addAttribute("student", new StudentDTO());
        model.addAttribute("address", new AddressDTO());
        model.addAttribute("user", new UserDTO());

        return "student-sign-up";
    }

    @PostMapping("/process-student")
    public String processForm(@Valid @ModelAttribute ("student") StudentDTO studentDTO,
                              BindingResult bindingResult,
                              @ModelAttribute ("address") AddressDTO addressDTO,
                              BindingResult bindingResultAddress,
                              @Valid @ModelAttribute ("user") UserDTO userDTO, BindingResult
                                userBindingResult,
                                  Model model){

        if(bindingResult.hasErrors())
        {
            logger.warn("User wrong input " +
                    Arrays.toString(bindingResult.getSuppressedFields()));
            return "student-sign-up";
        }

        if(userBindingResult.hasErrors())
        {
            return "student-sign-up";
        }

        try {

            studentService.saveStudent(studentDTO, addressDTO);
            userDTO.setEmail(studentDTO.getEmail());
            userService.saveUser(userDTO);
        }catch (Exception e)
        {
            model.addAttribute("message", "User already exist");
            return "student-sign-up";
        }

        return "confirmation";
    }


    @GetMapping("/list-of-students")
    public String listOfStudents(Model model){

        model.addAttribute("students", studentService.getAllStudents());

        return "all-students";
    }


    @GetMapping("/find-student-by-email")
    public String findStudentByEmail(@RequestParam ("email") String email, Model model)
    {
        model.addAttribute("student",studentService.findStudentByEmail(email));

        return "student-info";
    }

    @GetMapping("/find-student-by-email/{email}")
    public String findStudentByEmailUsingPathVariable(@PathVariable("email") String email, Model model)
    {
        model.addAttribute("student",studentService.findStudentByEmail(email));

        return "student-info";
    }

    @GetMapping("/search")
    public String findStudentByLastName(Model model)
    {
        model.addAttribute("name", new SearchModel());

        return "student-search";
    }

    @PostMapping("/process-search")
    public String processSearch(@ModelAttribute("name") SearchModel searchModel, Model model)
    {
       List<StudentDTO> studentDTOList =  studentService.searchStudentsByLastName(searchModel.getName());

       model.addAttribute("students", studentDTOList);

       return "search-result";
    }

    @GetMapping("/account")
    public String userAccount()
    {
        return "account";
    }


}
