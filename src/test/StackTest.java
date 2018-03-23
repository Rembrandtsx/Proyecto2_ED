package test;

import junit.framework.TestCase;

import model.data_structures.Stack;

public class StackTest extends TestCase{
	

	private Stack<String> pila;

    
    private int size;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una pila  vacia
     * 
     */
    private void setupEscenario1( )
    {
        pila = new Stack<String>();
        size = 0;

    }

    /**
     * Construye una nueva pila con 10 elementos
     * 
     */
    private void setupEscenario2( )
    {
        pila = new Stack<String>();
        size = 10;
        for( int cont = 0; cont < size; cont++ )
        {
            pila.push("text "+ cont);;
        }
    }

   

    /**
     * Prueba push
     * 
     **/
    public void testPush1( )
    {
        setupEscenario1( );
        size = 11;
        
        for( int cont = 0; cont < size; cont++ )
        {
            pila.push("text " + cont);;
        }
        // Verifica que la informaci�n sea correcta
        assertEquals( "La adici�n no se realiz� correctamente", pila.pop(), "text 10" );
        

        // Verifica que el tamaño
        assertEquals( "El tamaño de la lista no es el correcto", size-1, pila.getSize() );
    
    }

    /**
     * Prueba Pop
     * */
    public void testPop( )
    {
    	setupEscenario1( );
        size = 14;
        for( int cont = 0; cont < size; cont++ )
        {
            pila.push("text "+ cont);
        }
        
        // Verifica que la informaci�n sea correcta
        	assertEquals( "No se saco correctamente", pila.pop(), "text 13");
        	assertEquals( "No se saco correctamente", pila.pop(), "text 12");
        	assertEquals( "No se saco correctamente", pila.pop(), "text 11");
        	assertEquals( "No se saco correctamente", pila.pop(), "text 10");
            
            // Verifica que el tamaño
            assertEquals( "El tamaño de la cola no es el correcto", size-4, pila.getSize() );
         

    }

    public void testIsEmpty( )
    {
    	setupEscenario1( );
       
    	assertTrue( "Incorrecto la lista debe estar vacia", pila.isEmpty());
        
    	assertEquals( "El tamaño de la lista no es el correcto", size, pila.getSize() );
         

   
    }
    

}
