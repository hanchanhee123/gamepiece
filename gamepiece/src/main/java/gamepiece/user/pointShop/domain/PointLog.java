package gamepiece.user.pointShop.domain;

import java.text.DecimalFormat;

import lombok.Data;

@Data
public class PointLog {
	
	private String pointNo;
	private String id;
	private String pry;
	private String prc;
	private int pointReceive;
	private String pointDivision;
	private int totalPoint;
	
	public String getTotalPointFormatted() {
        DecimalFormat fmt = new DecimalFormat("#,###");
        return fmt.format(totalPoint);
    }
	
}
