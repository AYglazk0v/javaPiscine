package edu.school21.preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor {
	public String process(String message) {
        return message.toUpperCase();
	}
}
