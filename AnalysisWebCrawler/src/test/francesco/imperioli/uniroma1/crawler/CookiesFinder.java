package test.francesco.imperioli.uniroma1.crawler;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class CookiesFinder {

	public static void findCookiesByUrl(String url) {
		
		System.out.println("----------------Inizio cookies per indirizzo:" + url + "-----------------------------");
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.useragent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
		
		GetMethod method = new GetMethod(url);
		try {
			client.executeMethod(method);
			Cookie[] cookies = client.getState().getCookies();
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				System.err.println("{Cookie: " + cookie.getName() + ", Value: " + cookie.getValue() + ", IsPersistent?: "
						+ cookie.isPersistent() + ", Expiry Date: " + cookie.getExpiryDate() + ", Comment: "
						+ cookie.getComment() + "}");
				//cookie.setValue("My own value");
			}
			client.executeMethod(method);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			method.releaseConnection();
		}
		System.out.println("----------------Fine cookies per indirizzo:" + url + "-----------------------------");
	}

}
