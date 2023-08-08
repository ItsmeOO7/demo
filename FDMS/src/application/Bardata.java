package application;

public class Bardata{
	
	private String Date;
	private String sum;
	
	public Bardata(String Date,String sum)
	{
		this.Date=Date;
		this.sum=sum;
	}
	
	public Bardata()
	{
		
	}

	

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
}