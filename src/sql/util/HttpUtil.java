package sql.util;

//import com.game.webapp.interceptor.InnerGameMasterInterceptor;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

/**
 * 
 * @author  
 * @2012-10-11 下午11:54:28
 */
public class HttpUtil {
    public final static int DEFAULT_BUFFER_SIZE = 1024;
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HttpUtil.class);

	/**
	 * 
	 * @param urladdress
	 *            http://www.baidu.com/s
	 * @param param
	 *            "aa=22&bb=33"
	 * @return
	 * @throws Exception
	 */
	public static boolean post(String urladdress, String param)
			throws Exception {
		HttpURLConnection uc = null;
		try {
			URL url = new URL(urladdress);

			uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);
			uc.setDoOutput(true);
			uc.setInstanceFollowRedirects(true); // 不允许重定向
			uc.setRequestMethod("POST");
			uc.setConnectTimeout(5000); // 五秒连接超时
			uc.setReadTimeout(5000); // 5秒返回超时
			uc.getOutputStream().write(param.getBytes());
			uc.connect();
			int t = uc.getResponseCode();
			System.out.println("发送到" + urladdress + "\r\n resultcode=" + t);
			// if(logger.isDebugEnabled()){
			try {
				String responseMessage = uc.getResponseMessage();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(uc.getInputStream()));
				String lines = "";
				while (reader.ready()) {
					lines += reader.readLine();
				}
				reader.close();
				System.out.println("返回值" + t + " " + responseMessage + " \n" + lines);
			} catch (Exception e) {
				logger.error(e, e);
			}
			// }
			return true;

		} catch (Exception e) {
			logger.error("异常" + urladdress, e);
			if (e instanceof ConnectException) {
				logger.error(e);
			} else {
				logger.error(e, e);
			}
		} finally {
			if (uc != null && uc.getInputStream() != null) {
				// uc.disconnect();//释放资源，并有可能影响到持久连接
				uc.getInputStream().close();// 只释放实例资源，不会影响持久连接
			}
		}
		return false;
	}

	/**
	 * @example http://www.baidu.com/s?wd=秦美人&a2=33
	 * @param urladdress
	 * @return
	 * @throws Exception
	 */
	public static boolean wget(String urladdress) throws Exception {
		HttpURLConnection uc = null;
		try {
			URL url = new URL(urladdress);
			uc = (HttpURLConnection) url.openConnection();
			uc.setInstanceFollowRedirects(false); // 不允许重定向
			uc.setRequestMethod("GET");
			uc.setConnectTimeout(5000); // 10秒超时
			uc.setReadTimeout(5000); // 10秒超时
			uc.connect();
			int t = uc.getResponseCode();
			System.out.println("发送日志" + urladdress);
			if (logger.isDebugEnabled()) {
				String responseMessage = uc.getResponseMessage();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(uc.getInputStream()));
				String lines = "";
				while (reader.ready()) {
					lines += reader.readLine();
				}
				reader.close();
				System.out.println("返回信息" + t + " " + responseMessage + " \n"
						+ lines);
			}
			return true;
		} catch (Exception e) {
			System.out.println("发送日志出错" + urladdress);
			if (e instanceof ConnectException) {
				logger.error(e);
			} else {
				logger.error(e, e);
			}
		} finally {
			if (uc != null && uc.getInputStream() != null) {
				// uc.disconnect();//释放资源，并有可能影响到持久连接
				uc.getInputStream().close();// 只释放实例资源，不会影响持久连接
			}
		}
		return false;
	}

	/**
	 *
	 * @param urladdress
	 *            http://www.baidu.com/s
	 * @param param
	 *            "aa=22&bb=33"
	 * @return
	 * @throws Exception
	 */
	public static String postr(String urladdress, String param)
			throws Exception {
		HttpURLConnection uc = null;
		try {
			URL url = new URL(urladdress);

			uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);
			uc.setDoOutput(true);
			uc.setInstanceFollowRedirects(true); // 不允许重定向
			uc.setRequestMethod("POST");
			uc.setConnectTimeout(5000); // 五秒连接超时
			uc.setReadTimeout(5000); // 5秒返回超时
			uc.getOutputStream().write(param.getBytes());
			uc.connect();
			int t = uc.getResponseCode();
			System.out.println("发送到" + urladdress + "\r\n resultcode=" + t);
			// if(logger.isDebugEnabled()){
			String lines = "";
			try {
				String responseMessage = uc.getResponseMessage();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(uc.getInputStream()));

				while (reader.ready()) {
					lines += reader.readLine();
				}
				reader.close();
				System.out.println("返回值" + t + " " + responseMessage + " \n" + lines);
			} catch (Exception e) {
				logger.error(e, e);
			}
			// }
			return lines;

		} catch (Exception e) {
			logger.error("异常" + urladdress, e);
			if (e instanceof ConnectException) {
				logger.error(e);
			} else {
				logger.error(e, e);
			}
		} finally {
			if (uc != null && uc.getInputStream() != null) {
				// uc.disconnect();//释放资源，并有可能影响到持久连接
				uc.getInputStream().close();// 只释放实例资源，不会影响持久连接
			}
		}
		return "";
	}

    /**
     *
     * @param urladdress
     *            http://www.baidu.com
     * @return
     * @throws Exception
     */
    public static String getr(String urladdress)
            throws Exception {
        HttpURLConnection uc = null;
        try {
            URL url = new URL(urladdress);

            uc = (HttpURLConnection) url.openConnection();
            uc.setRequestMethod("GET");
            uc.setConnectTimeout(5000); // 五秒连接超时
            uc.setReadTimeout(5000); // 5秒返回超时
            uc.connect();
            int t = uc.getResponseCode();
            System.out.println("发送到" + urladdress + "\r\n resultcode=" + t);
            // if(logger.isDebugEnabled()){
            StringWriter lines = new StringWriter();
            try {
                String responseMessage = uc.getResponseMessage();
                InputStreamReader reader = new InputStreamReader(uc.getInputStream());

                char[] cbuf = new char[DEFAULT_BUFFER_SIZE];
                int n;
                while((n = reader.read(cbuf))!=-1) lines.write(cbuf, 0, n);
                System.out.println("返回值" + t + " " + responseMessage + " \n" + lines);
            } catch (Exception e) {
                logger.error(e, e);
            }
            // }
            return lines.toString();

        } catch (Exception e) {
            logger.error("异常" + urladdress, e);
            if (e instanceof ConnectException) {
                logger.error(e);
            } else {
                logger.error(e, e);
            }
        } finally {
            if (uc != null && uc.getInputStream() != null) {
                // uc.disconnect();//释放资源，并有可能影响到持久连接
                uc.getInputStream().close();// 只释放实例资源，不会影响持久连接
            }
        }
        return "";
    }

	public static void main(String[] args) {
		try {
			post("http://www.baidu.com/s", createUrlParam("wd", "秦美人"));
			wget("http://www.baidu.com/s?wd=秦美人&a2=33");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String createUrlParam(Object... param) {
		StringBuilder sb = new StringBuilder();
		boolean isfirst = true;
		try {
			if (param != null && param.length > 1) {
				for (int i = 0; i < param.length; i += 2) {
					if (param[i + 1] != null) {
						if (!isfirst) {
							sb.append('&');
						}
						sb.append(param[i]);
						sb.append('=');
						String value = param[i + 1].toString();
						value = java.net.URLEncoder.encode(value, "utf-8");
						sb.append(value);
						isfirst = false;
					}
				}
			}
		} catch (Exception ex) {
		}
		return sb.toString();
	}

//    public static String getSidFromRequest(HttpServletRequest request) {
//        return (String) request.getSession().getAttribute(InnerGameMasterInterceptor.KEY_SID);
//    }
//
//    public static String getSidFromSession(HttpSession session) {
//        return (String) session.getAttribute(InnerGameMasterInterceptor.KEY_SID);
//    }

}
