/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerAction;

/**
 *
 * @author Hello
 */
public class view {
private static   String savefile = "E:\\test.txt";
private static void saveAsFileWriter(String content) {
 
	 FileWriter fwriter = null;
	 try {
	  fwriter = new FileWriter(savefile);
	  fwriter.write(content);
	 } catch (IOException ex) {
	  ex.printStackTrace();
	 } finally {
	  try {
	   fwriter.flush();
	   fwriter.close();
	  } catch (IOException ex) {
	   ex.printStackTrace();
	  }
	 }

}
}
