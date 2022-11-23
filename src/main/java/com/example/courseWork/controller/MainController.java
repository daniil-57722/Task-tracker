package com.example.courseWork.controller;

import com.example.courseWork.entity.Board;
import com.example.courseWork.entity.Message;
import com.example.courseWork.entity.User;
import com.example.courseWork.repository.BoardRepository;
import com.example.courseWork.repository.CardRepository;
import com.example.courseWork.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CardRepository cardRepository;


    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/greeting")
    public String greet(){
        return "greeting";
    }



    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model, @AuthenticationPrincipal User user) {
        Iterable<Board> boards = boardRepository.findAllByUser(user);
        model.addAttribute("boards", boards);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/add_board")
    public String addBoard(@AuthenticationPrincipal User user, @RequestParam String text, Model model){
        System.out.println(user.getUsername());
        Board board = new Board(text, user);
        boardRepository.save(board);
        List<Board> boards = boardRepository.findAllByUser(user);
        System.out.println(boards.stream().count());
        model.addAttribute("boards", boardRepository.findAllByUser(user));
        return "main";
    }
}