package edu.school21.renderer;
import edu.school21.preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer {
	PreProcessor processor;
	
	public RendererErrImpl(PreProcessor processor) {
		this.processor = processor;
	}

	@Override
	public void print(String textToPrint) {
		System.err.println(this.processor.process(textToPrint));
	}
}
