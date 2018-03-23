package test;

import junit.framework.TestCase;
import model.data_structures.HeapBinario;
import model.logic.utils.ComparatorPorNumeros;;


public class HeapBinarioTest extends TestCase
{

    private HeapBinario<Integer> heap;

    
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
        heap = new HeapBinario(new ComparatorPorNumeros());
        size = 0;

    }

    /**
     * Construye una nueva lista con 10 elementos
     * 
     */
    private void setupEscenario2( )
    {
    	heap = new HeapBinario(new ComparatorPorNumeros());
        size = 10;
        for( int cont = 0; cont < size; cont++ )
        {
            heap.swim( cont);
        }
        heap.swim(17);
        heap.swim(16);
        heap.swim(34);
        heap.swim(11);
        size=14;
    }

    /**
     * Construye una nueva lista con 10 elementos
     * 
     */
    private void setupEscenario3( )
    {
    	heap = new HeapBinario(new ComparatorPorNumeros());
        size = 10;
        for( int cont = size-1; cont >= 0; cont-- )
        {
            heap.swim( cont);
        }
        heap.swim(17);
        heap.swim(9);
        heap.swim(34);
        heap.swim(0);
        size=14;
    }

    /**
     * Prueba estructura
     * 
     **/
    public void testEstructuraHeapBinario( )
    {
        setupEscenario3( );
        
        // Verifica que la informaci�n sea correcta
        
        Integer padre;
        Integer hijo1;
        Integer hijo2;
        
        for(int i=1; i<size;i++){
        	padre=heap.get(i);
        	hijo1=heap.get(i*2);
        	hijo2=heap.get((i*2)+1);
        	if(hijo1!=null&&hijo2!=null){
        		int comparadorHijo1= padre.compareTo(hijo1);
        		int comparadorHijo2= padre.compareTo(hijo2);
        		assertTrue( "La estructura no cumple las condiciones del Heap binario", comparadorHijo1>=0&&comparadorHijo2>=0 );
        	}
        }
       
        // Verifica que el tamaño
        assertEquals( "El tamaño del Heap Binario no es el correcto", size, heap.size( )-1 );
    
    }
    /**
     * Prueba add
     * 
     **/
    public void testSwim( )
    {
        setupEscenario3( );
        
        // Verifica que la informaci�n sea correcta
        assertEquals( "La adici�n no se realiz� correctamente", heap.get(1) , (Integer) 34 );
        
        assertEquals( "La adici�n no se realiz� correctamente", heap.get(heap.size()-1) , (Integer) 0 );
        
       
        // Verifica que el tamaño
        assertEquals( "El tamaño del Heap Binario no es el correcto", size, heap.size( )-1 );
    
    }


    /**
     * Prueba delete Max
     * */
    public void testSink( )
    {
    	setupEscenario2( );
    	int t=0;
    	assertEquals(  "No se obtuvo el elemento indicado", heap.get(1), (Integer) 34);
    	
    	heap.sink();
           // Verifica que la informaci�n sea correcta
            assertTrue( "No se borro correctamente", heap.get(1)!=34);
            
         t=heap.get(1);
         heap.sink();
            assertTrue( "No se borro correctamente", heap.get(1)!=t);
            
          t=heap.get(1);
          heap.sink();
          assertTrue( "No se borro correctamente", heap.get(1)!=t);
              
          

    }

    /**
     * Prueba get
     * 
    */
    public void testGetPorPosicion( )
    {
    	setupEscenario2( );
        

        
            // Verifica que la informaci�n sea correcta
        	assertEquals(  "No se obtuvo el elemento indicado", heap.get(1), (Integer) 34);
        	assertTrue(  "No se obtuvo el elemento indicado", heap.get(size).getClass().equals(Integer.class));
            
            
            // Verifica que el tamaño
            assertEquals( "El tamaño de la lista no es el correcto", size, heap.size( )-1 );
         

   
    }
    
    /**
     * Prueba get Max
     * 
    */
    public void testGetPorElemento( )
    {
    	setupEscenario3( );
        
    	// Verifica que la informaci�n sea correcta
            assertTrue( "No se obtuvo el elemento indicado", heap.get(1)!=null);
            
           
            
            // Verifica que el tamaño
            assertEquals( "El tamaño de la lista no es el correcto", size, heap.size()-1 );
         

   
    }
    /**
     * Prueba size
     * 
     * */
    public void testSize( )
    {
        setupEscenario2( );
        
        
     // Verifica que el tamaño
        assertEquals( "El tamaño de la lista no es el correcto", size, heap.size( )-1 );
     
        heap.sink();
        
     // Verifica que el tamaño
        assertEquals( "El tamaño de la lista no es el correcto", size-1, heap.size( )-1 );
      
    }

    
}