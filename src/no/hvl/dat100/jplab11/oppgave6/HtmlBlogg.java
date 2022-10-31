package no.hvl.dat100.jplab11.oppgave6;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.Innlegg;
import no.hvl.dat100.jplab11.oppgave2.Tekst;
import no.hvl.dat100.jplab11.oppgave2.Bilde;
import no.hvl.dat100.jplab11.oppgave3.Blogg;

public class HtmlBlogg extends Blogg {

	public HtmlBlogg() {
		super();
	}
	
	private static String HTMLPREFIX = 
			"<html>\n\t<head>\n\t\t<title>DAT100 Blogg</title>\n\t</head>\n\t<body>\n";
	
	private static String HTMLPOSTFIX = 
			"\t</body>\n</html>";
	
	@Override
	public String toString() {
		String html = HTMLPREFIX;
		
		Innlegg[] samling = super.getSamling();
		
		for(int i = 0; i < super.getAntall(); i++) {
			int id = samling[i].getId();
			String bruker = samling[i].getBruker();
			String dato = samling[i].getDato();
			String tekst = ((Tekst) samling[i]).getTekst();

			html += "<h2>" + bruker + "@" + dato + " [" + id + "]</h2>\n"
					 +"<p>" + tekst + "</p>\n";	
			
			try {
				String url = ((Bilde)samling[i]).getUrl();
				html += "<iframe src=" + url + " height=600 width=800></iframe>\n";
			} catch (Exception e) {
				System.out.println("Innlegg " + i + "har ikke bilde");
			}			
			
			html += "<hr>\n";
		}
		
		html += HTMLPOSTFIX;
		
		return html;
	}
}
