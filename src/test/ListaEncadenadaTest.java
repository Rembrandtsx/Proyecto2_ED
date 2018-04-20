package test;

import junit.framework.TestCase;
import model.data_structures.LinkedSimpleList;


public class ListaEncadenadaTest extends TestCase
{

    private LinkedSimpleList<String> lista;

    
    private int size;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una lista  vacia
     * 
     */
    private void setupEscenario1( )
    {
        lista = new LinkedSimpleList<String>();
        size = 0;

    }

    /**
     * Construye una nueva lista con 10 elementos
     * 
     */
    private void setupEscenario2( )
    {
        lista = new LinkedSimpleList<String>();
        size = 10;
        for( int cont = 0; cont < size; cont++ )
        {
            lista.add( "text 1");
        }
    }

    /**
     * Construye una nueva lista con 10 elementos
     * 
     */
    private void setupEscenario3( )
    {
    	lista = new LinkedSimpleList<String>();
        size = 100;
        for( int cont = 0; cont < size; cont++ )
        {
        	String s = "text "+cont;
            lista.add(s);
        }
    }

    /**
     * Prueba add
     * 
     **/
    public void testAgregarLista( )
    {
        setupEscenario1( );
        size = 11;
        for( int cont = 0; cont < size; cont++ )
        {
            lista.add( "text 1");
        }
        // Verifica que la informaci�n sea correcta
        assertEquals( "La adici�n no se realiz� correctamente", lista.get(0), "text 1" );
        

        // Verifica que el tamaño
        assertEquals( "El tamaño de la lista no es el correcto", size, lista.size( ) );
    
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
            lista.add( "text "+ cont );
        }
        

        lista.delete("text 4");
        lista.delete("text 7");
            // Verifica que la informaci�n sea correcta
            assertTrue( "No se borro correctamente", lista.get("text 4")!=null);
            assertTrue( "No se borro correctamente", lista.get("text 7")!=null);
            assertTrue( "No se borro correctamente", lista.delete("text 4"));
            assertTrue( "No se borro correctamente", lista.delete("text 7"));
            
          

    }

    /**
     * Prueba get
     * 
    */
    public void testGetPorPosicion( )
    {
    	setupEscenario1( );
        size = 11;
        for( int cont = 0; cont < size; cont++ )
        {
            lista.add( "text "+ cont );
        }

        
            // Verifica que la informaci�n sea correcta
        	assertEquals(  "No se obtuvo el elemento indicado", lista.get(4), "text 4");
        	assertEquals(  "No se obtuvo el elemento indicado", lista.get(10), "text 10");
            
            
            // Verifica que el tamaño
            assertEquals( "El tamaño de la lista no es el correcto", size, lista.size( ) );
         

   
    }
    /**
     * Prueba get
     * 
    */
    public void testGetPorElemento( )
    {
    	setupEscenario1( );
        size = 11;
        for( int cont = 0; cont < size; cont++ )
        {
            lista.add( "text "+ cont );
        }

        
            // Verifica que la informaci�n sea correcta
            assertTrue( "No se obtuvo el elemento indicado", lista.get("text 4")!=null);
            assertTrue( "No se obtuvo el elemento indicado", lista.get(5)!=null);
        
            
            
            // Verifica que el tamaño
            assertEquals( "El tamaño de la lista no es el correcto", size, lista.size( ) );
         

   
    }
    /**
     * Prueba size
     * 
     * */
    public void testSize( )
    {
        setupEscenario2( );
        lista.add( "text 2");
        size=11;
        
     // Verifica que el tamaño
        assertEquals( "El tamaño de la lista no es el correcto", size, lista.size( ) );
     
        lista.delete("text 2");
     // Verifica que el tamaño
        assertEquals( "El tamaño de la lista no es el correcto", size-1, lista.size( ) );
      
    }

    /**
     * Prueba listing
     * 
     * */
    public void testListing( )
    {
        setupEscenario2( );
        size = 11;
        String[] listaElementos= new String[lista.size()];
		lista.listing(listaElementos);
		
		
		for (String lista : listaElementos) {
	        assertEquals( "La información de los elementos esta mal", lista, "text 1" );
		}
		// Verifica que el tamaño
        assertEquals( "El tamaño de la lista no es el correcto", size-1, lista.size( ) );
     
    }
    
    public void test1( )
    {
        setupEscenario3( );
        
       for (String string : lista) {
    	   System.out.println(string);
       }
		
     
    }
    

}