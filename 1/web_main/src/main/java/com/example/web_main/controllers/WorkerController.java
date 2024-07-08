package com.example.web_main.controllers;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.web_main.models.Worker;
import com.example.web_main.repo.WorkerRepository;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WorkerController {
    @Autowired
    private WorkerRepository workerRepository;
    @GetMapping("/workers")
    public String WorkerMain(@RequestParam (required = false) String search, Model model) {
        model.addAttribute("title", "Workers");
        if(search == null || search.isEmpty()) {
            Iterable<Worker> workers = workerRepository.findAll();
            model.addAttribute("workers", workers);
            return "worker";
        }
        try{
            Long id = Long.parseLong(search);
            Worker worker = workerRepository.findById(id).get();
            model.addAttribute("workers", worker);
        }catch(NumberFormatException e) {
            Iterable<Worker> worker = workerRepository.findByFirstName(search);
            model.addAttribute("workers", worker);
        } catch (NoSuchElementException e) {
        model.addAttribute("error", "Worker with the provided ID does not exist");
            return "error";
        }
        return "worker"; 
    }
     @GetMapping("/workers/add")
    public String WorkerAdd(Model model) {
        model.addAttribute("title", "Добавление сотрудника");
        return "worker-add"; 
    }
    @PostMapping("/workers/add")
    public String WorkerPostAdd(@RequestParam String FirstName, @RequestParam String SecondName, @RequestParam String ThirdName, @RequestParam String Old, @RequestParam String Rank, @RequestParam String Stonks, @RequestParam String Branch, Model model) {
        Worker worker = new Worker(FirstName, SecondName, ThirdName, Old, Rank, Stonks, Branch);
        workerRepository.save(worker);
        return "redirect:/workers";
    }
    @GetMapping("/worker/{id}")
    public String WorkerInfo(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Информация о сотруднике №"+id);
        if(!workerRepository.existsById(id)) {
            return "redirect:/workers";
        }
        Optional<Worker> worker = workerRepository.findById(id);
        ArrayList<Worker> res = new ArrayList<>();
        worker.ifPresent(res::add);
        model.addAttribute("worker", res);
        return "worker-info"; 
    }
    @GetMapping("/worker/edit/{id}")
    public String WorkerEdit(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Изменение информации о сотруднике №"+id);
        if(!workerRepository.existsById(id)) {
            return "redirect:/workers";
        }
        Optional<Worker> worker = workerRepository.findById(id);
        ArrayList<Worker> res = new ArrayList<>();
        worker.ifPresent(res::add);
        model.addAttribute("worker", res);
        return "worker-edit"; 
    }
    @PostMapping("/worker/edit/{id}")
    public String WorkerPostEdit(@PathVariable(value = "id") long id,@RequestParam String FirstName, @RequestParam String SecondName, @RequestParam String ThirdName, @RequestParam String Old, @RequestParam String Rank, @RequestParam String Stonks, @RequestParam String Branch, Model model) {
        Worker worker = workerRepository.findById(id).orElseThrow();
        worker.setFirstName(FirstName);
        worker.setSecondName(SecondName);
        worker.setThirdName(ThirdName);
        worker.setOld(Old);
        worker.setRank(Rank);
        worker.setStonks(Stonks);
        worker.setBranch(Branch);
        workerRepository.save(worker);
        return "redirect:/workers";
    }
    @PostMapping("/worker/delete/{id}")
    public String WorkerPostDelete(@PathVariable(value = "id") long id, Model model) {
        Worker worker = workerRepository.findById(id).orElseThrow();
        workerRepository.delete(worker);
        return "redirect:/workers";
    }
    @GetMapping("/workers/search")
    @ResponseBody
    public Iterable<Worker> searchWorkers(@RequestParam("search") String search) {
        return workerRepository.findByFirstNameContainingIgnoreCase(search);
    }
}