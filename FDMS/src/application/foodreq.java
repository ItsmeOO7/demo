package application;

public class foodreq

{
	private String Id;
	private String Name;
	private String foodname; 
	private String foodweight ;
	private String center;
	private String Date;
	private String status;
	private int num;
	
	
	 public foodreq(String Id,String Name, String foodname, String foodweight,String center,String Date,String status ,int num) {
	        this.Id=Id;
	        this.Name=Name;
		    this.foodname = foodname;
	        this.foodweight = foodweight;
	        this.center =center;
	        this.Date =Date;
	        this.status = status;
	        this.num=num;
	    }
	
	public foodreq()
	{
	
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
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

	public String getdate() {
		return Date;
	}

	public void setdate(String date) {
		Date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	
	
}