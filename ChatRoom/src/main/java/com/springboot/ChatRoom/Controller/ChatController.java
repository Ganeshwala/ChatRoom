package com.springboot.ChatRoom.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import com.springboot.ChatRoom.Model.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public ChatMessage send(ChatMessage message) throws Exception {
		return new ChatMessage(HtmlUtils.htmlEscape(message.getUsername()),HtmlUtils.htmlEscape(message.getContent()));
	}
	
	@MessageMapping("/leave")
	@SendTo("/topic/messages")
	public ChatMessage leave(ChatMessage message) throws Exception{
		return new ChatMessage(HtmlUtils.htmlEscape(message.getUsername()), HtmlUtils.htmlEscape(message.getUsername()+" has left the chat room"));
	}
	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/chat")
	public String chat(@RequestParam String username,Model model) {
		model.addAttribute("username", username);
		return "chat";
	}
}
