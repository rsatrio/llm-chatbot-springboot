package com.rizky.chat.components;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class PromptComponent {

	@Value("${llamacpp.model}")
	private String modelPath;

	@Value("${llamacpp.prompt.path}")
	private String promptPath;


	private String promptContent;
	
	final Logger log1=LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void init()	{
		try	{
			if(Files.exists(Paths.get(promptPath),LinkOption.NOFOLLOW_LINKS))	{
				List<String> stringPrompt=Files.readAllLines(Path.of(promptPath));
				promptContent=stringPrompt.stream().collect(Collectors.joining(System.lineSeparator()));
			}
			else	{
				promptContent=getDefaultPrompt();
			}
		}
		catch(Exception e)	{
			log1.error("Error:",e);
		}

	}
	
	public String getPromptContent() {
		return promptContent;
	}

	private String getDefaultPrompt()	{
		String promptDefault="<|system|>\r\n"
				+ "You are Chatty, a friendly assistant chatbot who always responds to user questions and inquiry.</s>\r\n"
				+ "<|user|>\r\n"
				+ "{question} </s>\r\n"
				+ "<|assistant|>";

		return promptDefault;
	}

}
