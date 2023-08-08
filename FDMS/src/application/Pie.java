package application;

public class Pie{
	
	private String Center;
	private String sum;
	
	public Pie(String Center,String sum)
	{
		this.Center=Center;
		this.sum=sum;
	}
	
	public Pie()
	{
		
	}

	public String getCenter() {
		return Center;
	}

	public void setCenter(String center) {
		Center = center;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
	
}