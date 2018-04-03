package test;

import java.util.Iterator;

import junit.framework.TestCase;
import model.data_structures.LinkedSimpleList;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;
import model.data_structures.SimpleNodeSymbolTable;
import model.data_structures.SymbolTableLP;
import model.data_structures.SymbolTableSC;

//-----------------------------------------------------------------
// Clase de pruebas unitatias para Symbol Table con la implementacion de Linear Probing
// -----------------------------------------------------------------


public class RedBlackBSTTest extends TestCase
{

    private RedBlackBST<Integer, String> redBlackBST;

    
    private int size;
    
    

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un redBlackBST vacio
     * 
     */
    private void setupEscenario1( )
    {
    	
        redBlackBST = new RedBlackBST<>();
        size = 0;

    }

    /**
     * Construye un redBlackBST con 10 elementos
     * 
     */
    private void setupEscenario2( )
    {
    	
        redBlackBST = new RedBlackBST<>();
        size = 10;
        for( int cont = 0; cont < size; cont++ )
        {
            redBlackBST.put(cont, "Test " + cont);
        }
    }

    /**
     * Construye un redBlackBST con 100 elementos
     * 
     */
    private void setupEscenario3( )
    {
    	
        redBlackBST = new RedBlackBST<>();
        size = 101;
        for( int cont = size; cont > 0; cont--)
        {
            redBlackBST.put(cont, "Test " + cont);
        }
    }

    /**
     * Prueba put
     * 
     **/
    public void testPut( )
    {
        setupEscenario1( );
        size = 11;
        for( int cont = 0; cont < size; cont++ )
        {
            redBlackBST.put(cont, "Test " + cont);
        }
        // Verifica que la informaci�n sea correcta
        assertEquals( "La adici�n no se realiz� correctamente", redBlackBST.get(0), "Test 0" );
        
        assertEquals( "La adici�n no se realiz� correctamente", redBlackBST.get(3), "Test 3" );
        
        assertEquals( "La adici�n no se realiz� correctamente", redBlackBST.get(7), "Test 7" );
        
        assertEquals( "La adici�n no se realiz� correctamente", redBlackBST.get(10), "Test 10" );
        
        assertEquals( "La adici�n no se realiz� correctamente", redBlackBST.get(20), null );
        
        // Verifica que la informaci�n sea correcta, cuando la llave ya existe
        
        redBlackBST.put(0, "Test " + 54);
        redBlackBST.put(0, "Test " + 32);
        redBlackBST.put(0, "Test " + 10);
        
        
        redBlackBST.put(100, "Test " + 100);
        size ++;
        // Prueban las colisiones, con el metodo getNode que retorna la lista con los objetos que tienen la misma key
        assertEquals( "La adici�n no se realiz� correctamente", redBlackBST.get(0), "Test 10" );
        assertEquals( "La adici�n no se realiz� correctamente", redBlackBST.get(100), "Test 100" );
        
        // Verifica el tamaño
        assertEquals( "El tamaño no es el correcto", size, redBlackBST.size( ) );
    
    }

    /**
     * Prueba delete
     * */
    public void testDelete( )
    {
    	setupEscenario1( );
        size = 11;
        for( int cont = 0; cont < size; cont++ )
        {
            redBlackBST.put(cont, "Test " + cont);
        }
        

        redBlackBST.delete(4);
        redBlackBST.delete(7);
        
            // Verifica que la informaci�n sea correcta
        assertEquals( "No se borro correctamente", redBlackBST.get(4), null );
        assertEquals( "No se borro correctamente", redBlackBST.get(7), null );
        
        redBlackBST.delete(4);
        redBlackBST.delete(10);
        redBlackBST.delete(2);
        redBlackBST.delete(1);
        
        assertEquals( "No se borro correctamente", redBlackBST.get(4), null );
        assertEquals( "No se borro correctamente", redBlackBST.get(10), null );
        
         // Verifica el tamaño
        assertEquals( "El tamaño no es el correcto", size-5, redBlackBST.size( ) );
        

    }

    /**
     * Prueba get
     * 
    */
    public void testGet( )
    {
    	setupEscenario2( );
        
    	for( int cont = 0; cont < size; cont++ )
        {
    		assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.get(cont), "Test "+ cont);
        	
        }
        
