package edu.school21.preprocessor;

public class PreProcessorToLowerImpl implements PreProcessor {
	public String process(String message) {
        return message.toLowerCase();
	}
}
