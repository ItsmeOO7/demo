package application;

public class donation

{
	private String Id;
	private String foodname ;
	private String foodtype ;
	private String foodweight ;
	private String center;
	private String EXDate;
	private String Date;
	
	
	 public donation(String Id,String foodname, String foodtype, String foodweight,String center,String EXDate,String Date ) {
	        this.Id=Id;
		    this.foodname = foodname;
	        this.foodtype = foodtype;
	        this.foodweight = foodweight;
	        this.center =center;
	        this.EXDate =EXDate;
	        this.Date = Date;
	    }
	
	public donation()
	{
	
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getFoodtype() {
		return foodtype;
	}

	public void setFoodtype(String foodtype) {
		this.foodtype = foodtype;
	}

	public String getFoodweight() {
		return foodweight;
	}

	public void setFoodweight(String foodweight) {
		this.foodweight = foodweight;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getEXDate() {
		return EXDate;
	}

	public void setEXDate(String eXDate) {
		EXDate = eXDate;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
	
}