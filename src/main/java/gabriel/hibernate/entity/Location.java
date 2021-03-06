package gabriel.hibernate.entity;

import java.io.Serializable;
//Refer http://www.tutorialspoint.com/hibernate/hibernate_annotations.htm
//http://www.codejava.net/frameworks/hibernate/hibernate-one-to-one-mapping-with-foreign-key-annotations-example

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//@Entity: is required for every model class.
//@Table: maps the class with the corresponding database table. If omitted, Hibernate will use the class name.
//@Column: maps the field with the corresponding table column. If omitted, Hibernate will infer the column name and type based on signatures of the getter/setter.
//@Id and @GeneratedValue: are used in conjunction for a field that maps to the primary key. The values for this field are auto generated.
//@Temporal: must be used with a java.util.Date field to specify the actual SQL type of the column.
//@OneToOne and @JoinColumn: are used together to specify a one-to-one association and the join column

@Entity
@Table(name = "location")
public class Location implements Serializable {
	
	private static final long serialVersionUID = -6668691617561812702L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="location_packet_id")
	int id;
	
	@Column(name="mLatitude")
	double mLatitude;
	
	@Column(name="mLongitude")
	double mLongitude;
	
	@Column(name="mAccuracy")
	double mAccuracy;
	
	@Column(name="mSpeed")
	double mSpeed;
	
	@Column(name="mDistance")
	double mDistance;
	
	@Column(name="mAltitude")
	double mAltitude;
	
	@Column(name="mTime")
	Date mTime;
	
	@Column(name="mBearing")
	double mBearing;
	
	@Column(name="packet_received_time")
	Date packetReceivedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getmLatitude() {
		return mLatitude;
	}

	public void setmLatitude(double mLatitude) {
		this.mLatitude = mLatitude;
	}

	public double getmLongitude() {
		return mLongitude;
	}

	public void setmLongitude(double mLongitude) {
		this.mLongitude = mLongitude;
	}

	public double getmAccuracy() {
		return mAccuracy;
	}

	public void setmAccuracy(double mAccuracy) {
		this.mAccuracy = mAccuracy;
	}

	public double getmSpeed() {
		return mSpeed;
	}

	public void setmSpeed(double mSpeed) {
		this.mSpeed = mSpeed;
	}

	public double getmDistance() {
		return mDistance;
	}

	public void setmDistance(double mDistance) {
		this.mDistance = mDistance;
	}

	public double getmAltitude() {
		return mAltitude;
	}

	public void setmAltitude(double mAltitude) {
		this.mAltitude = mAltitude;
	}

	@Temporal(TemporalType.DATE)		//@Temporal: must be used with a java.util.Date field to specify the actual SQL type of the column
	@Column(name="mTime")
	public Date getmTime() {
		return mTime;
	}

	public void setmTime(Date mTime) {
		this.mTime = mTime;
	}

	public double getmBearing() {
		return mBearing;
	}

	public void setmBearing(double mBearing) {
		this.mBearing = mBearing;
	}

	@Temporal(TemporalType.DATE)		//@Temporal: must be used with a java.util.Date field to specify the actual SQL type of the column
	@Column(name="packet_received_time")
	public Date getPacketReceivedTime() {
		return packetReceivedTime;
	}

	public void setPacketReceivedTime(Date packetReceivedTime) {
		this.packetReceivedTime = packetReceivedTime;
	}
	
	
}