            // Verifica que la informaci�n sea correcta
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.get(4), "Test 4");
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.get(0), "Test 0");
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.get(8), "Test 8");
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.get(2), "Test 2");
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.get(10), null);
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.get(50), null);
            
        	
        	
        	// Verifica put si la llave ya esta agregada
        	redBlackBST.put(0, "Test " + 54);
            redBlackBST.put(7, "Test " + 54);
            
         // Prueban las colisiones, con el metodo getNode que retorna la lista con los objetos que tienen la misma key
            assertEquals( "La adici�n no se realiz� correctamente", redBlackBST.get(0), "Test 54" );
            assertEquals( "La adici�n no se realiz� correctamente", redBlackBST.get(0), "Test 54" );
            
            // Verifica que el tamaño
            assertEquals( "El tamaño no es el correcto", size, redBlackBST.size( ) );
         

   
    }
    
    /**
     * Prueba size
     * 
     * */
    public void testSize( )
    {
        setupEscenario3( );
        
     // Verifica que el tamaño
        assertEquals( "El tamaño no es el correcto", size, redBlackBST.size( ) );
      
        
        redBlackBST.put( 50, "Test 74");
        
     // Verifica que el tamaño
        assertEquals( "El tamaño no es el correcto", size, redBlackBST.size( ) );
     
     
        redBlackBST.delete(54);
        redBlackBST.delete(64);
        redBlackBST.delete(100);
        size=size-3;
     // Verifica que el tamaño
        assertEquals( "El tamaño no es el correcto", size, redBlackBST.size( ) );
      
    }

    /**
     * Prueba Estructura
     * 
     * */
    public void testEstructura( )
    {
    	
    	setupEscenario1();
    	///Se utiliza un ejemplo con 10 elementos
    	size = 20; ///Numero para el cual hay sobrecarga, se aplica rehash
        for( int cont = 0; cont < size; cont++ )
        {
            redBlackBST.put(cont, "Test " + cont);
        }
      ///Se agregan elementos aleatorios
    	
        
        
        
     // Verifica que el tamaño
        assertEquals( "El tamaño no es el correcto", size, redBlackBST.size( ) );
        
        
    }
    /**
     * Prueba keys
     * 
     * */
    public void testKeys( )
    {
        setupEscenario2( );
        
        Queue<Integer> listaLlaves=  (Queue<Integer>) redBlackBST.keys();
        
       Integer i=0;
       Integer current= listaLlaves.dequeue();
		while(current!=null) {
			assertEquals( "La información de los elementos esta mal", current, i);
			i++;
			current= listaLlaves.dequeue();
		}	
			
		
		
		// Verifica que el tamaño
        assertEquals( "El tamaño de la lista no es el correcto", size, redBlackBST.size( ) );
     
    }
    /**
     * Prueba contains
     * 
    */
    public void testContains( )
    {
    	setupEscenario3( );
        

        
            // Verifica que la informaci�n sea correcta
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.contains(4), true);
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.contains(8), true);
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.contains(54), true);
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.contains(3), true);
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.contains(2), true);
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.contains(97), true);
        	
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.contains(150), false);
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.contains(200), false);
        	assertEquals(  "No se obtuvo el elemento indicado", redBlackBST.contains(300), false);
        	
        	
        	
            // Verifica que el tamaño
            assertEquals( "El tamaño no es el correcto", size, redBlackBST.size( ) );
         

   
    }
    /**
     * Prueba in Empty
     * 
    */
    public void testIsEmpty( )
    {
    	setupEscenario1( );
        
            // Verifica que el tamaño
        assertEquals( "Debe estar vacia.", redBlackBST.isEmpty(), true );
         
            size = 11;
            for( int cont = 0; cont < size; cont++ )
            {
                redBlackBST.put(cont, "Test " + cont);
            }
         assertEquals( "No debe estar vacia.", redBlackBST.isEmpty(), false );
              
   
    }
    /**
     * Prueba in min
     * 
    */
    public void testMin( )
    {
    	setupEscenario3( );
        
    	
        assertEquals( "Ese no es el minimo.", 1 , (int) redBlackBST.min() );
         
        redBlackBST.delete(1);
        redBlackBST.delete(2);
        redBlackBST.delete(3);
                
        assertEquals( "Ese no es el minimo.", 4 , (int) redBlackBST.min() );
        
    }
    /**
     * Prueba max
     * 
    */
    public void testMax( )
    {
    	setupEscenario3( );
        
    	
        assertEquals( "Ese no es el maximo.", 101 , (int) redBlackBST.max() );
         
        redBlackBST.deleteMax();
        redBlackBST.delete(100);
        redBlackBST.deleteMax();
                
        assertEquals( "Ese no es el maximo.", 98 , (int) redBlackBST.max() );
        
    }
}