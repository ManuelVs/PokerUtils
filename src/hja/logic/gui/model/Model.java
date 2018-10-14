package hja.logic.gui.model;

import java.io.IOException;

public interface Model {
	
	void changeFile(String path) throws IOException;
	
	void selectBoard(int index);
}
