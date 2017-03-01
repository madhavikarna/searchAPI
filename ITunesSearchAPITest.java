package testautomation;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class ITunesSearchAPITest{


	private String BASE_URL="https://itunes.apple.com/search?";
	/*
	 * Test case where input parameters are empty
	 */
	@Test
	public void testSearchByNoParameter() throws ClientProtocolException, IOException{
		List<NameValuePair> params = new ArrayList<>();
				final HttpUriRequest request = new HttpGet(BASE_URL + URLEncodedUtils.format(params, Consts.UTF_8));
				final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				assertEquals(200, httpResponse.getStatusLine().getStatusCode());
				String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());				
				JSONObject object;
				try {
					object = new JSONObject(jsonFromResponse);
					assertEquals(0,object.get("resultCount"));				
				} catch (JSONException e) {
					e.printStackTrace();
				}
	}

	
	/*
	 * Test case where input parameter 'term' is valid
	 */
	@Test
	public void testSearchByTerm() throws ClientProtocolException, IOException{
		List<NameValuePair> params = new ArrayList<>();
				params.add(new BasicNameValuePair("term", "iTunes Test"));
				final HttpUriRequest request = new HttpGet(BASE_URL + URLEncodedUtils.format(params, Consts.UTF_8));
				final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				assertEquals(200, httpResponse.getStatusLine().getStatusCode());
				String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());				
				JSONObject object;
				try {
					object = new JSONObject(jsonFromResponse);
					assertEquals(2,object.get("resultCount"));				
				} catch (JSONException e) {
					e.printStackTrace();
				}
	}

	
	/*
	 * Test case where input parameter 'term' is invalid
	 */
	@Test
	public void testSearchByEmptyTerm() throws ClientProtocolException, IOException{
		List<NameValuePair> params = new ArrayList<>();
				params.add(new BasicNameValuePair("term", ""));
				final HttpUriRequest request = new HttpGet(BASE_URL + URLEncodedUtils.format(params, Consts.UTF_8));
				final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				assertEquals(200, httpResponse.getStatusLine().getStatusCode());
				String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());				
				JSONObject object;
				try {
					object = new JSONObject(jsonFromResponse);
					assertEquals(0,object.get("resultCount"));				
				} catch (JSONException e) {
					e.printStackTrace();
				}
	}
	
	/*
	 * Test case where input parameters 'term' and 'country ' are valid
	 */
	@Test
	public void testSearchByCountry() throws ClientProtocolException, IOException{
		List<NameValuePair> params = new ArrayList<>();
				params.add(new BasicNameValuePair("term", "iTunes Test"));
				params.add(new BasicNameValuePair("country", "US"));
				final HttpUriRequest request = new HttpGet(BASE_URL + URLEncodedUtils.format(params, Consts.UTF_8));
				final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				assertEquals(200, httpResponse.getStatusLine().getStatusCode());
				String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());				
				JSONObject object;
				try {
					object = new JSONObject(jsonFromResponse);
					assertEquals(2,object.get("resultCount"));				
				} catch (JSONException e) {
					e.printStackTrace();
				}
	}
	
	
	/*
	 * Test case where input parameters 'term' is valid and 'country' is invalid
	 */
	@Test
	public void testSearchByInvalidCountry() throws ClientProtocolException, IOException{
		List<NameValuePair> params = new ArrayList<>();
				params.add(new BasicNameValuePair("term", "iTunes Test"));
				params.add(new BasicNameValuePair("country", "ABCD"));
				final HttpUriRequest request = new HttpGet(BASE_URL + URLEncodedUtils.format(params, Consts.UTF_8));
				final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				assertEquals(400, httpResponse.getStatusLine().getStatusCode());
	}
	
	/*
	 * Test case where input parameters 'term', 'country' and 'media' are valid
	 */
	@Test
	public void testSearchByMedia() throws ClientProtocolException, IOException{
		List<NameValuePair> params = new ArrayList<>();
				params.add(new BasicNameValuePair("term", "iTunes Test"));
				params.add(new BasicNameValuePair("country", "US"));
				params.add(new BasicNameValuePair("media", "movie"));
				final HttpUriRequest request = new HttpGet(BASE_URL + URLEncodedUtils.format(params, Consts.UTF_8));
				final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				assertEquals(200, httpResponse.getStatusLine().getStatusCode());
				String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());				
				JSONObject object;
				try {
					object = new JSONObject(jsonFromResponse);
					assertEquals(0,object.get("resultCount"));				
				} catch (JSONException e) {
					e.printStackTrace();
				}
	}
	
	/*
	 * Test case where input parameters 'term', 'country' are valid and 'media' is invalid
	 */
	@Test
	public void testSearchByInvalidMedia() throws ClientProtocolException, IOException{
		List<NameValuePair> params = new ArrayList<>();
				params.add(new BasicNameValuePair("term", "iTunes Test"));
				params.add(new BasicNameValuePair("country", "US"));
				params.add(new BasicNameValuePair("media", "invalidmedia"));
				final HttpUriRequest request = new HttpGet(BASE_URL + URLEncodedUtils.format(params, Consts.UTF_8));
				final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				assertEquals(400, httpResponse.getStatusLine().getStatusCode());
	
	}
	
	
	
	/*
	 * Test case where input parameter 'limit' is valid
	 */
	@Test
	public void testSearchByLimit() throws ClientProtocolException, IOException{
		List<NameValuePair> params = new ArrayList<>();
				params.add(new BasicNameValuePair("term", "iTunes"));
				params.add(new BasicNameValuePair("country", "US"));
				params.add(new BasicNameValuePair("media", "all"));
				params.add(new BasicNameValuePair("limit", "100"));
				final HttpUriRequest request = new HttpGet(BASE_URL + URLEncodedUtils.format(params, Consts.UTF_8));
				final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				assertEquals(200, httpResponse.getStatusLine().getStatusCode());
				String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());				
				JSONObject object;
				try {
					object = new JSONObject(jsonFromResponse);
					assertEquals(100,object.get("resultCount"));				
				} catch (JSONException e) {
					e.printStackTrace();
				}
	
	}
	
	
	
	/*
	 * Test case for default limit value
	 */
	@Test
	public void testSearchByDefaultLimit() throws ClientProtocolException, IOException{
		List<NameValuePair> params = new ArrayList<>();
				params.add(new BasicNameValuePair("term", "iTunes"));
				params.add(new BasicNameValuePair("country", "US"));
				final HttpUriRequest request = new HttpGet(BASE_URL + URLEncodedUtils.format(params, Consts.UTF_8));
				final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				assertEquals(200, httpResponse.getStatusLine().getStatusCode());
				String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());				
				JSONObject object;
				try {
					object = new JSONObject(jsonFromResponse);
					assertEquals(50,object.get("resultCount"));				
				} catch (JSONException e) {
					e.printStackTrace();
				}
	
	}
	
	
	/*
	 * Test case where input parameter 'limit' is invalid
	 */
	@Test
	public void testSearchByInvalidLimit() throws ClientProtocolException, IOException{
		List<NameValuePair> params = new ArrayList<>();
				params.add(new BasicNameValuePair("term", "iTunes Test Limit"));
				params.add(new BasicNameValuePair("limit", "-10"));
				final HttpUriRequest request = new HttpGet(BASE_URL + URLEncodedUtils.format(params, Consts.UTF_8));
				final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				assertEquals(200, httpResponse.getStatusLine().getStatusCode());
				String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());				
				JSONObject object;
				try {
					object = new JSONObject(jsonFromResponse);
					assertEquals(0,object.get("resultCount"));				
				} catch (JSONException e) {
					e.printStackTrace();
				}
	
	}
}
