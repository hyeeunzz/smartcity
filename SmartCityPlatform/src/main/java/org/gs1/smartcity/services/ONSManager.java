package org.gs1.smartcity.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.gs1.smartcity.util.DomainGenerator;
import org.gs1.smartcity.util.HttpDel;
import org.json.JSONException;
import org.json.JSONObject;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.Type;

public class ONSManager {

	private static final String PROPERTY_PATH = "smartcity.properties";

	private static String onsServiceUrl;	//ONS management
	private static String onsServerIP;		//ONS server IP address
	private static String username;			//admin user name
	private static String password;			//admin password

	private DomainGenerator domainGenerator;

	public ONSManager() {

		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(PROPERTY_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		onsServiceUrl = prop.getProperty("ons_service_url");
		onsServerIP = prop.getProperty("ons_server_ip");
		username = prop.getProperty("admin_username");
		password = prop.getProperty("admin_password");

		domainGenerator = new DomainGenerator();
	}

	public boolean register(String type, String id, String classUrl) {

		String domain = domainGenerator.generate(type, id);

		String token = onsLogin();
		if(token == null) {
			System.out.println("registration is failed(login fail)");
			return false;
		}

		boolean add = addDomain(domain, token);
		if(add == false) {
			System.out.println("registration is failed(domain add is failed)");
			return false;
		}

		add = addRecords(domain, token, classUrl, null);
		if(add == true) {
			System.out.println("registration is done");
			return true;
		} else {
			System.out.println("registration is failed(record add is failed)");
			return false;
		}

	}
	
	public boolean registerRdata(String type, String id, String classUrl, String serviceUrl) {
		
		String domain = domainGenerator.generate(type, id);

		String token = onsLogin();
		if(token == null) {
			System.out.println("registration is failed(login fail)");
			return false;
		}
		
		boolean add = addRecords(domain, token, classUrl, serviceUrl);
		if(add == true) {
			System.out.println("registration is done");
			return true;
		} else {
			System.out.println("registration is failed(record add is failed)");
			return false;
		}
	}

	public boolean delete(String type, String id) {

		String domain = domainGenerator.generate(type, id);

		String token = onsLogin();
		if(token == null) {
			System.out.println("deletion is failed(login fail)");
			return false;
		}

		boolean delete = deleteDomain(domain, token);
		if(delete == true) {
			System.out.println("deletion is done");
			return true;
		} else {
			System.out.println("deletion is failed(delted fail)");
			return false;
		}
	}


	public List<String> query(String type, String id, String classUrl) {

		List<String> urlList = new ArrayList<String>();

		String domain = domainGenerator.generate(type, id);

		List<String> res = new ArrayList<String>();
		Record[] result = null;

		try {
			Lookup lookup = new Lookup(domain, Type.NAPTR);
			lookup.setResolver(new SimpleResolver("52.69.212.96"));
			lookup.setCache(null);
			result = lookup.run();
			int code = lookup.getResult();
			if (code == Lookup.SUCCESSFUL) {
				for(Record r : result){
					res.add(r.rdataToString());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		for(String r : res){
			if(r.toLowerCase().contains(classUrl)){
				urlList.add(r.substring(r.lastIndexOf("!^.*$!") + 6, r.lastIndexOf("!")));
			}
		}

		return urlList;
	}

	private String onsLogin() {

		String queryUrl = onsServiceUrl + "oauth/token";

		HttpClient client = HttpClientBuilder.create().build();

		HttpPost postRequest = new HttpPost(queryUrl);

		String clientId = username.replace(".", "").replace("@","");
		byte[] message = null;
		try {
			message = (clientId + ":" + password).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String encoded = DatatypeConverter.printBase64Binary(message);

		String auth = "Basic " + encoded;

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("grant_type", "password"));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));

		postRequest.setHeader("Authorization", auth);
		postRequest.setHeader("Content-type", "application/x-www-form-urlencoded");
		try {
			postRequest.setEntity(new UrlEncodedFormEntity(params));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		HttpResponse response = null;
		try {
			response = client.execute(postRequest);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if(response.getStatusLine().getStatusCode() != 200) {
			System.out.println("login is failed: error code: " + response.getStatusLine().getStatusCode());
			return null;
		}

		String entity = null;
		try {
			entity = EntityUtils.toString(response.getEntity());
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			JSONObject jobj = new JSONObject(entity);

			String token = jobj.getString("access_token");
			System.out.println("login is done : " + token);
			return token;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		System.out.println("login is failed");

		return null;
	}

	private boolean addDomain(String domain, String token) {

		String queryUrl = onsServiceUrl + "company/" + username + "/server/" + onsServerIP + "/map";

		HttpClient client = HttpClientBuilder.create().build();

		HttpPost postRequest = new HttpPost(queryUrl);

		String auth = "Bearer " + token;

		String parameters = "\"domainname\":" + "\"" +  domain + "\"";

		postRequest.setHeader("Authorization", auth);
		postRequest.setHeader("Content-type", "application/json");
		try {
			postRequest.setEntity(new StringEntity("{ " + parameters + " }"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpResponse response = null;
		try {
			response = client.execute(postRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(response.getStatusLine().getStatusCode() != 200) {
			System.out.println("domain add is failed: error code: " + response.getStatusLine().getStatusCode());
			return false;
		} else {
			System.out.println("domain is added");
			return true;
		}
	}

	private boolean deleteDomain(String domain, String token) {

		String queryUrl = onsServiceUrl + "company/" + username + "/server/" + onsServerIP + "/unOwnerOf";

		HttpClient client = HttpClientBuilder.create().build();

		HttpDel delRequest = new HttpDel(queryUrl);

		String auth = "Bearer " + token;

		String parameters = "\"domainname\":" + "\"" +  domain + "\"";

		delRequest.setHeader("Authorization", auth);
		delRequest.setHeader("Content-type", "application/json");
		try {
			delRequest.setEntity(new StringEntity("{ " + parameters + " }"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpResponse response = null;
		try {
			response = client.execute(delRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(response.getStatusLine().getStatusCode() != 200) {
			System.out.println("domain delete is failed: error code: " + response.getStatusLine().getStatusCode());
			return false;
		} else {
			System.out.println("domain is deleted");
			return true;
		}
	}

	private boolean addRecords(String domain, String token, String classUrl, String serviceUrl) {

		String queryUrl = onsServiceUrl + "company/" + username + "/domain/" + domain + "/newRecords";

		HttpClient client = HttpClientBuilder.create().build();

		HttpPost postRequest = new HttpPost(queryUrl);

		String auth = "Bearer " + token;
		String parameters = null;
		if(serviceUrl != null) {
			String rdata = "0 0 \\\"U\\\" \\\"" + classUrl + "\\\" \\\"!^.*$!" + serviceUrl + "!\\\" .";
			parameters = "\"records\":[{\"id\":\"-1\",\"name\":\"" +  domain + "\",\"type\":\"NAPTR\",\"ttl\":\"0\",\"content\":\"" + rdata + "\"}]";
		} else {
			parameters = "\"records\":[{\"id\":\"-1\",\"name\":\"" +  domain + "\",\"type\":\"A\",\"ttl\":\"0\",\"content\":\"" + onsServerIP + "\"}]";
		}

		postRequest.setHeader("Authorization", auth);
		postRequest.setHeader("Content-type", "application/json");
		try {
			postRequest.setEntity(new StringEntity("{ " + parameters + " }"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpResponse response = null;
		try {
			response = client.execute(postRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(response.getStatusLine().getStatusCode() != 200) {
			System.out.println("record add is failed: error code: " + response.getStatusLine().getStatusCode());
			return false;
		} else {
			System.out.println("records are added");
			return true;
		}
	}

}