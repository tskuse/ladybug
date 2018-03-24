package com.mycompany.a2;

public interface ICollection<E> {
	
	public void add(E object);
	
	public IIterator<E> getIterator();

}
