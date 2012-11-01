package edu.macalester;

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 11/1/12
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class textParser {
	public void textContent(String sms,String passcode){
		String[] res = sms.split("//s+");
		boolean passphrase = false;
		for(int i=0; i<res.length; i++){
			if(res[i].equals(passcode)){
				passphrase = true;
				res[i] = null;
			}
		}
		//if(passphrase) TriangulumMain.something(res);
	}
}
