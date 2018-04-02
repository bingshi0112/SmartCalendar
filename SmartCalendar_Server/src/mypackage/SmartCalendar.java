package mypackage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class SmartCalendar {
	
	//the main function is for testing.
	public static void main(String[] args) {
		SmartCalendar smartCalendar = new SmartCalendar();
		
		Connection conn = smartCalendar.connect();
		
		/*
		 * this is to test the method getUserCalendar(...)
		 */
//		String testUserCalendar = getUserCalendar("smartcalendartestuser@gmail.com", conn).toString();
//		System.out.println(testUserCalendar);
		
		/*
		 * this is to test the method getSimpleUserCalendar(...)
		 */
		String getSimpleUserCalendar = getSimpleUserCalendar("smartcalendartestuser@gmail.com", conn).toString();
		System.out.println(getSimpleUserCalendar);
		
		
		/*
		 * this is to test the method getUserCalendarInTimePeriod(...)
		 */
//		String testUserInfo = getUserCalendarInTimePeriod("smartcalendartestuser@gmail.com", "2016-10-20 00:00:00.0000", "2016-11-01 00:00:00.0000", conn).toString();
//		System.out.println(testUserInfo);
		
		/*
		 * this is to test the method getUserCalendarBeforeTime(...)
		 */
//		String testUserInfo = getUserCalendarBeforeTime("smartcalendartestuser@gmail.com", "2016-11-01 00:00:00.0000", conn).toString();
//		System.out.println(testUserInfo);
		
		
		/*
		 * this is to test the method getStartTime(...)
		 */
//		String startTime = getStartTime("smartcalendartestuser@gmail.com", "Movie Night", conn).toString();
//		System.out.println(startTime);
		
		
		/*
		 * this is to test the method getEndTime(...)
		 */
//		String endTime = getEndTime("smartcalendartestuser@gmail.com", "Movie Night", conn).toString();
//		System.out.println(endTime);
		
		/*
		 * this is to test the method getRepeatEndTime(...)
		 */
//		String repeatEndTime = getRepeatEndTime("smartcalendartestuser@gmail.com", "Movie Night", conn).toString();
//		System.out.println(repeatEndTime);
		
		/*
		 * this is to test the method getTravelTime(...)
		 */
//		String travelTime = getTravelTime("smartcalendartestuser@gmail.com", "Movie Night", conn).toString();
//		System.out.println(travelTime + " minutes.");
		
		
		/*
		 * this is to test the method getOriginalLocation(...)
		 */
//		String location = getOriginalLocation("smartcalendartestuser@gmail.com", "Movie Night", conn);
//		System.out.println(location);
		
		
		/*
		 * this is to test the method getEventLocation(...)
		 */
//		String location = getEventLocation("smartcalendartestuser@gmail.com", "Movie Night", conn);
//		System.out.println(location);
		
		
		/*
		 * this is to test the method getEventRepeat(...)
		 */
//		String eventRepeat = getEventRepeat("smartcalendartestuser@gmail.com", "Halloween Day", conn);
//		System.out.println(eventRepeat);
		
		
		/*
		 * this is to test the method ifAllDayEvent(...)
		 */
//		boolean isAllDayEvent = ifAllDayEvent("smartcalendartestuser@gmail.com", "Movie Night", conn);
//		String allDayEvent = (isAllDayEvent) ? "true" : "false";
//		System.out.println(allDayEvent);
		
		
		/*
		 * this is to test the method ifShouldTrafficCheck(...)
		 */
//		boolean shouldCheck = ifShouldTrafficCheck("smartcalendartestuser@gmail.com", "Halloween Day", conn);
//		String check = (shouldCheck) ? "true" : "false";
//		System.out.println(check);
		
		/*
		 * this is to test the method ifShouldAlert(...)
		 */
//		boolean shouldAlert = ifShouldAlert("smartcalendartestuser@gmail.com", "Movie Night", conn);
//		String alert = (shouldAlert) ? "true" : "false";
//		System.out.println(alert);
			
		
		/**
		 * {
	 *  	eventName: 		...,   	//String, 											Not Null
	 *  	allDayEvent:	..., 	//int, 												Not Null
	 *  	startTime:		..., 	//String in format "YYYY-MM-DD HH:MI:SS.0000", 		Not Null
	 *  	endTime:		..., 	//String in format "YYYY-MM-DD HH:MI:SS.0000", 		Not Null
	 *  	eventRepeat:	..., 	//String ("None", "Weekly", "Monthly", "Yearly"), 	Not Null
	 *  	repeatEndTime:	..., 	//String in format "YYYY-MM-DD HH:MI:SS.0000"
	 *  	travelTime:		...,	//int (travel time in minutes),						Not Null
	 *  	location: 		..., 	//String
	 *  	alert:			..., 	//int,												Not Null
	 *  	trafficCheck:	..., 	//int, 												Not Null
	 *  	description:	...,	//String
	 *  	originalLocation: ...	//String
	 * }
		 */
		
		/*
		 * this is to test the method postFullUserCalendar(String userID, JSONObject userEvent, Connection conn)
		 */
//		JSONObject exampleJson = new JSONObject();
//		try {
//			exampleJson.put("eventName", "exampleEventName");
//			exampleJson.put("allDayEvent", 0);
//			exampleJson.put("startTime", "1990-03-13 05:30:00.0000");
//			exampleJson.put("endTime", "1990-03-13 08:30:00.0000");
//			exampleJson.put("eventRepeat", "Yearly");
//			exampleJson.put("repeatEndTime", "2070-03-13 08:30:00.0000");
//			exampleJson.put("travelTime", 10);
//			exampleJson.put("location", "New York, NY,USA");
//			exampleJson.put("alert", 0);
//			exampleJson.put("trafficCheck", 0);
//			exampleJson.put("description", "exmaple description");
//			exampleJson.put("originalLocation", "Washington,DC,USA");
//		} catch (JSONException e) {
//			System.out.println("JSONException: " + e.getMessage());
//			e.printStackTrace();
//		}
//		postFullUserCalendar("smartcalendartestuser@gmail.com", exampleJson, conn);
		
		
		
//		String testUserInfo = getUserCalendarAfterTime("smartcalendartestuser@gmail.com", "2016-11-01 00:00:00.0000", conn).toString();
//		System.out.println(testUserInfo);
	
		
		//Following disconnect our program to the database.  
		/*******MUST DELETE BEFORE SUBMITTING, WAIT UNTIL GRADED********/
		try{
			conn.close();
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}
		/*******MUST DELETE BEFORE SUBMITTING, WAIT UNTIL GRADED********/
	}


	/**
	 * this method connects to our database for post/get information to/from database
	 * @return		Connection
	 */
	public Connection connect() {
		
		String url = "jdbc:mysql://cmpe281projectdatabase.c2cfw1gzdszr.us-west-2.rds.amazonaws.com:3306/cmpe281projectdatabase";  
		String userName = "SmartCalendar";
		String password = "CMPE281Project";
		String driver = "com.mysql.jdbc.Driver";
		Connection connection = null;
		try {
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Connected to the database.");
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		} catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		return connection;
	}
	
	/**
	 * Given the userID, this method return all events information (after current time) in a JSONObject in format:
	 * {
	 * 		"events" : [
	 * 				{
	 * 					"eventName": 		"Halloween Day",   	//String, 									Not Null
	 *  				"startTime":		"0:00:00.0000", 	//String in format "HH:MI:SS.0000", 		Not Null
	 *  				"date":				"10/31/2016"		//String in format "YYYY-MM-DD",			Not Null
	 * 				},
	 * 				{
	 * 					...
	 * 				},
	 * 				...
	 * 		]
	 * }
	 * 
	 * @param userID
	 * @param conn
	 * @return
	 */
	public static JSONObject getSimpleUserCalendar(String userID, Connection conn) {
		
		String curTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0000").format(Calendar.getInstance().getTime());
		
		return getUserCalendarAfterTime(userID, curTime, conn);
	}
	
	
	
	/**
	 * Given the userID, this method return all events (after current time) information in a JSONObject in format:
	 * {
	 * 		"events" : [
	 * 				{
	 * 					"eventName": 	"Halloween Day",   			//String, 											Not Null
	 *  				"allDayEvent":	1, 							//int, 												Not Null
	 *  				"startTime":	"10/31/2016 0:00:00.0000", 	//String in format "YYYY-MM-DD HH:MI:SS.0000", 		Not Null
	 *  				"endTime":		"07/6/2016 23:59:59.0000", 	//String in format "YYYY-MM-DD HH:MI:SS.0000", 		Not Null
	 *  				"eventRepeat":	"Yearly", 					//String ("None", "Weekly", "Monthly", "Yearly"), 	Not Null
	 *  				"repeatEndTime":"12/31/2050 23:59:59.0000", 	//String in format "YYYY-MM-DD HH:MI:SS.0000"
	 *  				"travelTime":	0,							//int (travel time in minutes),						Not Null
	 *  				"location": 	None, 						//String
	 *  				"alert":		0, 							//int,												Not Null
	 *  				"trafficCheck":	0, 							//int, 												Not Null
	 *  				"description":	NULL						//String
	 *  				"originalLocation": "Washington, DC, USA"	//String							
	 * 				},
	 * 				{
	 * 					...
	 * 				},
	 * 				...
	 * 		]
	 * }
	 * 
	 * @param userID
	 * @param conn
	 * @return
	 */
	public static JSONObject getUserCalendar(String userID, Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		
		JSONObject userCalendar = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		
		try {
			String curTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0000").format(Calendar.getInstance().getTime());	
			String query = "SELECT * FROM `" + userID + "` WHERE startTime >= '" + curTime + "';";

			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				JSONObject jsonTmp = new JSONObject();
				jsonTmp.put("eventName", rs.getString(2));
				jsonTmp.put("allDayEvent", rs.getInt(3));		//this will return 1/0
//				jsonTmp.put("allDayEvent", rs.getObject(3));   	//this will return "true"/"false"
				jsonTmp.put("startTime", rs.getString(4));
				jsonTmp.put("endTime", rs.getString(5));
				jsonTmp.put("eventRepeat", rs.getString(6));
				jsonTmp.put("repeatEndTime", rs.getString(7));
				jsonTmp.put("travelTime", rs.getInt(8));
				jsonTmp.put("location", rs.getString(9));
				jsonTmp.put("alert", rs.getInt(10));
				jsonTmp.put("trafficCheck", rs.getInt(11));
				jsonTmp.put("description", rs.getString(12));
				jsonTmp.put("originalLocation", rs.getString(13));
				
				jsonArr.put(jsonTmp);
			}
			userCalendar.put("events", jsonArr);
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} catch (JSONException e) {
			System.out.println("JSONException: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return userCalendar;
	}
	
	
	/**
	 * This method get eventName and corresponding startTime from database of a certain user during a time period.
	 * 
	 * @param userID
	 * @param startTime				//String in format "YYYY-MM-DD HH:MI:SS.0000"
	 * @param endTime				//String in format "YYYY-MM-DD HH:MI:SS.0000"
	 * @param conn
	 * @return JSONObject formatted in "{event1: startTime1, event2: startTime2,event3: startTime3,...}"
	 */
	public static JSONObject getUserCalendarInTimePeriod(String userID, String startTime, String endTime, Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		JSONObject jsonResult = new JSONObject();

		try {
			String query = "SELECT eventName, startTime FROM `" + userID + "` WHERE startTime BETWEEN '" + startTime + "' and '" + endTime + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount();  //actually, columns == 2 cuz we select two cols in query
		
			while(rs.next()) {
				jsonResult.put(rs.getString(1), rs.getString(columns)); //note: actually, columns == 2 we already know
			}
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} catch (JSONException e) {
			System.out.println("JSONException: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}

		return jsonResult;
	}
	

	/**
	 * This method get eventName and corresponding startTime from database of a certain user before a certain time.
	 * 
	 * @param userID
	 * @param dueTime
	 * @param conn
	 * 
	 * @return String formatted in "event1: startTime1 
	 * 								event2: startTime2 
	 * 								event3: startTime3 
	 * 								..."
	 */
	public static JSONObject getUserCalendarBeforeTime(String userID, String dueTime, Connection conn) {
		
		Statement stmt = null;
		ResultSet rs = null;
		JSONObject jsonResult = new JSONObject();
		
		try {
			String query = "SELECT eventName, startTime FROM `" + userID + "` WHERE startTime <= '" + dueTime + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount(); //actually, columns == 2 cuz we select two cols in query
			
			while(rs.next()) {
				jsonResult.put(rs.getString(1), rs.getString(columns)); //note: actually, columns == 2 we already know
			}
						
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} catch (JSONException e) {
			System.out.println("JSONException: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return jsonResult;	
	}
	
	
	
	/**
	 * This method get eventName and corresponding startTime from database of a certain user after a certain time.
	 * 
	 * @param userID
	 * @param dueTime
	 * @param conn
	 * 
	 * @return String formatted in "event1: startTime1 
	 * 								event2: startTime2 
	 * 								event3: startTime3 
	 * 								..."
	 */
	public static JSONObject getUserCalendarAfterTime(String userID, String curTime, Connection conn) {
		
		Statement stmt = null;
		ResultSet rs = null;
		JSONObject jsonResult = new JSONObject();
		
		try {
			String query = "SELECT eventName, startTime FROM `" + userID + "` WHERE startTime >= '" + curTime + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount(); //actually, columns == 2 cuz we select two cols in query
			
			while(rs.next()) {
				jsonResult.put(rs.getString(1), rs.getString(columns)); //note: actually, columns == 2 we already know
			}
						
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} catch (JSONException e) {
			System.out.println("JSONException: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return jsonResult;	
	}
	
	
	/**
	 * Given an eventName, this function returns the startTime of the event.
	 * 
	 * @param userID
	 * @param eventName
	 * @param conn
	 * @return
	 */
	public static String getStartTime(String userID, String eventName, Connection conn) {
		String startTime = "";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT startTime FROM `" + userID + "` WHERE eventName = '" + eventName + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
				startTime = rs.getString(1);
					
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return startTime;	
	}
	
	
	
	/**
	 * Given an eventName, this function returns the endTime of the event.
	 * 
	 * @param userID
	 * @param eventName
	 * @param conn
	 * @return
	 */
	public static String getEndTime(String userID, String eventName, Connection conn) {
		String endTime = "";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT endTime FROM `" + userID + "` WHERE eventName = '" + eventName + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
				endTime = rs.getString(1);
					
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return endTime;	
	}
	
	
	/**
	 * Given an eventName, this function returns the repeatEndTime of the event.
	 * 
	 * @param userID
	 * @param eventName
	 * @param conn
	 * @return
	 */
	public static String getRepeatEndTime(String userID, String eventName, Connection conn) {
		String repeatEndTime = "";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT repeatEndTime FROM `" + userID + "` WHERE eventName = '" + eventName + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
				repeatEndTime = rs.getString(1);
					
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return repeatEndTime;	
	}
	
	
	/**
	 * Given an eventName, this function returns the travelTime of the event.
	 * 
	 * @param userID
	 * @param eventName
	 * @param conn
	 * @return
	 */
	public static String getTravelTime(String userID, String eventName, Connection conn) {
		String travelTime = "";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT travelTime FROM `" + userID + "` WHERE eventName = '" + eventName + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
				travelTime = rs.getString(1);
					
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return travelTime;	
	}
	
	
	/**
	 * Given an eventName, this function returns the originalLocation of the event.
	 * 
	 * @param userID
	 * @param eventName
	 * @param conn
	 * @return
	 */
	public static String getOriginalLocation(String userID, String eventName, Connection conn) {
		String originalLocation = "";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT originalLocation FROM `" + userID + "` WHERE eventName = '" + eventName + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
				originalLocation = rs.getString(1);
					
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return originalLocation;	
	}

	
	
	
	/**
	 * Given an eventName, this function returns the location of the event.
	 * 
	 * @param userID
	 * @param eventName
	 * @param conn
	 * @return
	 */
	public static String getEventLocation(String userID, String eventName, Connection conn) {
		String location = "";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT location FROM `" + userID + "` WHERE eventName = '" + eventName + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
				location = rs.getString(1);
					
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return location;	
	}
	
	/**
	 * Given an eventName, this function returns the eventRepeat (the frequency) of the event, i.e. "None", "Weekly", "Monthly", or "Yearly"
	 * 
	 * @param userID
	 * @param eventName
	 * @param conn
	 * @return
	 */
	public static String getEventRepeat(String userID, String eventName, Connection conn) {
		String eventRepeat = "";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT eventRepeat FROM `" + userID + "` WHERE eventName = '" + eventName + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
				eventRepeat = rs.getString(1);
					
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return eventRepeat;	
	}
	
	/**
	 * Given an eventName, this function checks if this event is an allDayEvent.
	 * 
	 * @param userID
	 * @param eventName
	 * @param conn
	 * @return
	 */
	public static boolean ifAllDayEvent(String userID, String eventName, Connection conn) {
		boolean isAllDayEvent = false;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT allDayEvent FROM `" + userID + "` WHERE eventName = '" + eventName + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			int allDayEventValue = -1;
			while(rs.next())
				allDayEventValue = rs.getInt(1);
			
			isAllDayEvent = (allDayEventValue == 1) ? true : false;
					
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return isAllDayEvent;	
	}
	
	
	/**
	 * Given an eventName, this function checks if traffic information should be checked for this event.
	 * 
	 * @param userID
	 * @param eventName
	 * @param conn
	 * @return
	 */
	public static boolean ifShouldTrafficCheck(String userID, String eventName, Connection conn) {
		boolean shouldCheck = false;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT trafficCheck FROM `" + userID + "` WHERE eventName = '" + eventName + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			int trafficCheckValue = -1;
			while(rs.next())
				trafficCheckValue = rs.getInt(1);
			
			shouldCheck = (trafficCheckValue == 1) ? true : false;
					
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return shouldCheck;	
	}
	
	
	/**
	 * Given an eventName, this function checks if we need to give alerts for this event under some situation, i.e., weather/traffic changes.
	 * 
	 * @param userID
	 * @param eventName
	 * @param conn
	 * @return
	 */
	public static boolean ifShouldAlert(String userID, String eventName, Connection conn) {
		boolean shouldAlert = false;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT alert FROM `" + userID + "` WHERE eventName = '" + eventName + "';"; 
			System.out.println(query);  //for debug
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			int alertValue = -1;
			while(rs.next())
				alertValue = rs.getInt(1);
			
			shouldAlert = (alertValue == 1) ? true : false;
					
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
				
				rs = null;
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		return shouldAlert;	
	}

	
	
	/**
	 * This method put an event information of a certain user into the (corresponding table of) database.
	 * The event information is provided in a JSONObject.
	 * The userID is also included in the JSONObject in key-value pair.
	 * 
	 * @param userID
	 * @param userEvent in format:
	 * {
	 *  	eventName: 		...,   	//String, 											Not Null
	 *  	allDayEvent:	..., 	//int, 												Not Null
	 *  	startTime:		..., 	//String in format "YYYY-MM-DD HH:MI:SS.0000", 		Not Null
	 *  	endTime:		..., 	//String in format "YYYY-MM-DD HH:MI:SS.0000", 		Not Null
	 *  	eventRepeat:	..., 	//String ("None", "Weekly", "Monthly", "Yearly"), 	Not Null
	 *  	repeatEndTime:	..., 	//String in format "YYYY-MM-DD HH:MI:SS.0000"
	 *  	travelTime:		...,	//int (travel time in minutes),						Not Null
	 *  	location: 		..., 	//String
	 *  	alert:			..., 	//int,												Not Null
	 *  	trafficCheck:	..., 	//int, 												Not Null
	 *  	description:	...,	//String
	 *  	originalLocation: ...	//String
	 * }
	 * 
	 * @param conn
	 */
	public static void postFullUserCalendar(String userID, JSONObject userEvent, Connection conn) {
		
		Statement stmt = null;
				
		try {
			String eventName = userEvent.getString("eventName");
			int allDayEvent = userEvent.getInt("allDayEvent");
			String startTime = userEvent.getString("startTime");
			String endTime = userEvent.getString("endTime");
			String eventRepeat = userEvent.getString("eventRepeat");
			String repeatEndTime = userEvent.getString("repeatEndTime");  	//nullable
			int travelTime = userEvent.getInt("travelTime");
			String location = userEvent.getString("location");				//nullable
			int alert = userEvent.getInt("alert");
			int trafficCheck = userEvent.getInt("trafficCheck");
			String description = userEvent.getString("description");		//nullable
			String originalLocation = userEvent.getString("originalLocation"); //nullable
			
			String query = "INSERT INTO cmpe281projectdatabase.`" + userID + "`(eventName, allDayEvent, startTime, endTime, eventRepeat, repeatEndTime, travelTime, location, alert, trafficCheck, description, originalLocation) "
					+ " VALUES ('" + eventName + "', '" + allDayEvent + "', '" + startTime + "',"
					+ " '" + endTime + "', '" + eventRepeat + "', '" + repeatEndTime + "', '" + travelTime + "', '" + location + "', '" + alert +  "', '" + trafficCheck + "', '" + description + "', '" + originalLocation + "');";
			
			System.out.println(query);  //for debug
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
		} catch (JSONException e) {
			System.out.println("JSONException: " + e.getMessage());
			e.printStackTrace();
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
	}
	

}