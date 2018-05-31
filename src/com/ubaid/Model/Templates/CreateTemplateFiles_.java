package com.ubaid.Model.Templates;

import java.io.File;
import java.io.IOException;

import com.ubaid.controller.Controller;

public class CreateTemplateFiles_ 
{
	public CreateTemplateFiles_(Controller controller, File file) throws IOException
	{
		for(int i = 1; i <= 16; i++)
		{
			File file2 = new File(file.getAbsolutePath() + "/Template_" + i + ".html"); 
			System.out.println(file2.createNewFile());
		}
	}
}
