package slacklogger.implementation;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.mendix.core.Core;
import com.mendix.http.HttpHeader;
import com.mendix.http.HttpMethod;
import com.mendix.http.HttpResponse;

public class SlackMessageSender {

	public String sendMessage(String token, String channel, String message) {
		String result = null;
		try {
			HttpHeader[] headers = new HttpHeader[2];
			headers[0] = new HttpHeader("Authorization", "Bearer " + token);
			headers[1] = new HttpHeader("Content-type", "application/json");
			String clippedmessage = (message.length() > 4000) ? message.substring(0, 4000) : message;
			String data = "{\"text\" : \"" + escapeJsonString(clippedmessage) + "\", \"channel\" : \"" + channel + "\"}";
			HttpResponse response = Core.http().executeHttpRequest(new URI("https://slack.com/api/chat.postMessage"),
					HttpMethod.POST, headers, new ByteArrayInputStream(data.getBytes("UTF-8")));
			int status = response.getStatusCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getContent(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			char[] b = new char[1024];
			int line;
			while (0 <= (line = reader.read(b))) {
				sb.append(b, 0, line);
			}
			if (status == 200) {
				String responceContent = sb.toString();
				if (responceContent.indexOf("\"ok\":false") > 0) {
					String error = responceContent.replaceAll(".*\"error\":\"", "");
					error = error.replaceAll("\",\".*$", "");
					result = "Failure : " + error;
				}
			} else {
				result = "Failure : " + status;
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			result = "Failure : " + sw.toString();
		}
		return result;
	}

	/*
	 *
	 */
	private static final byte BKSL = 0x5C;
	private static Map<Byte, byte[]> escdMap = new HashMap<Byte, byte[]>();
	static {
		escdMap.put((byte)0x08, new byte[] { BKSL, 0x62 }); /* \\b */
		escdMap.put((byte)0x09, new byte[] { BKSL, 0x74 }); /* \\t */
		escdMap.put((byte)0x0A, new byte[] { BKSL, 0x6E }); /* \\n */
		escdMap.put((byte)0x0C, new byte[] { BKSL, 0x66 }); /* \\f */
		escdMap.put((byte)0x0D, new byte[] { BKSL, 0x72 }); /* \\r */
		escdMap.put((byte)0x22, new byte[] { BKSL, 0x22 }); /* \"  */
		escdMap.put((byte)0x2F, new byte[] { BKSL, 0x2F }); /* \/  */
		escdMap.put((byte)BKSL, new byte[] { BKSL, BKSL });/* \\  */
	}

	private static String escapeJsonString(String str) {
		try (ByteArrayOutputStream os = new ByteArrayOutputStream();) {
			byte[] bytestr = str.getBytes(StandardCharsets.UTF_8);
			for (byte b : bytestr) {
				byte[] escd = escdMap.get(b);
				if (escd != null) {
					os.write(escd);
				} else {
					os.write(b);
				}
			}
			return new String(os.toByteArray(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
