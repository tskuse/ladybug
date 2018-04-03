package com.mycompany.a3;

public interface ICollection<E> {
	
	public void add(E object);
	
	public IIterator<E> getIterator();

}
