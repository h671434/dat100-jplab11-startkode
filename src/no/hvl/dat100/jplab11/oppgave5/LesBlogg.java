package no.hvl.dat100.jplab11.oppgave5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;
import no.hvl.dat100.jplab11.oppgave2.*;
import no.hvl.dat100.jplab11.oppgave3.*;

import javax.swing.JOptionPane;

public class LesBlogg {

	private static String TEKST = "TEKST";
	private static String BILDE = "BILDE";

	public static Blogg les(String mappe, String filnavn) {

		Blogg samling = null;
		Scanner leser = null;
		
		try {		
			
			File innfil = new File(mappe + filnavn);
			leser = new Scanner(innfil);
			
			int antall = Integer.parseInt(leser.nextLine());
			
			samling = new Blogg (antall);
					
			for (int i = 0; i < antall; i++) {
				Innlegg innlegg = null;
				
				String type = leser.nextLine();		
				int id = Integer.parseInt(leser.nextLine());
				String bruker = leser.nextLine();
				String dato = leser.nextLine();
				int likes = Integer.parseInt(leser.nextLine());
				String tekst = leser.nextLine();
				
				if (type.equals(TEKST)) {
					innlegg = new Tekst(id, bruker, dato, likes, tekst);	
				}				
				if	(type.equals(BILDE)) {
					String url = leser.nextLine();
					innlegg = new Bilde(id, bruker, dato, likes, tekst, url);
				}
				
				if (!samling.ledigPlass()) {
					samling.leggTil(innlegg);
				} else {
					samling.leggTilUtvid(innlegg);
				}
			}	
			
		} catch (FileNotFoundException e) {		
			System.out.println("Finner ikke fil");
		} catch (NumberFormatException e) {
			System.out.println("Feil ved parsing til int");
		}

		leser.close();
		
		return samling;

	}
}
