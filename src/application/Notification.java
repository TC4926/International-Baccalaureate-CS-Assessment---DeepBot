package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
public class Notification {
	    private final IntegerProperty Time;
	    private final StringProperty Title;
	    private final BooleanProperty Monday;
	    private final BooleanProperty Tuesday;
	    private final BooleanProperty wednesday;
	    private final BooleanProperty Thursday;
	    private final BooleanProperty Friday;
	    private final BooleanProperty Saturday;
	    private final BooleanProperty Sunday;
	     
	    public Notification()
	    {
	        Time = new SimpleIntegerProperty(this, "Time");
	        Title = new SimpleStringProperty(this, "Title");
	        Monday = new SimpleBooleanProperty(this, "Monday");
	        Tuesday = new SimpleBooleanProperty(this, "Tuesday");
	        wednesday = new SimpleBooleanProperty(this, "wednesday");
	        Thursday = new SimpleBooleanProperty(this, "Thursday");
	        Friday = new SimpleBooleanProperty(this, "Friday");
	        Saturday = new SimpleBooleanProperty(this, "Saturday");
	        Sunday = new SimpleBooleanProperty(this, "Sunday");
	    }
	    public StringProperty TitleProperty() { return Title; }
	    public String getTitle() { return Title.get(); }
	    public void setTitle(String newTitle) { Title.set(newTitle); }
	    
	    public IntegerProperty TimeProperty() { return Time; }
	    public int getTime() { return Time.get(); }
	    public void setTime(int newTime) { Time.set(newTime); }
	    
	    public BooleanProperty MondayProperty() { return Monday; }
	    public Boolean getMonday() { return Monday.get(); }
	    public void setMonday(Boolean newMonday) { Monday.set(newMonday); }
	    
	    public BooleanProperty TuesdayProperty() { return Tuesday; }
	    public Boolean getTuesday() { return Tuesday.get(); }
	    public void setTuesday(Boolean newTuesday) { Tuesday.set(newTuesday); }
	    
	    public BooleanProperty wednesdayProperty() { return wednesday; }
	    public Boolean getwednesday() { return wednesday.get(); }
	    public void setwednesday(Boolean newwednesday) { wednesday.set(newwednesday); }
	    
	    public BooleanProperty ThursdayProperty() { return Thursday; }
	    public Boolean getThursday() { return Thursday.get(); }
	    public void setThursday(Boolean newThursday) { Thursday.set(newThursday); }
	    
	    public BooleanProperty FridayProperty() { return Friday; }
	    public Boolean getFriday() { return Friday.get(); }
	    public void setFriday(Boolean newFriday) { Friday.set(newFriday); }
	    
	    public BooleanProperty SaturdayProperty() { return Saturday; }
	    public Boolean getSaturday() { return Saturday.get(); }
	    public void setSaturday(Boolean newSaturday) { Saturday.set(newSaturday); }
	    
	    public BooleanProperty SundayProperty() { return Sunday; }
	    public Boolean getSunday() { return Sunday.get(); }
	    public void setSunday(Boolean newSunday) { Sunday.set(newSunday); }
	}
