package gabriel.hibernate.dao;

import gabriel.hibernate.entity.Location;
import gabriel.utilities.HibernateUtil;
import gabriel.utilities.SystemUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.json.JSONArray;
import org.json.JSONObject;

public class LocationDao {

	public static boolean storeLocationPacket(double mLatitude, 
									double mLongitude, 
									double mAccuracy, 
									double mSpeed, 
									double mDistance, 
									double mAltitude, 
									long mTime, 
									double mBearing){
		
		Session session = HibernateUtil.getSessionAnnotationFactory().openSession();
		
		try {
			session.beginTransaction();
			Location location = new Location();
			location.setmAccuracy(mAccuracy);
			location.setmAltitude(mAltitude);
			location.setmBearing(mBearing);
			location.setmDistance(mDistance);
			location.setmLatitude(mLatitude);
			location.setmLongitude(mLongitude);
			location.setmSpeed(mSpeed);
			SimpleDateFormat parserSDF=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date mLocationTime = null;
			try {
				mLocationTime = parserSDF.parse(SystemUtils.convertMsSinceEpochToDate(mTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			location.setmTime(mLocationTime);
			location.setPacketReceivedTime(new Date());
			session.save(location);
			session.getTransaction().commit();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	public static JSONArray getLocationJson(int numOfRecords){
		
		JSONArray locationArray = new JSONArray();
		
		Session session = HibernateUtil.getSessionAnnotationFactory().openSession();
		session.beginTransaction();
		Criteria count = session.createCriteria(Location.class);
        count.setProjection(Projections.rowCount());
        long total = (long) count.uniqueResult();
        if(total < numOfRecords){
        	numOfRecords = (int) total;
        }
        Criteria criteria = session.createCriteria(Location.class);
        criteria.setFirstResult((int) (total - numOfRecords));
        criteria.setMaxResults(numOfRecords);

        List<Location> list = criteria.list();
        Iterator<Location> locations = list.iterator();
        while(locations.hasNext()){
        	Location location = locations.next();
        	JSONObject locationJson = new JSONObject();
        	locationJson.put("mLatitude", location.getmLatitude());
        	locationJson.put("mLongitude", location.getmLongitude());
        	locationJson.put("mAccuracy", location.getmAccuracy());
        	locationJson.put("mSpeed", location.getmSpeed());
        	locationJson.put("mTime", location.getmTime());
        	
        	locationArray.put(locationJson);
        }
        return locationArray;
	}
	
}
