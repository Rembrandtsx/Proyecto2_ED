package model.data_structures;

import java.util.NoSuchElementException;

public class RedBlackBST<K extends Comparable<K>, V> {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;     

    
    private class Node {
        private K key;           
        private V val;         
        private Node left, right;  
        private boolean color;     
        private int size;         

        public Node(K key, V val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

  
    public RedBlackBST() {
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

   
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    } 


    public int size() {
        return size(root);
    }


    public boolean isEmpty() {
        return root == null;
    }



    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Argumento Null");
        return get(root, key);
    }

   
    private V get(Node x, K key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }


    public boolean contains(K key) {
        return get(key) != null;
    }


    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("Argumento Nulo");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;

    }

    
    private Node put(Node h, K key, V val) { 
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key, val); 
        else if (cmp > 0) h.right = put(h.right, key, val); 
        else              h.val   = val;

        
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }


    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        
    }


    private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }


    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;

    }

 
    private Node deleteMax(Node h) { 
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }


    public void delete(K key) { 
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;

    }

  
    private Node delete(Node h, K key) { 


        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
     
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }



   
    private Node rotateRight(Node h) {

        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }


    private Node rotateLeft(Node h) {
      
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }


    private void flipColors(Node h) {

        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

  
    private Node moveRedLeft(Node h) {


        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

   
    private Node moveRedRight(Node h) {

        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }


    private Node balance(Node h) {


        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }


    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }


    public K min() {
        if (isEmpty()) throw new NoSuchElementException("Llamada con una tabla vacia");
        return min(root).key;
    } 

  
    private Node min(Node x) { 

        if (x.left == null) return x; 
        else                return min(x.left); 
    } 


    public K max() {
        if (isEmpty()) throw new NoSuchElementException("llamada con una tabla vacia");
        return max(root).key;
    } 

    
    private Node max(Node x) { 
        
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 


    public K floor(K key) {
        if (key == null) throw new IllegalArgumentException("argumento null");
        if (isEmpty()) throw new NoSuchElementException("llamada con una tabla vacia");
        Node x = floor(root, key);
        if (x == null) return null;
        else           return x.key;
    }    

    
    private Node floor(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t; 
        else           return x;
    }

    
    public K ceiling(K key) {
        if (key == null) throw new IllegalArgumentException("Argumento Invalido");
        if (isEmpty()) throw new NoSuchElementException("Llamado con una tabla vacia");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else           return x.key;  
    }

    
    private Node ceiling(Node x, K key) {  
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0)  return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t; 
        else           return x;
    }


    public K select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("Argumento invalidp : " + k);
        }
        Node x = select(root, k);
        return x.key;
    }

  
    private Node select(Node x, int k) {

        int t = size(x.left); 
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    } 


    public int rank(K key) {
        if (key == null) throw new IllegalArgumentException("El argumento es Null");
        return rank(key, root);
    } 


    private int rank(K key, Node x) {
        if (x == null) return 0; 
        int cmp = key.compareTo(x.key); 
        if      (cmp < 0) return rank(key, x.left); 
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
        else              return size(x.left); 
    } 

    public Iterable<K> keys() {
        if (isEmpty()) return (Iterable<K>) new LinkedSimpleList<K>();
        return keys(min(), max());
    }

    public Iterable<K> keys(K lo, K hi) {
        if (lo == null) throw new IllegalArgumentException("Primer Argumento es Null");
        if (hi == null) throw new IllegalArgumentException("Segundo Argumento es Null");

        LinkedSimpleList<K> list = new LinkedSimpleList<K>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys(root, list, lo, hi);
        return (Iterable<K>) list;
    } 

    private void keys(Node x, LinkedSimpleList<K> list, K lo, K hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, list, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) list.add(x.key); 
        if (cmphi > 0) keys(x.right, list, lo, hi); 
    } 

 
    public int size(K lo, K hi) {
        if (lo == null) throw new IllegalArgumentException("El Primer Argumento es Null");
        if (hi == null) throw new IllegalArgumentException("el segundo argumento es Null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }


  
    private boolean check() {
        if (!isBST())            System.out.println("No Esta En Orden Simetrico");
        if (!isSizeConsistent()) System.out.println("Conteo de SubArboles No consistenten");
        if (!isRankConsistent()) System.out.println("Ranks no consistentes");
        if (!is23())             System.out.println("No es un arbol 2-3");
        if (!isBalanced())       System.out.println("no esta balanceado");
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }

   
    private boolean isBST() {
        return isBST(root, null, null);
        
    }

    
    private boolean isBST(Node x, K min, K max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    } 

    
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    } 

    
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (K key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    
    private boolean is23() { return is23(root); }
    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    } 

   
    private boolean isBalanced() { 
        int black = 0;     
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

   
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    } 
}
