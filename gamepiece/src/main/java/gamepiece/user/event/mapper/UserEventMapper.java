package gamepiece.user.event.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.event.domain.Event;

@Mapper
public interface UserEventMapper {

	List<Event> progressEvent();
}
