package com.BookStore.BookStore.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.BookStore.BookStore.Entity.Book;
import com.BookStore.BookStore.Entity.MyBookList;
import com.BookStore.BookStore.service.BookService;
import com.BookStore.BookStore.service.MyBookListService;

import ch.qos.logback.core.model.Model;
import jakarta.annotation.PostConstruct;

@Controller
public class BookController {

	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;
	
	@GetMapping("/ok")
	public String home() {
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookregister() {
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook(){
		List<Book>list=service.getAllBook();
//		ModelAndView m=new ModelAndView();
//   	m.setViewName("bookList");
//		m.addObject("book",list);
		return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		service.save(b);
		return "redirect:/available_books";
	}
	
	@GetMapping("/my_books")
	
	public ModelAndView getMyBooks() {
		List<MyBookList>list=myBookService.getAllMyBooks();
		return new ModelAndView("myBooks","book",list);
				
		
		
	}
	
  
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		
		return "redirect:/my_books";
	}
	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		myBookService.deleteById(id);
		return "redirect:/my_books";
	}
	
	@RequestMapping("/editbook/{id}")
	public ModelAndView editBook(@PathVariable("id") int id,Model model) {
		
		Book b=service.getBookById(id);
		
		return new ModelAndView("bookEdit","book",b);
	}
	
}
