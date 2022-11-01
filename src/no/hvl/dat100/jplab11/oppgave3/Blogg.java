package no.hvl.dat100.jplab11.oppgave3;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;

import java.util.Arrays;

public class Blogg {

	Innlegg[] inleggtabell;
	int nesteledig = 0;

	public Blogg() {
		inleggtabell = new Innlegg[20];
		nesteledig = 0;
	}

	public Blogg(int lengde) {
		inleggtabell = new Innlegg[lengde];
		nesteledig = 0;
	}

	public int getAntall() {
		return this.nesteledig;
	}
	
	public Innlegg[] getSamling() {
		return this.inleggtabell;
	}

	private void quickSort(Innlegg[] array, int lowIndex, int highIndex){
		if (lowIndex >= highIndex){
			return;
		}

		int pivot = array[highIndex].getId();
		int leftPointer = lowIndex;
		int rightPointer = highIndex;
		while(leftPointer < rightPointer){
			while(array[leftPointer].getId() <= pivot && leftPointer < rightPointer){
				leftPointer++;
			}
			while(array[rightPointer].getId() >= pivot && leftPointer < rightPointer){
				rightPointer--;
			}
			swap(array, rightPointer, leftPointer);
		}
		swap(array, leftPointer, highIndex);

		quickSort(array, lowIndex, leftPointer - 1);
		quickSort(array, leftPointer + 1, highIndex);
	}

	private void swap(Innlegg[] array, int index1, int index2){
		Innlegg temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;

	}


	public int finnInnlegg(Innlegg innlegg) {
		if (nesteledig > 2) {
			quickSort(this.inleggtabell, 0, nesteledig);
		}
		int left = 0;
		int right = nesteledig - 1;
		while (left <= right) {
			int mid = left + ((right - left) / 2);
			if (this.inleggtabell[mid].getId() == innlegg.getId()) {
				return mid;
			} else if (innlegg.getId() < this.inleggtabell[mid].getId()) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}



	public boolean finnes(Innlegg innlegg) {
		return finnInnlegg(innlegg) != -1;
	}

	public boolean ledigPlass() {
		return nesteledig < this.inleggtabell.length;
	}
	
	public boolean leggTil(Innlegg innlegg) {
		if(!ledigPlass()){
			return false;
		}
		if(finnes(innlegg)){
			return false;
		}
		inleggtabell[nesteledig] = innlegg;
		nesteledig++;
		return true;
	}
	
	public String toString() {
		throw new UnsupportedOperationException(TODO.method());
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {
		throw new UnsupportedOperationException(TODO.method());
	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {

		throw new UnsupportedOperationException(TODO.method());
		
	}
	
	public boolean slett(Innlegg innlegg) {
		
		throw new UnsupportedOperationException(TODO.method());
	}
	
	public int[] search(String keyword) {
		
		throw new UnsupportedOperationException(TODO.method());

	}
}