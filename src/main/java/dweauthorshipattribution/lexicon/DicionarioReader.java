/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dweauthorshipattribution.lexicon;

/**
 *
 * @author daniel
 */
//package br.ufpb.compiladores.projetofinal.negocio.lexico.dicionario;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

//import br.ufpb.compiladores.projetofinal.util.logger.ILogger;
//import br.ufpb.compiladores.projetofinal.util.logger.LoggerFactory;

public class DicionarioReader {
	public static final String LANGUAGE = "pt";
	private String url;
	private final DicionarioExceptionHandler handler = new DicionarioExceptionHandler();
//	private static final ILogger LOGGER = LoggerFactory.getLogger(DicionarioReader.class);

public DicionarioReader(String url) {
	this.url = url;
}

	public String searchByGet(String word) {
		String entityString = "";
		String result = "";
		try {
			url = "http://www.priberam.pt/dlpo/default.aspx?pal=";
			URI uri = new URI(url + word);
			HttpGet get = new HttpGet(uri);
			HttpClient httpclient = HttpClients.createDefault();
			System.out.printf("##Consultando '%s'. Palavra pesquisada: %s", url, word);
			HttpEntity response = httpclient.execute(get).getEntity();
			if (response != null) {
				result = EntityUtils.toString(response);
		    	entityString = result.substring(result.indexOf("<em>") + 3, result.indexOf("</em>"));
			} else {
				throw new IOException("Couldn't read " + url);
			}
		} catch(Exception e) {
			if (result.contains("pess.") || result.contains("gerúndio") || result.contains("part.")) {
				entityString = " verbo ";
			} else {
                            if(word.endsWith("s")){
                                word = word.substring(0, word.length()-1);
                                System.out.println("PALAVRA : "+word);
                                searchByGet(word);
                            }
                            else if(word.endsWith("a")){
                                word = word.substring(0, word.length()-1);
                                word = word+"o";
                                System.out.println("PALAVRA : "+word);
                                
                                searchByGet(word);
                            }
                            else if(word.endsWith("mente")){
                                entityString = "adv";
                            }
                            else{
                                entityString = " desconhecido ";
                            }
//				entityString = " desconhecido ";
//                                entityString = JOptionPane.showInputDialog("Palavra "+ word+" não encontrada no dicionário, informe a classe gramatical correta :");
			}
		}
		return entityString;
	}
//
//	public String searchByPost(String word) {
//		String entityString = "";
//
//		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//		formParams.add(new BasicNameValuePair("palavra", word));
//		formParams.add(new BasicNameValuePair("lang", LANGUAGE));
//
//		HttpPost post = new HttpPost(url);
//		UrlEncodedFormEntity entity;
//
//		try {
//			entity = new UrlEncodedFormEntity(formParams, "UTF-8");
//			post.setEntity(entity);
//			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
//				post.setHeader("User-Agent", "(Windows; U; Windows NT 6.0; pt-BR; rv:1.9.2.4) Gecko/20100513 Firefox/3.6.4");
//			} else {
//				post.setHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; pt-BR; rv:1.9.2.3) Gecko/20100423 Ubuntu/10.04 (lucid) Firefox/3.6.");
//			}
//			post.setHeader("Accept-Language", "pt-br,pt;q=0.8,en-us;q=0.5,en;q=0.3");
//			post.setHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
//			post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//			post.setHeader("Accept-Encoding", "gzip,deflate");
//			post.setHeader("Keep-Alive", "115");
//			post.setHeader("Connection", "keep-alive");
//			post.setHeader("X-Requested-With", "XMLHttpRequest");
//
//			HttpClient httpclient = new DefaultHttpClient();
//
//			LOGGER.customInfo("Consultando '%s'. Palavra pesquisada: %s", url, word);
//			HttpEntity response = httpclient.execute(post).getEntity();
//			if (response != null) {
//		    	entityString = EntityUtils.toString(response);
//			} else
//				throw new IOException("Couldn't read "+url);
//		} catch (Exception e) {
//			handler.handle(e);
//		}
//
//		return entityString;
//	}
}
