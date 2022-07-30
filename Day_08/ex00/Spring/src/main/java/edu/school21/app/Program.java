package edu.school21.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.school21.printer.Printer;

public class Program {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		Printer printer = context.getBean("printerWithPrefixAndUpperStandardRenderer", Printer.class);
		printer.print("Hello!");
		((ClassPathXmlApplicationContext) context).close();
	}	       
} 