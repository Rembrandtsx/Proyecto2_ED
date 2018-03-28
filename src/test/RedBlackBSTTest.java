package test;

import java.util.Iterator;

import junit.framework.TestCase;
import model.data_structures.LinkedSimpleList;
import model.data_structures.Queue;
import model.data_structures.SimpleNodeSymbolTable;
import model.data_structures.SymbolTableLP;
import model.data_structures.SymbolTableSC;

//-----------------------------------------------------------------
// Clase de pruebas unitatias para Symbol Table con la implementacion de Linear Probing
// -----------------------------------------------------------------


public class RedBlackBSTTest extends TestCase
{

    private SymbolTableSC<Integer, String> hashTable;

    
    private int size;
    
    private int capacity;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva hash Table LP vacia
     * 
     */
    private void setupEscenario1( )
    {
    	capacity=101;
        hashTable = new SymbolTableSC<>(capacity);
        size = 0;

    }

    /**
     * Construye una nueva hash Table LP con 10 elementos
     * 
     */
    private void setupEscenario2( )
    {
    	capacity=101;
        hashTable = new SymbolTableSC<>(capacity);
        size = 10;
        for( int cont = 0; cont < size; cont++ )
        {
            hashTable.put(cont, "Test " + cont);
        }
    }

    /**
     * Construye una nueva hash Table LP con 100 elementos
     * 
     */
    private void setupEscenario3( )
    {
    	capacity=101;
        hashTable = new SymbolTableSC<>(capacity);
        size = 101;
        for( int cont = 0; cont < size; cont++ )
        {
            hashTable.put(cont, "Test " + cont);
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
            hashTable.put(cont, "Test " + cont);
        }
        // Verifica que la informaci�n sea correcta
        assertEquals( "La adici�n no se realiz� correctamente", hashTable.get(0), "Test 0" );
        
        assertEquals( "La adici�n no se realiz� correctamente", hashTable.get(3), "Test 3" );
        
        assertEquals( "La adici�n no se realiz� correctamente", hashTable.get(7), "Test 7" );
        
        assertEquals( "La adici�n no se realiz� correctamente", hashTable.get(10), "Test 10" );
        
        assertEquals( "La adici�n no se realiz� correctamente", hashTable.get(20), null );
        
        // Verifica que la informaci�n sea correcta, cuando la llave ya existe
        
        hashTable.put(0, "Test " + 54);
        hashTable.put(0, "Test " + 32);
        hashTable.put(0, "Test " + 10);
        size=size+3;
        LinkedSimpleList<SimpleNodeSymbolTable<Integer, String>> lista= hashTable.getNode(0);
        
        
     // Prueban las colisiones, con el metodo getNode que retorna la lista con los objetos que tienen la misma key
        assertEquals( "La adici�n no se realiz� correctamente", hashTable.getNode(0).get(0).getElement(), "Test 0" );
        assertEquals( "La adici�n no se realiz� correctamente", hashTable.getNode(0).get(1).getElement(), "Test 54" );
        assertEquals( "La adici�n no se realiz� correctamente", hashTable.getNode(0).get(2).getElement(), "Test 32" );
        assertEquals( "La adici�n no se realiz� correctamente", hashTable.getNode(0).get(3).getElement(), "Test 10" );
        
        // Verifica el tamaño
        assertEquals( "El tamaño no es el correcto", size, hashTable.size( ) );
    
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
            hashTable.put(cont, "Test " + cont);
        }
        

        hashTable.delete(4);
        hashTable.delete(7);
        
            // Verifica que la informaci�n sea correcta
        assertEquals( "No se borro correctamente", hashTable.get(4), null );
        assertEquals( "No se borro correctamente", hashTable.get(7), null );
        
        hashTable.delete(4);
        hashTable.delete(10);
       
        assertEquals( "No se borro correctamente", hashTable.get(4), null );
        assertEquals( "No se borro correctamente", hashTable.get(10), null );
        
