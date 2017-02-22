package com.pranil.Log4J;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
/*{
	final static Logger logger=Logger.getLogger(App.class);
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        logger.error("Error happens here");
    }
}*/

{

	final static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {

		App obj = new App();

		try{
			obj.divide();
		}catch(ArithmeticException ex){
			logger.error("Sorry, something wrong!", ex);
		}


	}

	private void divide(){

		int i = 10 /0;

	}

}
