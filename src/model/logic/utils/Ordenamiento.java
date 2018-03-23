package model.logic.utils;
import java.util.Comparator;

import model.data_structures.ILinkedList;
import model.data_structures.LinkedSimpleList;
import model.data_structures.SimpleNode;


public class Ordenamiento <T extends Comparable<T>>{

	
	    SimpleNode<T> head;
	    
	   
	 
    public void mergeSortLinkedList(LinkedSimpleList<T> services) {
        head = sortLinkedList(services.getFirst());
    }

    private SimpleNode<T> sortLinkedList(SimpleNode<T> head) {

        if (head == null || head.getNext() == null)
            return head;

        int numOfElements = getCount(head);

        int mid = numOfElements / 2;

        SimpleNode<T> currentSimpleNode = head;
        SimpleNode<T> left = head;
        SimpleNode<T> right = null;

        int countHalf = 0;
        while (currentSimpleNode != null) {
            countHalf++;
            SimpleNode<T> next = currentSimpleNode.getNext();

            if (countHalf == mid) {
                currentSimpleNode.modifyNext(null);
                right = next;
            }
            currentSimpleNode = next;
        }

        SimpleNode<T> leftHalf = sortLinkedList(left);
        SimpleNode<T> rightHalf = sortLinkedList(right);
        SimpleNode<T> mergedLinkedList = merge(leftHalf, rightHalf);

        return mergedLinkedList;
    }

    private SimpleNode<T> merge(SimpleNode<T> left, SimpleNode<T> right) {
        SimpleNode<T> leftSimpleNode = left;
        SimpleNode<T> rightSimpleNode = right;

        SimpleNode<T> duplicateHead = new SimpleNode<T>(null);
        SimpleNode<T> currentSimpleNode = duplicateHead;

        while (leftSimpleNode != null || rightSimpleNode != null) {

            if (leftSimpleNode == null) {
                currentSimpleNode.modifyNext(new SimpleNode<T>(rightSimpleNode.getElement()));
                rightSimpleNode = rightSimpleNode.getNext();
                currentSimpleNode = currentSimpleNode.getNext();

            } else if (rightSimpleNode == null) {
            	currentSimpleNode.modifyNext(new SimpleNode<T>(leftSimpleNode.getElement()));
                leftSimpleNode = leftSimpleNode.getNext();
                currentSimpleNode = currentSimpleNode.getNext();

            } else {
                if (leftSimpleNode.getElement().compareTo( rightSimpleNode.getElement()) < 0) {
                	currentSimpleNode.modifyNext(new SimpleNode<T>(leftSimpleNode.getElement()));
                    leftSimpleNode = leftSimpleNode.getNext();
                    currentSimpleNode = currentSimpleNode.getNext();

                } else if (leftSimpleNode.getElement().compareTo(rightSimpleNode.getElement()) == 0) {
                	    currentSimpleNode.modifyNext(new SimpleNode<T>(leftSimpleNode.getElement()));
                    currentSimpleNode.getNext().modifyNext(new SimpleNode<T>(rightSimpleNode.getElement()));
                    currentSimpleNode = currentSimpleNode.getNext().getNext();
                    leftSimpleNode = leftSimpleNode.getNext();
                    rightSimpleNode = rightSimpleNode.getNext();

                } else {
                    currentSimpleNode.modifyNext(new SimpleNode<T>(rightSimpleNode.getElement()));
                    rightSimpleNode = rightSimpleNode.getNext();
                    currentSimpleNode = currentSimpleNode.getNext();
                }
            }
        }

        return duplicateHead.getNext();
    }

    private int getCount(SimpleNode<T> head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.getNext();
        }
        return count;
    }

	    
	
}
