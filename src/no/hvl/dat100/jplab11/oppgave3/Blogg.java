package no.hvl.dat100.jplab11.oppgave3;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;
import no.hvl.dat100.jplab11.oppgave2.*;

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

	//recursive quick sort algorithm to sort an array for our list
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

	//help method for the quicksort
	private void swap(Innlegg[] array, int index1, int index2){
		Innlegg temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;

	}

	//binary search for for id
	public int finnInnlegg(Innlegg innlegg) {
		if (nesteledig > 2) {
			quickSort(this.inleggtabell, 0, nesteledig - 1);
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

	@Override
	public String toString() {
		StringBuilder strMaker = new StringBuilder();
		strMaker.append(nesteledig).append("\n");
		for(int i = 0; i < nesteledig; i++){
			strMaker.append(this.inleggtabell[i].toString());
		}
		return strMaker.toString();
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {
		Innlegg[] nytabell = new Innlegg[inleggtabell.length*2];

		for(int i = 0; i < nesteledig && i < inleggtabell.length; i++){
			nytabell[i] = inleggtabell[i];
		}
		inleggtabell = nytabell;
		quickSort(inleggtabell, 0, nesteledig-1);
	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {
		if(finnes(innlegg)){
			return false;
		}
		if(ledigPlass()){
			inleggtabell[nesteledig] = innlegg;
			return true;
		}
		else{
			utvid();
			inleggtabell[nesteledig] = innlegg;
			return true;
		}

		
	}
	
	public boolean slett(Innlegg innlegg) {

		int pos = finnInnlegg(innlegg);
		if( pos == -1){
			return false;
		}
		inleggtabell[pos] = inleggtabell[nesteledig-1];
		inleggtabell[nesteledig - 1] = null;
		nesteledig--;
		return true;

	}
	
	public int[] search(String keyword) {

		int[] matches = new int[nesteledig];


		for (int i = 0; i < nesteledig; i++) {

			String tekst = ((Tekst) inleggtabell[i]).getTekst();

			if (tekst.contains(keyword)) {
				matches[i] = inleggtabell[i].getId();
				}

		}
		return matches;

	}
}
