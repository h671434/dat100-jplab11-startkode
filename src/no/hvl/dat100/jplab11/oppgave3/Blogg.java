package no.hvl.dat100.jplab11.oppgave3;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;
import no.hvl.dat100.jplab11.oppgave2.*;

public class Blogg {
	
	private Innlegg[] innleggtabell;
	private int nesteledig;

	public Blogg() {
		innleggtabell = new Innlegg[20];
		nesteledig = 0;
	}

	public Blogg(int lengde) {
		innleggtabell = new Innlegg[lengde];
		nesteledig = 0;
	}

	public int getAntall() {
		return nesteledig;
	}
	
	public Innlegg[] getSamling() {
		return innleggtabell;
	}
	
	public int finnInnlegg(Innlegg innlegg) {
		int i = nesteledig - 1;
		boolean funnet = false;
		
		while (i >= 0 && !funnet) {
			funnet = innlegg.erLik(innleggtabell[i]);
			if (!funnet)
				i--;
		}
		
		return i;
	}

	public boolean finnes(Innlegg innlegg) {
		return finnInnlegg(innlegg) >= 0;
	}

	public boolean ledigPlass() {
		return nesteledig < innleggtabell.length;
	}
	
	public boolean leggTil(Innlegg innlegg) {
		boolean lagttil = false;
		
		if (ledigPlass()) {
			innleggtabell[nesteledig] = innlegg;
			nesteledig += 1;
			lagttil = true;
		}
			
		return lagttil;	
	}
	
	public String toString() {
		int lengde = nesteledig;
		String innleggstr = lengde + "\n";
		
		for (int i = 0; i < lengde; i++)
			innleggstr += innleggtabell[i].toString();
		
		return innleggstr;
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {
		int lengde = innleggtabell.length;
		Innlegg[] ref = new Innlegg[lengde * 2];
		
		for (int i = 0; i < lengde; i++)
			ref[i] = innleggtabell[i];
		
		innleggtabell = ref;
	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {
		boolean lagttil = false;
		
		if (!finnes(innlegg)) {
			if (!ledigPlass())
				utvid();
			
			lagttil = leggTil(innlegg);
		}
		
		return lagttil;
	}
	
	public boolean slett(Innlegg innlegg) {
		boolean slettet = false;
		int slettpos = finnInnlegg(innlegg);
		
		if (slettpos >= 0) {
			int lengde = innleggtabell.length - 1;
			Innlegg[] ref = new Innlegg[lengde];
				
			for (int i = 0; i < lengde; i++) {
				if (i < slettpos)
					ref[i] = innleggtabell[i];
				else
					ref[i] = innleggtabell[i + 1];
			}
			
			innleggtabell = ref;
			nesteledig--;
			slettet = true;
		}
		
		return slettet;
	}
	
	public int[] search(String keyword) {
		int antallres = 0;
		int[] resultat = new int[antallres];
		
		for (int i = 0; i < nesteledig - 1; i++) {	
			String tekst = ((Tekst) innleggtabell[i]).getTekst();
			
			if (tekst.contains(keyword)) {			
				antallres++;	
				int[] ref = new int[antallres];
				
				for (int j = 0; j < antallres - 1; j++) {
					ref[j] = resultat[j];
				}
				ref[antallres] = innleggtabell[i].getId();	
				
				resultat = ref;
			}
		}
		
		return resultat;
	}
}