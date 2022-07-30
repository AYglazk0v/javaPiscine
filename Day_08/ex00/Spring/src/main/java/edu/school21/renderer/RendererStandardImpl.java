package edu.school21.renderer;
import edu.school21.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {
	PreProcessor processor;
	
	public RendererStandardImpl(PreProcessor processor) {
		this.processor = processor;
	}

	@Override
	public void print(String textToPrint) {
		System.out.println(this.processor.process(textToPrint));
	}
}
