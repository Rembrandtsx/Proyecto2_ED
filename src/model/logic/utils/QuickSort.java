package model.logic.utils;

import java.util.Comparator;
import java.util.Random;

import model.data_structures.ArrayList;

public class QuickSort<T extends Comparable<T>>  {

	
	
	
	
	public void quickSort(ArrayList<T> lista, boolean ascendente, ComparatorHarvesianDistance comparator)
	{
		shuffleArray(lista);
		quickSorting(lista, 0, lista.size()-1, ascendente, comparator);
	}

	private void quickSorting(ArrayList<T> lista, int low, int high, boolean ascendente, ComparatorHarvesianDistance comparator)
	{
		if (low < high)
		{
			/* pi is partitioning index, arr[pi] is 
            now at right place */
			int pi = quickSortpartition(lista, low, high, ascendente, comparator);

			// Recursively sort elements before
			// partition and after partition
			quickSorting(lista, low, pi-1, ascendente, comparator);
			quickSorting(lista, pi+1, high, ascendente, comparator);
		}
	}
	public void shuffleArray(ArrayList<T> a)
	{
		int n = a.size();
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++) 
		{
			int change = i + random.nextInt(n - i);
			swap(a, i, change);
		}
	}
	private void swap(ArrayList<T> a, int i, int change)
	{
		T helper = a.get(i);
		a.insert(i, a.get(change));
		a.insert(change, helper);
	}
	private int quickSortpartition(ArrayList<T> lista, int low, int high, boolean ascendente, ComparatorHarvesianDistance comparador)
	{
		T pivot = lista.get(high); 
		int i = (low-1); // index of smaller element
		for (int j=low; j<high; j++)
		{

			if(ascendente)
			{
				// If current element is smaller than or
				// equal to pivot
				if (comparador.compare(lista.get(j), pivot) <= 0)
				{
					i++;

					// swap arr[i] and arr[j]
					T a = lista.get(i);
					T b = lista.get(j);

					lista.insert(j, a);
					lista.insert(i, b);
				}
			}
			else if(!ascendente)
			{
				if (comparador.compare(lista.get(j), pivot) >= 0)
				{
					i++;

					// swap arr[i] and arr[j]
					T a = lista.get(i);
					T b = lista.get(j);

					lista.insert(j, a);
					lista.insert(i, b);
				}
			}

		}
		// swap arr[i+1] and arr[high] (or pivot)
		T c = lista.get(i+1);
		lista.insert(i+1, lista.get(high));
		lista.insert(high, c);

		return i+1;
	}
}
