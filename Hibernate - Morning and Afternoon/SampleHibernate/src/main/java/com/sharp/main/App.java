package com.sharp.main;

import com.sharp.jdbc.DbConnector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DbConnector.connect();
    }
}
