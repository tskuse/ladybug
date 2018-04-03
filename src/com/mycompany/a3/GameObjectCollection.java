package com.mycompany.a3;

import java.util.Vector;

public class GameObjectCollection<E> implements ICollection<E> {
	
	private Vector<E> objects;
	
	public GameObjectCollection() {
		objects = new Vector<E>();
	}

	public void add(E object) {
		objects.add(object);
	}

	public IIterator<E> getIterator() {
		return new GameObjectIterator(0);
	}
	
	private class GameObjectIterator implements IIterator<E> {
		
		private int current;
		
		public GameObjectIterator(int first) {
			current = first;
		}

		public boolean hasNext() {
			return current < objects.size();
		}

		public E getNext() {
			return objects.get(current++);
		}
		
	}

}
