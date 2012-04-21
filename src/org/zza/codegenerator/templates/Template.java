package org.zza.codegenerator.templates;

public interface Template {
	
	/**
	 * 
	 * @return The number of the current line after generating code
	 */
	public void generateTM();

	public int getSize();
}
