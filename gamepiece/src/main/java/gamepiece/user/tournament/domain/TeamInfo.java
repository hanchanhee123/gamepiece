package gamepiece.user.tournament.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties({"teamId",
					   "imageUrl",
					   "colorImageUrl",
					   "whiteImageUrl",
					   "blackImageUrl",
					   "dssWhiteImageUrl",
					   "dssBlackImageUrl",
					   "orderPoint"
					   })
@Data
public class TeamInfo {
	private String gameCode;
	private String name;
	private String nameAcronym;
	private String nameEng;
	private String nameEngAcronym;
}
