package gamepiece.user.event.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import gamepiece.user.event.domain.Event;

@Component("user.EventMapper")
@Mapper
public interface EventMapper {

	List<Event> progressEvent();

}
