package com.multicampus.todo;

import com.multicampus.todo.dto.PageRequestDto;
import com.multicampus.todo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MyController {
    @Autowired
    private ITodoService service;

    private final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @RequestMapping("/")
    public String root() throws Exception {
        return "redirect:todo/list";
    }

//    @RequestMapping("/todo/list")
//    public String todoList(Model model) {
//        model.addAttribute("dto", service.list());
//        return "todo/list";
//    }

    @RequestMapping("/todo/read")
    public String todoRead(HttpServletRequest request, Model model) {
        Long tno = Long.parseLong(request.getParameter("tno"));
        model.addAttribute("dto", service.view(tno));
        return "todo/read";
    }

    @RequestMapping(value ="/todo/register", method = RequestMethod.GET)
    public String todoRegisterGet(HttpServletRequest request) {
        return "todo/register";
    }

    @RequestMapping(value ="/todo/register", method = RequestMethod.POST)
    public String todoRegisterPost(HttpServletRequest request) {
        String title = request.getParameter("title");
        LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"), DATETIMEFORMATTER);
        String writer = request.getParameter("writer");

        int nResult = service.insert(title, dueDate, writer);
        System.out.println("Write " + nResult);
        return "redirect:list";
    }

    @GetMapping("/todo/modify")
    public String todoModifyGet(HttpServletRequest request, Model model) {
        Long tno = Long.parseLong(request.getParameter("tno"));
        model.addAttribute("dto", service.view(tno));
        //검색/필터링/페이징 조건은 초기화
        return "todo/modify";
    }

    @PostMapping("/todo/modify")
    public String todoModifyPost(HttpServletRequest request, Model model) {
        Long tno = Long.parseLong(request.getParameter("tno"));
        String title = request.getParameter("title");
        LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"), DATETIMEFORMATTER);

        String finishedStr = request.getParameter("finished");
        Boolean finished = false;
        if(finishedStr != null && finishedStr.equals("on")) finished = true;

        model.addAttribute("dto", service.update(tno, title, dueDate, finished));
        //검색/필터링/페이징 조건은 초기화
        return "redirect:list";
    }

    @RequestMapping("/todo/remove")
    public String todoDelete(HttpServletRequest request, Model model) {
        Long tno = Long.parseLong(request.getParameter("tno"));
        int nResult = service.delete(tno);
        System.out.println("Delete: " + nResult);
        return "redirect:list";
    }

    @RequestMapping("/todo/list")
    public void list(@Valid PageRequestDto pageRequestDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            pageRequestDto = PageRequestDto.builder().build();
        }
        model.addAttribute("responseDto", service.getList(pageRequestDto));
    }
}
