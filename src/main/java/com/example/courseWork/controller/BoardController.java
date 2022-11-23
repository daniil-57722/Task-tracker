package com.example.courseWork.controller;

import com.example.courseWork.entity.*;
import com.example.courseWork.repository.CardRepository;
import com.example.courseWork.repository.MessageRepository;
import com.example.courseWork.repository.TaskRepository;
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
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Controller
public class BoardController {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    MessageRepository messageRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/tasks/save")
    public String saveTask(@RequestParam Task task, @RequestParam Boolean status){
        System.out.println("save");
        Board board = task.getCard().getBoard();
        task.setStatus(status);
        return "redirect:/board/"+board.getIdBoard();
    }

    @PostMapping("/card/add")
    public String add(@RequestParam Board board,
                      @RequestParam String text,
                      @RequestParam("file") MultipartFile file,
                      Model model) throws IOException {

        Card card = new Card(text, board);
        if(file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            card.setFilename(resultFilename);
        }
        cardRepository.save(card);
        model.addAttribute("board", board.getIdBoard());
        model.addAttribute("cards", cardRepository.findAllByBoard(board));
        return "board";
    }

    @GetMapping("/board/{board}")
    public String openBoard(@PathVariable Board board, Model model){
        Iterable<Card> cards = cardRepository.findAllByBoard(board);
        model.addAttribute("cards", cards);
        model.addAttribute("board", board.getIdBoard());
        model.addAttribute("messages", board.getMessages());
        return "board";
    }

    @GetMapping("/board/{board}/card/{card}")
    public String openCard(@PathVariable Card card, @PathVariable Board board, Model model){
        Iterable<Card> cards = cardRepository.findAllByBoard(board);
        model.addAttribute("cards", cards);
        model.addAttribute("board", board.getIdBoard());
        model.addAttribute("cardName", card.getCardName());
        model.addAttribute("cardImage", card.getFilename());
        model.addAttribute("cardBody", card.getTasks());
        model.addAttribute("cardId", card.getIdCard());
        model.addAttribute("messages", board.getMessages());
        return "board";
    }

    @PostMapping("/task/add")
    public String addTask(@RequestParam Card card, @RequestParam Board board, @RequestParam String text){
        Task task = new Task(text,card,false);
        taskRepository.save(task);
        return "redirect:/board/"+board.getIdBoard()+"/card/"+card.getIdCard();
    }

    @GetMapping("/task/{task}/delete")
    public String deleteTask(@PathVariable Task task){
        System.out.println("delete");
        Card card = task.getCard();
        Board board = card.getBoard();
        taskRepository.delete(task);
        return "redirect:/board/"+board.getIdBoard()+"/card/"+card.getIdCard();
    }

    @GetMapping("/task/{task}/changeStatus")
    public String changeTask(@PathVariable Task task){
        System.out.println("change");
        Card card = task.getCard();
        Board board = card.getBoard();
        boolean status = task.getStatus();
        task.setStatus(!status);
        taskRepository.save(task);
        return "redirect:/board/"+board.getIdBoard()+"/card/"+card.getIdCard();
    }

    @PostMapping("/task/user")
    public String setTaskIssuer(@AuthenticationPrincipal User user, @RequestParam Task task){
        Card card = task.getCard();
        Board board = card.getBoard();
        if(task.getIssuer()==null){
            task.setIssuer(user);
        } else if(Objects.equals(user.getId(), task.getIssuer().getId())){
            task.setIssuer(null);
        }
        taskRepository.save(task);
        return "redirect:/board/"+board.getIdBoard()+"/card/"+card.getIdCard();
    }
    @PostMapping("/message/add")
    public String addMessage(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam Board board){
        Message message = new Message(user, text, board);
        messageRepository.save(message);
        return "redirect:/board/"+board.getIdBoard();

    }

}