         // Verifica el tamaño
            assertEquals( "El tamaño no es el correcto", size-3, hashTable.size( ) );
        

    }

    /**
     * Prueba get
     * 
    */
    public void testGet( )
    {
    	setupEscenario2( );
        

        
            // Verifica que la informaci�n sea correcta
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.get(4), "Test 4");
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.get(0), "Test 0");
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.get(8), "Test 8");
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.get(2), "Test 2");
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.get(10), null);
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.get(50), null);
            
        	
        	
        	// Verifica obtener lista, a partir de las colisiones
        	hashTable.put(0, "Test " + 54);
            hashTable.put(0, "Test " + 32);
            hashTable.put(0, "Test " + 10);
            
            hashTable.put(7, "Test " + 54);
            hashTable.put(7, "Test " + 32);
            hashTable.put(7, "Test " + 10);
            
            size=size+6;
            LinkedSimpleList<SimpleNodeSymbolTable<Integer, String>> lista= hashTable.getNode(0);
            
            LinkedSimpleList<SimpleNodeSymbolTable<Integer, String>> lista2= hashTable.getNode(7);
            
         // Prueban las colisiones, con el metodo getNode que retorna la lista con los objetos que tienen la misma key
            assertEquals( "La adici�n no se realiz� correctamente", lista.get(0).getElement(), "Test 0" );
            assertEquals( "La adici�n no se realiz� correctamente", lista.get(1).getElement(), "Test 54" );
            assertEquals( "La adici�n no se realiz� correctamente", lista.get(2).getElement(), "Test 32" );
            assertEquals( "La adici�n no se realiz� correctamente", lista.get(3).getElement(), "Test 10" );
            
        	
            assertEquals( "La adici�n no se realiz� correctamente", lista2.get(0).getElement(), "Test 7" );
            assertEquals( "La adici�n no se realiz� correctamente", lista2.get(1).getElement(), "Test 54" );
            assertEquals( "La adici�n no se realiz� correctamente", lista2.get(2).getElement(), "Test 32" );
            assertEquals( "La adici�n no se realiz� correctamente", lista2.get(3).getElement(), "Test 10" );
            
            // Verifica que el tamaño
            assertEquals( "El tamaño no es el correcto", size, hashTable.size( ) );
         

   
    }
    
    /**
     * Prueba size
     * 
     * */
    public void testSize( )
    {
        setupEscenario3( );
        
     // Verifica que el tamaño
        assertEquals( "El tamaño no es el correcto", size, hashTable.size( ) );
      
        
        hashTable.put( 50, "Test 74");
        size=size+1;
     
        // Verifica que el tamaño
        assertEquals( "El tamaño no es el correcto", size, hashTable.size( ) );
     
     
        hashTable.delete(54);
        hashTable.delete(64);
        hashTable.delete(100);
        size=size-3;
     // Verifica que el tamaño
        assertEquals( "El tamaño no es el correcto", size, hashTable.size( ) );
      
    }

    /**
     * Prueba reHash
     * 
     * */
    public void testReHash( )
    {
    	setupEscenario1();
    	
    	capacity=101;
        size = 607; ///Numero para el cual hay sobrecarga, se aplica rehash
        for( int cont = 0; cont < size; cont++ )
        {
            hashTable.put(cont, "Test " + cont);
        }
        capacity= obtieneNumPrimo(capacity);
     // Verifica que el tamaño
        assertEquals( "El tamaño no es el correcto", size, hashTable.size( ) );
     // Verifica la carga
        assertEquals( "La capacidad no es el correcto", capacity, hashTable.capacity() );
        
        
    }
    /**
     * Prueba keys
     * 
     * */
    public void testKeys( )
    {
        setupEscenario2( );
        
        Iterator<Integer> listaLlaves= hashTable.Keys();
        
        int i=0;
		while(listaLlaves.hasNext()) {
			
			assertEquals( "La información de los elementos esta mal",(int) listaLlaves.next(), i);
			
			i++;
		}
		
		
		// Verifica que el tamaño
        assertEquals( "El tamaño de la lista no es el correcto", size, hashTable.size( ) );
     
    }
    /**
     * Prueba contains
     * 
    */
    public void testContains( )
    {
    	setupEscenario3( );
        

        
            // Verifica que la informaci�n sea correcta
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.contains(4), true);
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.contains(8), true);
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.contains(54), true);
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.contains(3), true);
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.contains(2), true);
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.contains(97), true);
        	
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.contains(150), false);
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.contains(200), false);
        	assertEquals(  "No se obtuvo el elemento indicado", hashTable.contains(300), false);
        	
        	
        	
            // Verifica que el tamaño
            assertEquals( "El tamaño no es el correcto", size, hashTable.size( ) );
         

   
    }
    /**
     * Prueba in Empty
     * 
    */
    public void testIsEmpty( )
    {
    	setupEscenario1( );
        
            // Verifica que el tamaño
        assertEquals( "Debe estar vacia.", hashTable.isEmpty(), true );
         
            size = 11;
            for( int cont = 0; cont < size; cont++ )
            {
                hashTable.put(cont, "Test " + cont);
            }
         assertEquals( "No debe estar vacia.", hashTable.isEmpty(), false );
              
   
    }

    public int obtieneNumPrimo(int cap){
		int numPosPrimo= (cap*2)+1;
		
		boolean esPrimo=true;
		while(esPrimo==true){
			
			for(int i=2; i<numPosPrimo;i++){
					if(numPosPrimo%i==0.0){
						esPrimo=false;
					}
			}
			if(esPrimo==true){
				return numPosPrimo;
			}
			else{
				numPosPrimo++;
				esPrimo=true;
			}
		}
		return numPosPrimo;
	}
}